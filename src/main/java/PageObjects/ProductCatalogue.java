package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;

//    constructor
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "col-lg-4")
    List<WebElement> products;

    @FindBy (css = ".ng-animation" )
            WebElement spinner;

    By productsBy = By.cssSelector("col-lg-4");
    By addToCartBy = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.id("toast-container");


    public List<WebElement> getProductList(){
        waitForElementToAppear((productsBy));
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText()
                .equalsIgnoreCase(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCartBy).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }


}
