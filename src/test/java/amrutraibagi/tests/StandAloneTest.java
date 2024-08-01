package amrutraibagi.tests;
import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		String productName="ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		//LandingPage LP=new LandingPage(driver);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("amrutraibagi2018@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pappa@143");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.card-body")));
		List<WebElement> products=driver.findElements(By.cssSelector("div.card-body"));
		
		//Stream help to iterate
		//filter to check the condition
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//.ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();
		
		//Finding product in Cart page
		List<WebElement> Cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		//Stream help to iterate
		//anyMatch will compare and return boolean value to check the condition
		Boolean match=Cartproducts.stream().anyMatch(Cproduct->Cproduct.getText().equals(productName));
		Assert.assertTrue(match);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".totalRow button"))));
		WebElement Checkout=driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", Checkout);
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Thread.sleep(2000);
		//Handle Auto Suggestive DropDown
		Actions a=new Actions(driver);
		//a.moveToElement(driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]"))).click().sendKeys("India").build().perform();
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		Thread.sleep(1000);
		//driver.findElement(By.className("action__submit")).click();
		WebElement PlaceOrder=driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click()", PlaceOrder);
		
		String ConfirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(ConfirmMessage);
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
		
	}
}
