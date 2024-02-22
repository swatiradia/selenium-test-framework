package Tests;

import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest {

    @Test
    public void ErrorValidation(){

        ProductCatalogue productCatalogue = landingPage.loginApplication("swar@adia.com", "Swat000ia1");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }
}
