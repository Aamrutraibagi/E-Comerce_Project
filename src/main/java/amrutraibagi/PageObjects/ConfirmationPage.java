package amrutraibagi.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import amrutraibagi.AbstractComponents.AbsractComponents;



public class ConfirmationPage extends AbsractComponents {
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		//If we want to Initialize the Code Constructor is best to use
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//driver.findElement(By.cssSelector(".hero-primary")).getText()
	//By using PageFactory design pattern we can define Webelement as above
	@FindBy(css=".hero-primary")
	WebElement ConfirmationMsg;
	
	
	//String ConfirmMessage=
	//System.out.println(ConfirmMessage);
	
	//verify the Submitted product Successfully
	public String verifyConfirmationMessage() {
		
		return ConfirmationMsg.getText();
		
	}
	

	

}
