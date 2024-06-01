package listeners;

import utils.Log;
import utils.Reporters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.Scenario;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This is the testNG listeners class that will have create and flush extent report
 */
public class TestListener implements ITestListener {

    public static ExtentReports extent = Reporters.getReportInstance();
    public static ThreadLocal<ExtentTest> test =new ThreadLocal<ExtentTest>();

    private Log log=new Log();
    @Override
    public void onFinish(ITestContext contextFinish) {
        log.info(Status.INFO,"Test Suite is Ending");
        extent.flush();
    }

    @Override
    public void onStart(ITestContext contextStart) {
        log.info(Status.INFO,"Test Suite is starting");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info(Status.INFO,"Method failed with certain success percentage" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(Status.FAIL,"Scenario failed ");
//        Object testClass = result.getInstance();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn(Status.WARNING,"Scenario skipped " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
//        test.set(extent.createTest(result.getName()+ " started"));
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(Status.PASS,"Scenario passed");
    }

    public static void setTest(Scenario scenario){
        test.set(TestListener.extent.createTest(scenario.getName()));
    }
}