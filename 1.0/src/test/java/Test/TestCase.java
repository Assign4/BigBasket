package Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import Util.ReusableFunctions;
import PageObjects.CommonPageMyAccount;
import PageObjects.Fruits_Vegetables;
import PageObjects.Beauty_Hygiene;
import PageObjects.HomePage;


import Driver.Driver;

public class TestCase extends Driver{
	private static Logger objLgr=LogManager.getLogger(Test.class.getName());
	public CommonPageMyAccount commonPageMyAccount;
	public Beauty_Hygiene bandh;
	public HomePage homepage;
	public Fruits_Vegetables fandv;
	String testCase_No, url;
	public Boolean isTestCasePassed;
	public JavascriptExecutor js;
	
	public TestCase(Hashtable<String, String> input) {
		this.testCase_No=input.get("TC_No");
		this.url=input.get("URL");
	}
	
	@BeforeClass
	public void launchMyAccountHome() throws JSONException, Exception {
		webdriver=initialize();
		objLgr.info("Web driver initializing complete");
		objLgr.info("webdriver/remoteWebDriver Initialized");
		webdriver.manage().deleteAllCookies();
		webdriver.manage().window().maximize();
		commonPageMyAccount = new CommonPageMyAccount(webdriver);
		bandh = new Beauty_Hygiene(webdriver);
		homepage = new HomePage(webdriver);
		actn=new Actions(webdriver);
		fandv = new Fruits_Vegetables(webdriver);
		js = ((JavascriptExecutor) webdriver);
		webdriver.get(url); 
	}
	
	@Test (enabled=true, description = "Add an item to the cart.", priority=1)
	public void addItem() throws Exception {
		js.executeScript("arguments[0].scrollIntoView();",bandh.get_makeup_category());
		bandh.get_makeup_category().click();
		js.executeScript("arguments[0].scrollIntoView();",bandh.get_Product1());
		commonPageMyAccount.get_addToBasket_button(bandh.get_Product1()).click();
		for(WebElement button : homepage.get_continue_locator()) {
			if(button.isDisplayed()) {
				button.click();
			    break;
			}
		}
		Thread.sleep(1000);
		String Actual_count = homepage.get_basket_count().getText().toString();
		String expected_count = "1 items";
		Assert.assertEquals(Actual_count, expected_count);
		objLgr.info("The item is added and My basket count increased to 1");
	}
	
	@Test (enabled=true, description = "Add multiple item to the cart.", priority=2)
	public void add_MultipleItems() throws Exception {
		disposeWebdrive();
		launchMyAccountHome();
		int count = 0;
		js.executeScript("arguments[0].scrollIntoView();",fandv.get_veggies());
		fandv.get_veggies().click();
		String veggies_count = fandv.get_veggies_count().getText().toString();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		WebElement showmore_button = null;
		do
		{
			showmore_button = fandv.get_showMore_Button();
			if(count>=120)
				if(showmore_button!=null) 
					actn.moveToElement(showmore_button).click().perform();
	        if(count<120)
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
		}while(showmore_button!=null);
		count = fandv.get_count().size();
		String actual_value = String.valueOf(count);
		String expected_value = veggies_count.replace("(", "").replace(")","");
		Assert.assertEquals(expected_value, actual_value);
	}
	
	@AfterClass
	public void disposeWebdrive() {
		objLgr.info("Closing browser.");
		webdriver.close();
		if(webdriver == null){
			objLgr.info("Webdriver is null");
		}
		objLgr.info("Disposing webdriver/removeWebdriver.");
		webdriver.quit();
	}
			
		
	
	

}
