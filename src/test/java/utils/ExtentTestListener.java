package utils;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestListener extends BaseTest implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private WebDriver getDriverFromResult(ITestResult result) {
        return ((BaseTest) result.getInstance()).getDriver();
    }



    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(
                result.getMethod().getMethodName() + " [" + BaseTest.getBrowserName() + "]");
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = getDriverFromResult(result);
        test.get().log(Status.PASS, "Test passed");

        if (driver != null) {
            try {
                String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.get().log(Status.INFO, "Screenshot could not be attached.");
            }
        } else {
            test.get().log(Status.WARNING, "Driver is null. Screenshot skipped.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriverFromResult(result);
        test.get().log(Status.FAIL, "Test failed: " + result.getThrowable());

        if (driver != null) {
            try {
                String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.get().log(Status.INFO, "Screenshot could not be attached.");
            }
        } else {
            test.get().log(Status.WARNING, "Driver is null. Screenshot skipped.");
        }
        System.out.println("Test failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        WebDriver driver = getDriverFromResult(result);
        test.get().log(Status.SKIP, "Test skipped");

        if (driver != null) {
            try {
                String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.get().log(Status.INFO, "Screenshot could not be attached.");
            }
        } else {
            test.get().log(Status.WARNING, "Driver is null. Screenshot skipped.");
        }
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }


}
