package utils;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenBrowser {

	static WebDriver driver=FrameworkVariables.driver;

	public static void openBrowser(String Browsername) {

		if(Browsername.toUpperCase().contains("CHROME")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("chrome launched");
		}
		else if(Browsername.toUpperCase().contains("EDGE")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("edge launched");
		}
		else if(Browsername.toUpperCase().contains("FIREFOX")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("firefox launched");
		}
		else {
			System.err.println("Choose a valid Browser");
		}

	}
	
	public static void openUrl(String url) {
		driver.get(url);
	}

	public static void quitBrowser() {
		driver.quit();
	}

	/*
	 * Mobile
	 */
	@SuppressWarnings("unchecked")
	public static AppiumDriver<WebElement> launchApplication() throws Exception{

			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability("platformName", "iOS");
			cap.setCapability("platformVersion", "15.3");
			cap.setCapability("deviceName", "UAT_RTA's iPhone");
			cap.setCapability("udid", "00008020-000508AC2269002E");
			cap.setCapability("automationName", "XCUITest");
			cap.setCapability("bundleId", "com.sbi.lotusintouch.internal");
			cap.setCapability("clearSystemFiles", true);
			URL url=new URL("http://0.0.0.0:4724/wd/hub");
			driver = new IOSDriver<WebElement>(url,cap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			return (AppiumDriver<WebElement>)driver;

	}
	


}
