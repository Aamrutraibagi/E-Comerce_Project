package amrutraibagi.PageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amrutraibagi.AbstractComponents.AbsractComponents;



public class LandingPage extends AbsractComponents {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		//If we want to Initialize the Code Constructor is best to use
		//Using Super keyword we can pass the driver to parent class
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//WebElement UserEmail=driver.findElement(By.id("userEmail"));
	//By using PageFactory design pattern we can define Webelement as above
	@FindBy(id="userEmail")
	WebElement UserEmail;
	
	//driver.findElement(By.id("userPassword")).sendKeys("Pappa@143");
	@FindBy(id="userPassword")
	WebElement Passward;
	
	//driver.findElement(By.id("login")).click();
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatelog LoginApplication(String Email,String password) {
		UserEmail.sendKeys(Email);
		Passward.sendKeys(password);
		submit.click();
		ProductCatelog PC=new ProductCatelog(driver);
		return PC;
	}
	
	public String getErrorMessage() {
		//It will wait for WebElement to appear on page //code written in AbstractComponents page
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void GoTO() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//amrutRaibagi//resources//GlobalData.properties");
		prop.load(fis);
		String URL=prop.getProperty("URL");
		driver.get(URL);
	}
	
	

}
