package listeners;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.base;
import resources.extentReporter;

public class Listeners extends base implements ITestListener {
	ExtentReports ext= extentReporter.getExtentReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); //Parallel test
	
	public void onTestStart(ITestResult result) {
		
		// TODO Auto-generated method stub
		 test= ext.createTest(result.getMethod().getMethodName());
		 extentTest.set(test); //parallel test
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.PASS, "As Expected");  //sequential test
		extentTest.get().log(Status.PASS, "As Expected"); //parallel test
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//test.fail(result.getThrowable()); //sequential test
		extentTest.get().fail(result.getThrowable()); //parallel test
		WebDriver driver=null;
		
		String methodName= result.getMethod().getMethodName();
	try {
		driver =(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshot(methodName, driver), result.getMethod().getMethodName()); //parallel test
			//getScreenshot(methodName, driver); //sequential test
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ext.flush();
	}

}
