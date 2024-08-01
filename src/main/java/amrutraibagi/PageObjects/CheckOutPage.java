package amrutraibagi.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import amrutraibagi.AbstractComponents.AbsractComponents;



public class CheckOutPage extends AbsractComponents {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		//If we want to Initialize the Code Constructor is best to use
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")
	//By using PageFactory design pattern we can define Webelement as above
	@FindBy(xpath="//input[@placeholder=\'Select Country\']")
	WebElement CountryDropdown;
	
	By DropdownvalueBy=By.cssSelector(".ta-results");
	
	
	//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	@FindBy (css=".ta-item:nth-of-type(2)")
	WebElement SelectCountry;
	
	
	//driver.findElement(By.cssSelector(".action__submit"))
	@FindBy (css=".action__submit")
	WebElement PaceOrderBtn;
	
	
	//Actions a=new Actions(driver);
	//a.sendKeys(driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")), "India").build().perform();
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	//WebElement PlaceOrder=driver.findElement(By.cssSelector(".action__submit"));
	//JavascriptExecutor js1=(JavascriptExecutor)driver;
	//js1.executeScript("arguments[0].click()", PlaceOrder);
	
	public  void selectCountry(String country) {
		Actions a=new Actions(driver);
		a.sendKeys(CountryDropdown, country).build().perform();
		waitForElement(DropdownvalueBy);
		SelectCountry.click();
		
	}
	
	public ConfirmationPage SubmitOrder() {
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click()", PaceOrderBtn);
		return new ConfirmationPage(driver);
	}
	
	

}
