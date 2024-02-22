package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

    WebDriver driver;

    //    constructor
    public  LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail") WebElement userEmail;
    @FindBy(id = "userPassword") WebElement userPassword;
    @FindBy(id = "login") WebElement submit;

    By loginError = By.cssSelector(".ng-trigger-flyInOut");

    public void GoToPage(){
        driver.get("https://rahulshettyacademy.com/client");

    }
    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();

/*    Creating an object for ProductCatalogue page and returning it*/

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage(){
        waitForElementToAppear(loginError);
        return driver.findElement(loginError).getText();

    }

}
