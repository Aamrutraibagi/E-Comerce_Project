package amrutraibagi.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import amrutraibagi.AbstractComponents.AbsractComponents;



public class ProductCatelog extends AbsractComponents {
	
	WebDriver driver;
	public ProductCatelog(WebDriver driver) {
		//If we want to Initialize the Code Constructor is best to use
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector("div.card-body"));
	//By using PageFactory design pattern we can define Webelement as above
	@FindBy(css="div.card-body")
	List<WebElement> products;
	
	//driver.findElement(By.cssSelector(".ng-animating")
	@FindBy(css=".ng-animating")
	WebElement Spinner;
			
	
	By produnctsBY=By.cssSelector("div.card-body");
	By addToCart=By.cssSelector("div.card-body button:last-of-type");
	By toasterMSG=By.cssSelector("#toast-container");
	
	public  List<WebElement> getProducts() {
		waitForElement(produnctsBY);
		return products;
	}
	
	
	//Products list in Home Page
	public WebElement getProductByName(String ProductName) {
		
		//Stream help to iterate
		//filter to check the condition
		WebElement prod=getProducts().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
	}
	
	//select the Product from Home Page
	public void addProduct(String ProductName) {
		WebElement prod=getProductByName(ProductName);
		prod.findElement(addToCart).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		waitForElement(toasterMSG);
		
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		waitForInvisiblityOfElement(Spinner);
	}
	
	
	
	
	
	

}
