package TestComponents;

import DataPackage.DataReader;
import PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

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
