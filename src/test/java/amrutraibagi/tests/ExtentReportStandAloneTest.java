package amrutraibagi.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportStandAloneTest {
	
	
	ExtentReports extent;
	@BeforeTest
	public void reportConfig() {
		
		
		//ExtentReports   & ExtentSpartReporter impotent class for creating Extent Report
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//ExtentReports is main class and the ExtentSpartReporter report is attached to ExtentReports main class
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Amrut Raibagi");
		
	}
	
	
	@Test
	public void initializeDemo() {
		
		ExtentTest Test=extent.createTest("initializeDemo Test");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		driver.close();
		Test.fail("Result do not match");
		extent.flush();
	}

}
