package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {

    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (className = "form-group")
    WebElement country;

    @FindBy (css = ".ta-item:nth-of-type(2)")
    WebElement selectCountry;

    By countryResults = By.cssSelector(".ta-results");

    @FindBy (css = ".action__submit")
    WebElement submitButton;
    public void selectingTheCountry(String country){
        Actions a = new Actions(driver);
        a.sendKeys(country, country).build().perform();
        waitForElementToAppear(countryResults);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submitButton.click();

/*    Creating an object for ConfirmationPage and returning it */

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }

}
