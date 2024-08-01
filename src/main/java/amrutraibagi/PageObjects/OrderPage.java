package amrutraibagi.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amrutraibagi.AbstractComponents.AbsractComponents;


//Creating the PageObjectModel (POM) class i.e., page wise from the class StandAloneTest class which is in src/test/java

public class OrderPage extends AbsractComponents{
	//This driver doesn't has life to give life to this Local driver we need to create parameterized constructor 
	WebDriver driver;
	
	//This class object we need to create in StandAloneTest class and need to pass parameter driver to give life to driver in this current class
	//initialization steps are done in constructor class because it will be the first class to be executed
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver; //this keyword give life or store the driver value in Local class driver
		PageFactory.initElements(driver, this);//this points to current class element
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderProductsName;
	
	
	public boolean verifyOrdersDisplay(String Productname) {
		boolean cart=OrderProductsName.stream().anyMatch(Pcart->Pcart.getText().equalsIgnoreCase(Productname));
		System.out.println(cart);
		return cart;
	}
		
	

}
