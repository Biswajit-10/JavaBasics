package utils;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import keyword.engine.KeyWordEngine;

public class BaseFunction {

	static WebDriver driver=KeyWordEngine.driver;
	
	public void waitForVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(KeyWordEngine.driver,30);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch (Exception e) {
			writeConsoleLog("element is not visible");
		}
	}

	public WebElement findElement(By by) {
		return KeyWordEngine.driver.findElement(by);
	}
	
	public WebElement find(By by,String locatorType,String LocatorValue) {
		WebElement element=null;
		try {
			element = driver.findElement(by);
			System.out.println("element is found using "+locatorType+" and"+LocatorValue);
		}catch (Exception e) {
			System.err.println("element is not found using "+locatorType+" and"+LocatorValue);
		}
		return element;
	}
	
	public WebElement findElement(String locatorType,String LocatorValue) {
	
		switch(locatorType) {

		case "XPATH":
			System.out.println("Searchig element by XPATH");
			By by = By.xpath(LocatorValue);
			return find(by,locatorType,LocatorValue);

		case "ID":
			System.out.println("Searchig element by ID");
			By by1 = By.id(LocatorValue);
			return find(by1,locatorType,LocatorValue);

		case "CLASSNAME":
			System.out.println("Searchig element by CLASSNAME");
			By by2 = By.className(LocatorValue);
			return find(by2,locatorType,LocatorValue);
	
		case "NAME":
			System.out.println("Searchig element by NAME");
			By by3 = By.name(LocatorValue);
			return find(by3,locatorType,LocatorValue);

		case "LINKTEXT":
			System.out.println("Searchig element by LINKTEXT");
			By by4 = By.linkText(LocatorValue);
			return find(by4,locatorType,LocatorValue);

		case "CSSSLECTOR":
			System.out.println("Searchig element by CSSSLECTOR");
			By by5 = By.linkText(LocatorValue);
			return find(by5,locatorType,LocatorValue);

		default:
			System.out.println("Choose a Valid Selector");
			break;
		}
		return null;
	}
	
	public void jsClick(WebElement element) {
		 JavascriptExecutor executor = (JavascriptExecutor) KeyWordEngine.driver;
		    executor.executeScript("arguments[0].click();", element);
	}
	
	public void jsClick2(List<WebElement> element) {
		JavascriptExecutor executor = (JavascriptExecutor) KeyWordEngine.driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void click(WebElement element) {
		waitForVisibility(element);
		String elementName = element.getText();
		try {
			element.click();
			writeConsoleLog(elementName + " is clicked");
		} catch (Exception e) {
			writeConsoleLog(elementName + " unable to click");
		}
	}

	public void sendKeys(WebElement element,String data,String fieldName) {
		try {
			clear(element);
			element.sendKeys(data);
			writeConsoleLog(data + " is sent to " + fieldName + " text field ");
		} catch (Exception e) {
			writeConsoleLog("send Keys " + data + " in " + fieldName + " is failed");
		}
	}

	public void sendKeys(WebElement element,String data) {
		String fieldName=null;
		try {
			clear(element);
			fieldName=element.getAttribute("placeholder");
			if(fieldName==null) {
				fieldName=element.getAttribute("aria-label");
			}
			element.sendKeys(data);
			writeConsoleLog(data + " is sent to " + fieldName + " text field ");
		} catch (Exception e) {
			writeConsoleLog("send Keys " + data + " in " + fieldName + " is failed");
		}
	}

	public void clear(WebElement element) {
		try {
			element.clear();
			writeConsoleLog("text field is cleared");
		} catch (Exception e) {
			writeConsoleLog("clearing text filed failed");
		}
	}

	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public void writeConsoleLog(String log) {
		LogManager.getLogger().info(log);
	}


	public static void takeSnapShot(WebDriver webdriver,String fileNameWithPath){

		TakesScreenshot scrShot =((TakesScreenshot)webdriver);

		//Call getScreenshotAs method to create image file

		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination

		File DestFile=new File(fileNameWithPath);

		//Copy file at destination

		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void validate(Object expected, Object actual, String assertionFor) {

//		try {
//			logFile(assertionFor,actual, expected);
//			Assert.assertEquals(actual, expected, assertionFor);
//			ReporterClass.pass(ASSERTION_FOR + " - <b> <u>" + assertionFor
//					+ "</u> </b>   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,
//					true);
//			LogManager.getLogger().info(ASSERTION_FOR + " - " + assertionFor
//					+ "  |   Actual: " + actual + " and  Expected: " + expected,
//					true);
//		} catch (AssertionError assertionError) {
//			ReporterClass.fail(ASSERTION_FOR + " - <b> <u>" + assertionFor
//					+ "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,true);
//			LogManager.getLogger().info(ASSERTION_FOR + " - " + assertionFor
//					+ "   |   Actual: " + actual + " and  Expected: " + expected,true);
//			BaseTest.LogOutTest();	/* LogOut First then assertion fail because after that @AfterMethod running */
//			Assert.fail(assertionFor);
//		}
	}
	
	
	
}
