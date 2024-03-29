**Submit Order Test - Explanation** 

1. The test simulates the process of placing an order form beginning to the end.
2. All the element locator pertaining to every webpage is stored in its respective Page Object class.
3. The PageObject Classes are under main/java as they do not have test data in them.
4. These classes are used to write test classes which are under test/java.
5. The SubmitOrderTest begin with creating an object of ProductCatalogue page.
6. The TestNG annotation BeforeMethod is used in the BaseTest Class that runs first.
7. The launchApplication method runs to
    i. initialize webdriver.
    ii. logins into the Website using userEmail and Password.
    iii. returns a landing page object back to SubmitOrderTest.
8. The landingPage object is stored in the ProductCatalogue object and used to get the list of products.
9. Also add the product to cart based on the productName passed by the DataProvider method, which is in return fetched
   from a JSON file.
10. Each POM class has a method to go to the next page and returns the object to the next page.
11. Each POM class uses @FindBy or By annotation to find element locators.
12. Each POM class has a constructor that take the Webdriver object form the AbstractComponents parent class and sets
    it to the driver object of its own class.
13. Add initializes all the element location using the iniElement method of the PageFactory class.

**AbstractComponents Class - Explanation**

1. It has the element locators of all the generic components that appear on the Website like
   i. Home Page
   ii. Orders Page
   iii. Cart Page
2. Also has method that implements the action to load those generic pages.
3. Also has method to implement ExplicitWaits for element to  appear and to disappear.

**Global properties file under Resource Package - Explanation**

1. This holds data regarding the browser. Changing browser here can help run tests on different version.
2. The BaseTest class inherits AbstractComponents Class , the driverInitializer method take the data from Global
   properties file to change the browser.

**BaseTest Class - Explanation**

1. Run all the base method to get the test started like , initializes the driver and opens the browser.
2. Has the methods with @BeforeMethod annotation for driverInitializer method.
3. Has the @AfterMethod annotation for the closeBrowser method.
4. Launched the website and logins in into it using userEmail and Password.
5. Lastly has a method to take screenshot of the website when the test fails to send the file to reports.

**testSuites Package - Explanation**

1. The package as many TestNg xml file to run specific tests based on the groups and execution type (parallel).

**reports Package - Explanation**

1. This package has an index.html file to see the Extent Report based on the test execution.
2. Screenshot folder holder the screenshot of test fails.
3. Extent report is implemented by adding the dependency in the pom.xml.
4. A class is created under Resources package called ExtentReportNG to initialize extent report.
5. The object of Class ExtentSparkReport is created to set the path of index.html file.
6. As well as set configurations like Document Name, report name etc.
7. Then initialize and assigned ExtentReport object with different configuration, and returned the same object.

**Listener Class - Explanation**

1. This class is under the TestComponents Package.
2. The class implements ITestListener interface.
3. Here , Extent object is created and all the functions are performed based on the test execution.
4. And adding the Listener tag in testng.xml to include the extent report functionality to run tests.
5. To solve the incorrect reporting on test result on parallel execution ThreadLocal object is used.

**RetryFailedTest Class - Explanation**

1. to be able to run flaky test one more time to be sure of the error, RetryFailedTest class in implement.
2. The class implements IRetryListener interface and check if the count is less than maxTry, if it is, runs the test again.
3. The attribute retryAnalyser is added to the @Test annotation above the flaky test, to be able to run again.

**To run test from command line - Explanation**

1. maven has to be installed and env variable has to be set and well as the org.apache.maven.plugins 
   dependency should be added in pom.xml file.
2. created profiles in pom.xml file for different testng.xml file, to be able to run these files from cmd prompt.
3. use mvn -test -P(profile name) to run the test.
4. Also changed the code in BaseTest class file to dynamically accept browser type to run the tests
  cmd to run mvn test -P(profile name) -DFirefox

**Setting up jenkins - Explanation**

1. Download jenkins , I downloaded using homebrew with the below commands
   Sample commands:
   Install the latest LTS version: **brew install jenkins-lts**
   Start the Jenkins service: **brew services start jenkins-lts**
   Restart the Jenkins service: **brew services restart jenkins-lts**
   Update the Jenkins version: **brew upgrade jenkins-lts**
2. Once the server starts hit the local server - **localhost:8080** to launch Jenkins
3. Set up new project, I gave my gitHub repo to download code.
4. Did the below configurations
      Repository URL : https://github.com/swatiradia/selenium-test-framework.git
      Branch Specifier : */main
      Build Steps -> Invoke top-level Maven targets 
        Maven Version : Maven 
        Goals : test -P"$profile"  -Dbrowser="$browserName"
      Choice Parameter - > Name : browserName
      Choices : chrome
                chromeheadless
                firefox
                edge
      Choice Parameter - > Name : profile
      Choices : Regression
                Purchase
                ErrorValidation
                CucumberTests

**Cucumber BDD Files - Explanation**

1. Created a new package called CucumberFiles to add all the Feature files.
2. Another package called StepDefinitionFiles for Step definition implementation files.
3. Created TestNGTestRunner files to be able to run Cucumber files. More explanation is in the file comments.