package StepDefinitionFiles;

import PageObjects.*;
import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefsImplementation extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;

    public ConfirmationPage confirmationPage;

    @Given("I landed on the Ecommerce page")
    public void I_landed_on_the_Ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("I logged in with {} and {}")
    public void iLoggedInWithAnd(String email, String password) {
        productCatalogue = landingPage.loginApplication(email, password);
    }

    @When("I add the {} to the cart")
    public void iAddTheToTheCart(String productName) {
        productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

    }

    @And("I checkout with {}")
    public void iCheckoutWith(String productName) {
        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplayed(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectingTheCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("I see {} is displayed")
    public void iSeeIsDisplayed(String confirmationMessage) {

        String confirmMessage = confirmationPage.getConfirmationText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(confirmationMessage));
        closeBrowser();
    }
}
