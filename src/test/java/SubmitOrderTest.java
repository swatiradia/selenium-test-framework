import PageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;



public class SubmitOrderTest {

    public static void main(String[] args) {

        String productName ="ZARA COAT 3";
        String countryName = "india";

        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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

        checkoutPage.selectingTheCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

/*        All the actions happening in ConfirmationPage */

        String confirmMessage = confirmationPage.getConfirmationText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        driver.close();
    }
}
