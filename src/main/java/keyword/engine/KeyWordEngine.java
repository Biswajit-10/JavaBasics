package keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import keyword.base.Base;
import utils.BaseFunction;

/**
 * 
 * @author Biswajit
 *
 */
public class KeyWordEngine extends BaseFunction{

	public static WebDriver driver;
	public Properties prop;

	public static Workbook book;
	public static Sheet sheet;

	public Base base;
	public WebElement element;
	String stepNo;

	public final String SCENARIO_SHEET_PATH = "./src/main/java/keyword/scenarios/hubspot_scenarios.xlsx";
//	public final String SCENARIO_SHEET_PATH = "./src/main/java/com/qa/hs/keyword/scenarios/scenarios.csv";

	public void startExecution(String sheetName) {
		
		initFile(sheetName);
		
		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {

				String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();
				stepNo = sheet.getRow(i + 1).getCell(k + 5).toString().trim();

				switch (action) {
				case "open browser":
					base = new Base();
					prop = base.init_properties();					/* Initialise Properties in config.properties */
					if (value.isEmpty() || value.equals("NA")) {
						driver = base.init_driver(prop.getProperty("browser"));
					} else {
						driver = base.init_driver(value);
					}
					takeSnapShot(driver, "./Screenshots/"+stepNo+".png");
					break;

				case "enter url":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("url"));
					} else {
						driver.get(value);
					}
					takeSnapShot(driver, "./Screenshots/"+stepNo+".png");
					break;
			
				case "verifyTitle":
					if(driver.getTitle().equals(value)){
						System.out.println("Title Matched");
					}else {
						Assert.fail("Title doesn't match");
					}
					takeSnapShot(driver, "./Screenshots/"+stepNo+".png");
					break;

				case "quit":
					driver.quit();
					break;
					
				default:
					break;
				}

				switch (locatorType) {
				case "id":
					element = driver.findElement(By.id(locatorValue));
					actions(action,value,locatorType);
					break;

				case "name":
					element = driver.findElement(By.name(locatorValue));
					actions(action,value,locatorType);
					break;

				case "xpath":
					element = driver.findElement(By.xpath(locatorValue));
//					actions(action,value,locatorType);
					jsClick(element);
					break;

				case "cssSelector":
					element = driver.findElement(By.cssSelector(locatorValue));
					actions(action,value,locatorType);
					break;

				case "className":
					element = driver.findElement(By.className(locatorValue));
					actions(action,value,locatorType);
					break;

				case "linkText":
					element = driver.findElement(By.linkText(locatorValue));
					actions(action,value,locatorType);
					break;

				case "partialLinkText":
					element = driver.findElement(By.partialLinkText(locatorValue));
					actions(action,value,locatorType);
					break;

				default:
					break;
				}

			} catch (Exception e) {

			}

		}

	}

	public void actions(String action,String value,String locatorType) {
		
		if (action.equalsIgnoreCase("sendkeys")) {
			sendKeys(element,value);
		} else if (action.equalsIgnoreCase("click")) {
			click(element);
		} else if (action.equalsIgnoreCase("isDisplayed")) {
			isDisplayed(element);
		} else if (action.equalsIgnoreCase("getText")) {
			String elementText = element.getText();
			System.out.println("text from element : " + elementText);
		}
		/*	Taking screenshot  */
		takeSnapShot(driver, "./Screenshots/"+stepNo+".png");
		locatorType = null;
	}
	
	public void initFile(String sheetName) {
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
			System.out.println("Scenario path successfully found");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Scenario path not found");
		}

		try {
			book = WorkbookFactory.create(file);
			System.out.println("workbook factory created");
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			System.out.println("workbook factory creation failed");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("workbook factory creation failed");
		}
		try {
		sheet = book.getSheet(sheetName);
		System.out.println("Given Test Case Sheet found");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Given Test Case Sheet not found");
		}
	}
}
