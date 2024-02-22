package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class OrderPage extends AbstractComponents {

    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "tr td:nth-child(3)")
    List<WebElement> Orders;

    public boolean verifyOrderDetails(String productName){
        Boolean match = Orders.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }

}
