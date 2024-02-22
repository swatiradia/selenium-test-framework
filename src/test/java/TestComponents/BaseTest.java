package TestComponents;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public  WebDriver driverInitializer() throws IOException {


/*      Fetching Data from GlobalData.properties file to have a more efficient testing structure */

        Properties prop = new Properties();

/*      System.getProperty("user.dir") get the patch dynamically depending on different users */

        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Resources/GlobalData.properties");
        prop.load(fileInputStream);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")){ /*      To run the test in Chrome  */
            WebDriverManager.chromedriver().clearDriverCache().setup();
            driver = new ChromeDriver();

        }else if (browserName.equalsIgnoreCase("firefox")) {  /*      To run the test in Firefox  */
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver = driverInitializer();
        landingPage = new LandingPage(driver);
        landingPage.GoToPage();
        return landingPage;

    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
