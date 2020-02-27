package PageObjects;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPageMyAccount {
	public WebDriver driver;

	private static Logger objLgr=LogManager.getLogger(CommonPageMyAccount.class.getName());
	
	public CommonPageMyAccount(WebDriver driver) {
		this.driver=driver;
	}
	
	//X-path to navigate to Makeup category
	By addToBasket_button=By.xpath("./ancestor::div[@qa='product']/*//button[@qa='add']");
	

	//Method to get Add To Basket button	
	public WebElement get_addToBasket_button(WebElement element) {
		return isNestedElementFound(addToBasket_button, "Add To Basket Button", 90, element);
	}
		
	//Method to check if any element is present in the page and return the element if true else null
		public WebElement isElementFound (By element_Name, String elementName, int timeoutInSec) {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
			WebElement webElement = null;
			int attempts = 0;
			try {
				while (attempts <= 2) {
					objLgr.info("(isElementFound)  Attempt " + (attempts + 1) + ": " + elementName + " (" + element_Name);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(element_Name));
						webElement = driver.findElement(element_Name);
						break;
						//			objLgr.info(elementName + " found: " + element_Name );
						//		} catch (TimeoutException exp) {
						//			objLgr.info(elementName + " visibility wait timed out after "+timeoutInSec+" seconds. "+exp);
						//		} catch (NoSuchElementException noexp) {
						//			objLgr.info(elementName + " not found : "+noexp);
					} catch (StaleElementReferenceException stelexp) {
						objLgr.info(elementName + " not attached to page object : " + stelexp);
					}
					attempts++ ;
				}
			} catch (TimeoutException exp) {
				objLgr.info(elementName + "(" + element_Name + ") visibility wait timed out after "+timeoutInSec+" seconds. "+exp);
			} catch (NoSuchElementException noexp) {
				objLgr.info(elementName + " not found : " + noexp);
			} catch (Exception e){
				objLgr.info(elementName + " (" + element_Name + " ) Not Found. " + e);
			}
			return webElement;
		}
		//Method to check if any element is present in the page and return the element if true else null
		public List<WebElement> areElementsFound (By element_Name, String elementName, int timeoutInSec) {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
			List<WebElement> webElement = null;
			int attempts = 0;
			try{
				while(attempts < 2){
					objLgr.info("(areElementsFound) Attempt " + (attempts + 1) + ": " + elementName + " (" + element_Name);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(element_Name));
						webElement = driver.findElements(element_Name);
						break;
						//			objLgr.info(webElement.size() + " matching elements found for " + elementName + "(" + element_Name + ")");
						//			objLgr.info(elementName + " found.  (" + element_Name + ")");
					} catch (StaleElementReferenceException stelexp) {
						objLgr.info(elementName + " not attached to page object : "+stelexp);
					}
					attempts++;
				}
			} catch (TimeoutException exp) {
				objLgr.info(elementName + " visibility wait timed out after "+timeoutInSec+" seconds. "+exp);
			} catch (NoSuchElementException noexp) {
				objLgr.info(elementName + " not found : "+noexp);
			} catch (Exception e){
				objLgr.info(elementName + " (" + element_Name + " ) Not Found. " + e);
			}

			return webElement;
		}
		//Method to check if any element is present in any dependent element and return the element if true else null
		public WebElement isNestedElementFound (By element_Name, String elementName, int timeoutInSec, WebElement element) {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
			WebElement webElement = null;
			int attempts = 0;
			try {
				while (attempts < 2) {
					objLgr.info("(isNestedElementFound) Attempt " + (attempts + 1) + ": " + elementName + " (" + element_Name);
					try {
						wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, element_Name));
						webElement = element.findElement(element_Name);
						break;
						//			objLgr.info(elementName + " element nested under " + element + " element found.");
						//			} catch (TimeoutException exp) {
						//				objLgr.info(elementName + " visibility wait timed out after " + timeoutInSec + " seconds. " + exp);
						//			} catch (NoSuchElementException noexp) {
						//				objLgr.info(elementName + " not found : " + noexp);
					} catch (StaleElementReferenceException stelexp) {
						objLgr.info(elementName + " not attached to page object : " + stelexp);
					}
					attempts++;
				}
			} catch (TimeoutException exp) {
				objLgr.info(elementName + " visibility wait timed out after " + timeoutInSec + " seconds. " + exp);
			} catch (NoSuchElementException noexp) {
				objLgr.info(elementName + " not found : " + noexp);
			} catch (Exception e){
				objLgr.info(elementName + " (" + element_Name + " ) Not Found. " + e);
			}
			return webElement;
		}

}
