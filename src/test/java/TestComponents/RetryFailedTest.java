package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {

    int maxTry = 1;
    int count =0;

    @Override
    public boolean retry(ITestResult result) {
        if(count<maxTry){
            count++;
            return true;
        }
        return false;
    }
}
