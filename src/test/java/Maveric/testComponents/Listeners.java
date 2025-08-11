package Maveric.testComponents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Maveric.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener  {
	ExtentReports extent=ExtentReportsNG.config();
	ExtentTest test;
	ThreadLocal<ExtentTest>extentTest=new ThreadLocal();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	
		//This is the entry point like how a test case and it's extent report got mapped
		test=extent.createTest(result.getMethod().getMethodName());
		
		extentTest.set(test);//It will generate a unique id for each test whenever the tests will come here
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test case is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//By Using thi, we are trasnfering the driver from the Test class to this Listener class as the result variable is already having the idea of the Test Case/class
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Screenshot & Attach it to report
		extentTest.get().fail(result.getThrowable());
		String FilePath = null;
		try {
			FilePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(FilePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
