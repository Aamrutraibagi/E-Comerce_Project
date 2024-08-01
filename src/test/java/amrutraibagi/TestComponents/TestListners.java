package amrutraibagi.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import amrutraibagi.Resources.ExtentReporterNG;

public class TestListners extends BaseTest implements ITestListener {
		
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ExtentTest test;
	//with test we are facing the non synchonization issue to make the process synchronized we make it as thread safe by ThreadLocal
	//even after running test concurrently Each object creation has it's own thread
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	@Override
	//"result" variable hold all the information about the test it start to execute so we use result.getMethod to fetch the test method name
	public void onTestStart(ITestResult result) {
		
	    //When we start to execute we need to createTest with Test name
		test=extent.createTest(result.getMethod().getMethodName());
		//here how we push object inside ThreadLocal, which will assign unique thread id -->test
		extentTest.set(test); 
	  }

	@Override
	public void onTestSuccess(ITestResult result) {
		 extentTest.get().log(Status.PASS, "Test Passed");
	  }

	@Override
	public void onTestFailure(ITestResult result) {
		//1st take screenshot 2nd step attach the screenshot
		
		 extentTest.get().log(Status.FAIL, "Test Failed"); //this is just msg
	    extentTest.get().fail(result.getThrowable()); //we will get error msg
	    //we need to give the life to this driver
	    try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	    
	    //screenshot attach it to the report
	    String filePath = null;
		try {
			filePath=getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }

	 

	@Override
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	@Override
	public void onStart(ITestContext context) {
	    // not implemented
	  }

	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();
	  }
}
