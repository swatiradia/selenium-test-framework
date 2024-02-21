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



public class StandAloneTest {

    public static void main(String[] args) {

        String productName ="ZARA COAT 3";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

/*        All the actions happening in Landing Page */

        driver.findElement(By.id("userEmail")).sendKeys("swati@radia.com");
        driver.findElement(By.id("userPassword")).sendKeys("Swati@radia1");
        driver.findElement(By.id("login")).click();

/*        All the actions happening in Product Catalogue Page */

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

        List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
        WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText()
                .equalsIgnoreCase(productName)).findFirst().orElse(null);
//        assert prod != null;
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animation")));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

/*        All the actions happening in Product Catalogue Page */

        List<WebElement> productInCart = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));

        boolean match = productInCart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();

/*        All the actions happening in Checkout Page */

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.className("form-group")), "india").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

/*        All the actions happening in Confirmation  Page */

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        
    }
}
