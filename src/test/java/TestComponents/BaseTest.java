package TestComponents;

import DataPackage.DataReader;
import PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest extends DataReader {

    public WebDriver driver;
    public LandingPage landingPage;
    public  WebDriver driverInitializer() throws IOException {


/*      Fetching Data from GlobalData.properties file to have a more efficient testing structure */

        Properties prop = new Properties();

/*      System.getProperty("user.dir") get the patch dynamically depending on different users */

        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Resources/GlobalData.properties");
        prop.load(fileInputStream);


        String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");


        if (browserName.contains("chrome")){ /*      To run the test in Chrome  */

//            ChromeOptions object is used to be able to run the tests in headless mode, the object is passed
//            in the ChromeDriver object initialization

            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().clearDriverCache().setup();
            if(browserName.contains("headless")){
                chromeOptions.addArguments("headless");
            }
            driver = new ChromeDriver(chromeOptions);
//            Recommended full screen size
            driver.manage().window().setSize(new Dimension(1440, 900));


        }else if (browserName.equalsIgnoreCase("firefox")) {  /*      To run the test in Firefox  */
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }


    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
//        read json to string, StandardCharsets.UTF_8 is encoding format
        String JsonContent = FileUtils.readFileToString
                (new File(filePath),
                        StandardCharsets.UTF_8);

//        Converting String to HashMap to feed to the DataProvider Method
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;

    }

    //    Method to take screenshot when a test fails and save the file in the destinationFile location
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(System.getProperty("user.dir") + "/reports/screenshots/" + testCaseName + ".png");
        FileUtils.copyFile(screenShotFile, destinationFile);
        return System.getProperty("user.dir") + "/reports/screenshots/" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = driverInitializer();
        landingPage = new LandingPage(driver);
        landingPage.GoToPage();
        return landingPage;

    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
    }
}
