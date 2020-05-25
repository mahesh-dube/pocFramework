package Report;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import pocFramework.pocFramework.GetScreenshot;

public class TestListener implements ITestListener {

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");

		try {

			GetScreenshot.captureScreen(result.getName());

		} catch (IOException e) {

			ExtentTestManager.getTest().info("An exception occurred while taking screenshot " + e.getCause());
		}
		try {
			ExtentTestManager.getTest().pass("Screenshot", MediaEntityBuilder
					.createScreenCaptureFromPath("." + GetScreenshot.captureScreen(result.getName())).build());
		} catch (IOException e) {
			ExtentTestManager.getTest().info("An exception occured while taking screenshot " + e.getCause());
		}
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {

		ExtentTestManager.getTest().info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().info((result.getMethod().getMethodName() + " failed!"));

		try {

			GetScreenshot.captureScreen(result.getName());

		} catch (IOException e) {

			ExtentTestManager.getTest().info("An exception occurred while taking screenshot " + e.getCause());
		}
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

		try {
			ExtentTestManager.getTest().fail("Screenshot", MediaEntityBuilder
					.createScreenCaptureFromPath("." + GetScreenshot.captureScreen(result.getName())).build());
		} catch (IOException e) {
			ExtentTestManager.getTest().info("An exception occured while taking screenshot " + e.getCause());
		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
