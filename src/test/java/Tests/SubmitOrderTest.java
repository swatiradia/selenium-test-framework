package Tests;
import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;



public class SubmitOrderTest extends BaseTest {
    String countryName = "india";
    @Test (dataProvider = "getData", groups = {"Purchase"})
        public void submitOrderTest(String email, String password, String productName) throws IOException {

/*        All the actions happening in Landing Page are Handled in BaseTest class and the Landing page
          object is created there and the application is launched using the @BeforeMethod annotation  */

        ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

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
    }


    @Test (dependsOnMethods = "submitOrderTest")
    public void orderHistoryTest() throws IOException {

        ProductCatalogue productCatalogue = landingPage.loginApplication("swati@radia.com", "Swati@radia1");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDetails("ZARA COAT 3"));

    }

    @DataProvider
    public Object[][] getData(){
        return new Object [][] {{"swati@radia.com","Swati@radia1", "ZARA COAT 3"},{"mihir@radia.com","Mihir@radia6", "ADIDAS ORIGINAL"}};
    }
}
