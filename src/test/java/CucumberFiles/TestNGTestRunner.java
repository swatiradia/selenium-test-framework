package CucumberFiles;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


// The below step runs all teh files given in features, maps it to files in glue, monochrome
// give results in readable format, and generate the reports in html format.
@CucumberOptions (features = "src/test/java/CucumberFiles", glue = "StepDefinitionFiles",
monochrome = true, plugin = {"html:target/cucumber.html"})

// By default, cucumber will not be able to scan the TestNG assertions/libraries. To create seamless integration
// we extend AbstractTestNGCucumberTests - helps cucumber understand and TestNg code wherever it's written.
//However, Junit is inbuilt in cucumber.
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
