package Tests;

import PageObjects.CartPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

    @Test (groups = {"ErrorHandlingTest"})
    public void loginErrorValidationTest(){

        ProductCatalogue productCatalogue = landingPage.loginApplication("swar@adia.com", "Swat000ia1");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
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
