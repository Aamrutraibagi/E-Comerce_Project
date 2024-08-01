package amrutraibagi.TestDataDriven;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;

public class TestDataForExcel {

	public static void main(String[] args) throws IOException {
		DriveExcelData DED=new DriveExcelData();
		ArrayList data=DED.getData("Add Profile");
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		//Example for using the Excel Data in Selenium
		//driver.findElement(By.xpath(null)).sendKeys(data.get(1));
	}

}
