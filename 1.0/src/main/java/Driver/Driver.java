package Driver;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.io.FileUtils;

import com.mashape.unirest.http.exceptions.UnirestException;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.*;

public class Driver extends ExtentReporterNG{
	private static Logger objLgr = LogManager.getLogger(Driver.class.getName());
	public static WebDriver webdriver;
	public static Properties prop;
	public static FileInputStream fisPropfile;
	protected String propertyFileLocation = "src/main/java/Driver/AutomationParameters.properties";
	public static String platform;
	public static String browser;
	public ChromeOptions chromeOptions;
	public static Actions actn;
	
	public void getAutomationProperties() throws IOException {

		prop=new Properties();
		fisPropfile = new FileInputStream(propertyFileLocation);
		if (fisPropfile == null)
			objLgr.error("Test Execution begins");
		else
			objLgr.info("Property file " + propertyFileLocation + " read");
		prop.load(fisPropfile);
		platform = prop.getProperty("Platform");
		browser = prop.getProperty("Browser");
	}
	
	public WebDriver initialize() throws IOException {
		objLgr.info("Initializing webdriver");
		getAutomationProperties();
		if(prop.getProperty("Browser").equalsIgnoreCase("Chrome")) {	
			try {
				chromedriver();		
			} catch(Exception e) {
				objLgr.error("Failed to launch " + prop.getProperty("Browser") + " browser.");
			}
		}
		return webdriver;
	}
	//Method to initialize Chrome Driver
		@SuppressWarnings("deprecation")
		public void chromedriver(){
			if (platform.equals("Windows"))
				System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverLocation"));
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			webdriver= new ChromeDriver(caps);
		}
		//Method to get Screenshot
		public void getScreenshot(String result) throws IOException, UnirestException {
				objLgr.info("Screenshot location " + prop.getProperty("ScreenshotLocation")+result+prop.getProperty("Screenshot")+"screenshot.png");
				if(webdriver == null) {
					objLgr.info("Webdriver is null.  Cannot take screenshot");
				}
				else {
					File src=((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
					try{
						FileUtils.copyFile(src, new File(prop.getProperty("ScreenshotLocation")+result+prop.getProperty("Screenshot")+"screenshot.png"));
					} catch (Exception e){
						objLgr.info("Screenshoting failed!!! " + e);
					}

				}
		}

}
