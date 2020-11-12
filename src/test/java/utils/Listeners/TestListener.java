package utils.Listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.Commons.Screencapture;
import utils.ExtentReports.ExtentManager;
import utils.ExtentReports.ExtentTestManager;

public class TestListener extends BaseTest implements ITestListener {
	Logger log = LogManager.getLogger(TestListener.class);

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onStart(ITestContext iTestContext) {
		log.info("Test Class Instantiated : " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", this.driver);
	}

	public void onFinish(ITestContext iTestContext) {
		log.info("Test Finished : " + iTestContext.getName());
		//Do tier down operations for extentreports reporting!
		ExtentManager.getReporter().flush();
	}

	public void onTestStart(ITestResult iTestResult) {
		log.info("Test Case Started : " + getTestMethodName(iTestResult));
		ExtentTestManager.getTest().assignCategory(iTestResult.getTestClass().getRealClass().getSimpleName());
	}

	public void onTestSuccess(ITestResult iTestResult) {
		log.info("Test Case Passed :" + getTestMethodName(iTestResult));

		//Get driver from BaseTest and assign to local webDriver variable.
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getDriver();

		//Take Screenshot
		String screenshotPath = Screencapture.getScreenshotBase64(webDriver);

		//ExtentReports log and screenshot operations for failed tests.
		try {
			ExtentTestManager.getTest().info(MarkupHelper.createLabel("Test Passed",ExtentColor.GREEN));
			ExtentTestManager.getTest().pass("Screenshot : ", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void onTestFailure(ITestResult iTestResult)  {
		log.info("Test Case Failed :" + getTestMethodName(iTestResult));

		//Get driver from BaseTest and assign to local webDriver variable.
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getDriver();

		//Take Screenshot
		String screenshotPath = Screencapture.getScreenshotBase64(webDriver);

		//ExtentReports log and screenshot operations for failed tests.
		try {
			ExtentTestManager.getTest().info(MarkupHelper.createLabel("Test Failed",ExtentColor.RED));
			ExtentTestManager.getTest().info(MarkupHelper.createLabel(""+iTestResult.getThrowable(),ExtentColor.RED));
			ExtentTestManager.getTest().fail("Screenshot : ", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult iTestResult) {
		log.info("Test Case Skipped :  " + getTestMethodName(iTestResult));
		//ExtentReports log operation for skipped tests.
		ExtentTestManager.getTest().info(MarkupHelper.createLabel("Test Skipped",ExtentColor.ORANGE));
		ExtentTestManager.getTest().skip(MarkupHelper.createLabel(""+iTestResult.getThrowable(),ExtentColor.ORANGE));
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		log.info("Test Case failed but it is in defined success ratio  : " + getTestMethodName(iTestResult));
	}

}
