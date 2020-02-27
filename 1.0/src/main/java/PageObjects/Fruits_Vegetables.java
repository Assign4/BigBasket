package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Fruits_Vegetables extends CommonPageMyAccount {
	public Fruits_Vegetables(WebDriver driver) {
		super(driver);
	}
	
	//X-path to navigate to fresh Vegetables
		By veggies=By.xpath("//img[contains(@src,'All_Fresh-veggies')]");
		//X-path to string value to fresh Vegetables
				By veggies_count=By.xpath("//span[text()='Fresh Vegetables' and @class='ng-binding']/following-sibling::span");//div[@class='Tips' and text()='${nbsp}']
		//X-path to navigate to fresh Vegetables
				By showMore_Button=By.xpath("//button[contains(text(),'Show More')]");
		//X-path to get count of fresh Vegetables
				By count=By.xpath("//div[@qa='product']");
				By packSize = By.xpath("//span[contains(text(),'Pack Size')]");
				
		
		//Method to get Shop By Category drop down option button
		public WebElement get_veggies() {
			return isElementFound(veggies, "Fresh veggies", 90);
		}
		public WebElement get_packSize() {
			return isElementFound(packSize, "Pack Size Locator", 90);
		}
		//Method to get show more button
		public WebElement get_showMore_Button() {
			return isElementFound(showMore_Button, "Show More Button", 10);
		}
		//Method to get Shop By Category drop down option button
		public WebElement get_veggies_count() {
			return isElementFound(veggies_count, "Fresh veggies count", 90);
		}
		//Method to get count	
		public List<WebElement> get_count() {
			return areElementsFound(count, "Product Count" , 90);
		}

}
