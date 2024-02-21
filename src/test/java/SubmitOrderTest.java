import PageObjects.*;
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
        String county = "india";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

/*        All the actions happening in Landing Page */

        LandingPage landingPage = new LandingPage(driver);
        landingPage.GoToPage();
        ProductCatalogue productCatalogue = landingPage.loginApplication("swati@radia.com", "Swati@radia1");

/*        All the actions happening in Product Catalogue Page */

        productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

/*        All the actions happening in Product Catalogue Page */

        boolean match = cartPage.verifyProductDisplayed(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();

/*        All the actions happening in Checkout Page */

         checkoutPage.selectingTheCountry(county);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

/*        All the actions happening in Confirmation  Page */

        String confirmMessage = confirmationPage.getConfirmationText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        driver.close();
    }
}
