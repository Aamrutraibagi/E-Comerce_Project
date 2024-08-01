package amrutraibagi.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import amrutraibagi.PageObjects.CartPage;
import amrutraibagi.PageObjects.ProductCatelog;
import amrutraibagi.TestComponents.BaseTest;
import amrutraibagi.TestComponents.RetryFailedTest;

public class ErrorValidations extends BaseTest {
	@Test(groups= {"ErrorHandling"},retryAnalyzer=RetryFailedTest.class)
	public void LoginErrorValidation() {
		String productName="ZARA COAT 3";
		LP.LoginApplication("amrutraigi2018@gmail.com", "Pappa@143");
		Assert.assertEquals("Incorrectemail or password.", LP.getErrorMessage());
	}
	
	
	
	@Test
	public void ProductErrorValidation() throws IOException  {
		
		String productName="IPHONE 13 PRO";
		String CountryName="India";
		
		//LandingPage LP=launchApplication();
		LP.LoginApplication("amrut.raibag2018@gmail.com", "Pappa@143");
		ProductCatelog PC=new ProductCatelog(driver);
		List<WebElement> Pnames=PC.getProducts();
		PC.getProductByName(productName);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		List<WebElement> productslist=PC.getProducts();
		PC.addProduct(productName);
		
		//Taking to Cart Page(object returned in AbstractComponents class)
		CartPage CP=PC.goToCartPage();
		
		
		
		Boolean match=CP.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		

	}
	
}
