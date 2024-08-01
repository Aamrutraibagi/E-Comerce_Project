package amrutraibagi.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import amrutraibagi.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage LP;
	
	//these above veriables are made as public so every class can access them
	public WebDriver InitializeDriver() throws IOException {
		//object creation for properties class which is under java.util 
		Properties prop=new Properties();
				
		//object creation for FileInputStream to convert file (GlobalData.properties) into input stream wgich is under java.Io
		//FileInputStream it accepts the file path inside arguments
		//C:\\Users\\2326232\\Documents\\Automation Practice\\Practice_1\\src\\main\\java\\amrutRaibagi\\resources\\GlobalData.properties
		//As above path is To long so to reduce the writting of sysytem path we have method called System.getProperty("user.dir")
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//amrutRaibagi//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("Browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("Firefox")){
			
			//Firefox
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	//Before every method this method will get executed
		@BeforeMethod(alwaysRun=true)
		public LandingPage launchApplication() throws IOException {
			driver=InitializeDriver();
			LP=new LandingPage(driver);
			LP.GoTO();
			return LP;
		}
		
		public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
			TakesScreenshot TS=(TakesScreenshot)driver;
			File Source=TS.getScreenshotAs(OutputType.FILE);
			File file=new File(System.getProperty("user.dir")+ "//reports"+ testcaseName + ".png");
			FileUtils.copyFile(Source, file);
			return System.getProperty("user.dir")+ "\\reports"+ testcaseName + ".png";
		}
		
	//After every method this method will get executed
		@AfterMethod(alwaysRun=true)
		public void tearDown() {
			driver.close();
		}
	
}

