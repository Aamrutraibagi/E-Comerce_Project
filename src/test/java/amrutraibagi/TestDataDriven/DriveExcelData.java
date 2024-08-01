package amrutraibagi.TestDataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class DriveExcelData {
	
	public ArrayList<String> getData(String TestCase) throws IOException {
		ArrayList arr=new ArrayList();
		//Appache POI API
	//Create an Object for XSSFWorkBook class
	//Get an Access to sheet 
	//Get the Access to all the Rows of Sheet
	//Get the Access to Specific row 1st row from all row
	//Get the Access to each and every Cell of Row
	//And Access the to the Data you required
	
	FileInputStream fis=new FileInputStream("C:\\Users\\admin\\Documents\\TestExcelData.xlsx");
	XSSFWorkbook workbook=new XSSFWorkbook(fis);
	int sheets=workbook.getNumberOfSheets();
	for(int i=0;i<sheets;i++) {
		if(workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
			XSSFSheet sheet=workbook.getSheetAt(i);
			
			//1. Identify TestCase Column by the entire 1st row
			Iterator<Row> rows=sheet.iterator();//sheet is collection of rows
				Row firstRow=rows.next();//row is collection of cells
				Iterator<Cell> cell=firstRow.cellIterator();
				int k=0;
				int column=0;
				while(cell.hasNext()) {
					Cell value=cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						//desired column
						column=k;
					}
					k++;
				}
				System.out.println(column);
				//2.Once Column is identified then scan entire test case column to identify purchase test case row
				while(rows.hasNext()) {
					Row row=rows.next();
					if(row.getCell(column).getStringCellValue().equalsIgnoreCase(TestCase)) {
						//3. After you grab purchase test case row pull all the data of that row and feed it to the test
						Iterator<Cell> cv=row.cellIterator();
						while(cv.hasNext()) {
							Cell c=cv.next();
							switch(c.getCellType()) {
							case STRING:
								arr.add(c.getStringCellValue());
								break;
							case NUMERIC:
								arr.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								break;
								
							}
							
						}
					}
				}
			}
		}
				return arr;
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
	}
	

}
