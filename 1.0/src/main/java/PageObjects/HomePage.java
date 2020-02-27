package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends CommonPageMyAccount {
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//X-path to Shop By Category drop down option
	By drop_down_Option=By.xpath("//a[contains(text(),'Shop by Category')]//i");
	//X-path to get basket count
	By basket_count=By.xpath("//a[contains(@ng-click,'Basket')]//span[contains(text(),'My Basket')]//span");
	//X-path to beauty & Hygiene locator
	By beauty_hygiene=By.xpath("//a[contains(text(),'Beauty & Hygiene')]");
	//X-path to Continue locator
	By continue_locator=By.xpath("//a[@qa='continueBtn']");
	
	//Method to get Shop By Category drop down option button
	public WebElement get_drop_down_Option() {
		return isElementFound(drop_down_Option, "Shop By Category drop down option button", 90);
	}
	//Method to get Shop By Category drop down option button
		public WebElement get_basket_count() {
			return isElementFound(basket_count, "Basket count", 90);
		}

		//Method to get beauty & Hygiene locator	
		public List<WebElement> get_beauty_hygiene() {
			return areElementsFound(beauty_hygiene, "Beauty & Hygiene" , 90);
		}
		
		//Method to get Continue locator	
				public List<WebElement> get_continue_locator() {
					return areElementsFound(continue_locator, "Continue Locator" , 90);
				}

}
