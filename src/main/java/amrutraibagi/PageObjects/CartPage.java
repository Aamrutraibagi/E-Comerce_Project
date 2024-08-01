package amrutraibagi.PageObjects;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import amrutraibagi.AbstractComponents.AbsractComponents;



public class CartPage extends AbsractComponents {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		//If we want to Initialize the Code Constructor is best to use
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//List<WebElement> Cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
	//By using PageFactory design pattern we can define Webelement as above
	@FindBy(css=".cartSection h3")
	List<WebElement> Cartproducts;
	
	
	//driver.findElement(By.cssSelector(".totalRow button")
	@FindBy (css=".totalRow button")
	WebElement CheckOutBtn;
	
	
	
	
	public  List<WebElement> getCartProducts() {
		
		return Cartproducts;
	}
	
	
	//verify the added product in Cart Page
	public Boolean verifyProductDisplay(String ProductName) {
		
		//Stream help to iterate
		//filter to check the condition
		Boolean match=getCartProducts().stream().anyMatch(Cproduct->Cproduct.getText().equalsIgnoreCase(ProductName));
		System.out.println(match);
		return match;
	}
	
	
	//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".totalRow button"))));
	//WebElement Checkout=driver.findElement(By.cssSelector(".totalRow button"));
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click()", Checkout);
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	public CheckOutPage gotoCheckoutPage() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", CheckOutBtn);
		CheckOutPage checkoutpage=new CheckOutPage(driver);
		return checkoutpage;
		 
	}
	
	
	
	
	
	
	
	
	

}
