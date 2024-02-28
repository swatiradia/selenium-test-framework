package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    public ExtentReports extentReportObject(){

        String path = System.getProperty("user.dir")+"/reports/index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setDocumentTitle("Test Results");
        extentSparkReporter.config().setReportName("Web Automation Result");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("SDET", "Swati Radia");
        extentReports.createTest(path);
        return extentReports;

    }
}
