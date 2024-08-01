package amrutraibagi.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import amrutraibagi.PageObjects.CartPage;
import amrutraibagi.PageObjects.OrderPage;



public class AbsractComponents {
	
	WebDriver driver;
	
	public AbsractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();
	@FindBy (css="[routerlink*=\"cart\"]")
	WebElement CartHeader;
	
	@FindBy (css="button[routerlink='/dashboard/myorders']")
	WebElement myorders;

	//By.cssSelector("div.card-body")
	public void waitForElement(By findBy) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	public void waitForInvisiblityOfElement(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
		}
	
	
	//driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();
	public CartPage goToCartPage() {
		CartHeader.click();
		CartPage CP=new CartPage(driver);
		return CP;
	}
	
	public OrderPage goToMyOrdersPage(){
    	myorders.click();
    	OrderPage OP=new OrderPage(driver);
    	return OP;
    }
	

}
