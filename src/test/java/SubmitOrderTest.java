import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;



public class SubmitOrderTest {

    public static void main(String[] args) {

        String productName ="ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

/*        All the actions happening in Landing Page */

        LandingPage landingPage = new LandingPage(driver);
        landingPage.GoToPage();
        landingPage.loginApplication("swati@radia.com", "Swati@radia1");

/*        All the actions happening in Product Catalogue Page */

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        productCatalogue.clickOnCart();


        List<WebElement> productInCart = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));

        Boolean match = productInCart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);


        driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.className("form-group")), "india").build().perform();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));



    }
}
