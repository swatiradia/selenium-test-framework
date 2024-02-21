package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    /* Constructor */
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//*[@class='cartSection']/h3")
    List<WebElement> productInCart;

    @FindBy (css = ".totalRow button")
    WebElement checkoutButton;

   public boolean verifyProductDisplayed(String productName){
       return productInCart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }

   public CheckoutPage goToCheckout(){
       checkoutButton.click();

/*    Creating an object for CheckoutPage and returning it*/

       CheckoutPage checkoutPage = new CheckoutPage(driver);
       return checkoutPage;
   }
}
