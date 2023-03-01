package keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author NaveenKhunteta
 *
 */
public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browserName){
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
//			if(prop.getProperty("headless").equals("yes")){
//				/*headless mode*/
//				ChromeOptions options = new ChromeOptions();
//				options.addArguments("--headless");
//				driver = new ChromeDriver(options);
//			}else{
			WebDriverManager.chromedriver().setup();
			 driver=new ChromeDriver();
			 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			}
//		} else if(browserName.equals("firefox")){
//			System.setProperty("webdriver.gecko.driver", "/Users/NaveenKhunteta/Downloads/geckodriver");
//			driver = new FirefoxDriver();
//		}
		return driver;
	}
	
	public Properties init_properties(){
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/keyword/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	
	

}
