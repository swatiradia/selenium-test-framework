package Tests;

import PageObjects.CartPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import TestComponents.RetryFailedTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

    @Test (groups = {"ErrorHandlingTest"}, retryAnalyzer = RetryFailedTest.class)
    public void loginErrorValidationTest(){

        ProductCatalogue productCatalogue = landingPage.loginApplication("swar@radia.com", "Swat000ia1");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email. password.");
    }

    @Test
    public void productNameErrorValidationTest() throws IOException {

        String productName ="ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("swati@radia.com", "Swati@radia1");
        productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyProductDisplayed("ZARA COAT 12");
        Assert.assertFalse(match);

    }
}
