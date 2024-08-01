package amrutraibagi.tests;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import amrutraibagi.Data.DataReader;
import amrutraibagi.PageObjects.CartPage;
import amrutraibagi.PageObjects.CheckOutPage;
import amrutraibagi.PageObjects.ConfirmationPage;
import amrutraibagi.PageObjects.LandingPage;
import amrutraibagi.PageObjects.OrderPage;
import amrutraibagi.PageObjects.ProductCatelog;
import amrutraibagi.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
		String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
		
		//LandingPage LP=new LandingPage(driver);
		//LP.GoTO();
		//Above 2 lines are kept in BaseTest Class
		//Taking to ProductCatelog page(object returned in LandingPage class)
		ProductCatelog PC=LP.LoginApplication(input.get("Email"), input.get("password"));
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		List<WebElement> productslist=PC.getProducts();
		PC.addProduct(input.get("productName"));
		
		//Taking to Cart Page(object returned in AbstractComponents class)
		CartPage CP=PC.goToCartPage();
		
		
		
		Boolean match=CP.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=CP.gotoCheckoutPage();
		Thread.sleep(2000);
		
		
		
		//Handle Auto Suggestive DropDown
		checkoutpage.selectCountry("India");
		Thread.sleep(1000);
		ConfirmationPage confirmationpage=checkoutpage.SubmitOrder();
		
		
		String ConfirmMessage=confirmationpage.verifyConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		//driver.close();
		
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	//dependsOnMethod will help in executing mentioned method before below method to execute
	//This method should get executed only after submitOrder method bcz after submitting order user can check in ordered History Page
	public void orderHistoryTest() {
		
		LP.LoginApplication("amrut.raibag2018@gmail.com", "Pappa@143");
		OrderPage OP=new OrderPage(driver);
		OP.goToMyOrdersPage();
		Boolean match=OP.verifyOrdersDisplay(productName);
		Assert.assertTrue(match);
		
	}

	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//return new Object[][] {{"amrutraibagi2018@gmail.com","Pappa@143","ZARA COAT 3"},{"amrutraibagi2018@gmail.com","Pappa@143","IPHONE 13 PRO"}};
		//Above data we can send by Hash map if the data is more like 15 parameters
		
		/*
		 * Map<String,String> MyMap=new HashMap<String,String>(); MyMap.put("Email",
		 * "amrutraibagi2018@gmail.com"); MyMap.put("password", "Pappa@143");
		 * MyMap.put("productName", "ZARA COAT 3");
		 * 
		 * Map<String,String> MyMap1=new HashMap<String,String>(); MyMap1.put("Email",
		 * "amrutraibagi2018@gmail.com"); MyMap1.put("password", "Pappa@143");
		 * MyMap1.put("productName", "IPHONE 13 PRO");
		 * 
		 * return new Object[][] {{MyMap},{MyMap1}};
		 */
		
		//For Same Above we can Pass data By JSON by using DataReaderClass
		DataReader DR=new DataReader();
		
		List<HashMap<String,String>> data =DR.getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//amrutraibagi//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
				
	}
	
	
	
	
}
