package com.bit.listeners;

import com.aventstack.extentreports.Status;
import com.bit.base.BaseTest;
import com.bit.utilities.ExtentManager;
import com.bit.utilities.TestUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.bit.utilities.ExtentTestManager.getTest;

//public class CustomListener extends BaseTest_LINUX implements ITestListener {
public class CustomListener extends BaseTest implements ITestListener {
    String screenshotName = null;
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
        ITestListener.super.onTestSuccess(result);
        TestUtil.captureScreenshot(userDir + "/target/"+screenshotName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        context.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        ExtentManager.extentReports.flush();
    }
}