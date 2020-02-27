package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Beauty_Hygiene extends CommonPageMyAccount {
	public Beauty_Hygiene(WebDriver driver) {
		super(driver);
	}
	
	//X-path to navigate to Makeup category
	By makeup_category=By.xpath("//img[contains(@src,'Makeup')]");
	//X-path to navigate to Nails category
	By nails_category=By.xpath("//span[text()='Nails']");
	//X-path to select nail color remover
	By product1=By.xpath("//img[contains(@src,'lakme-nail-colour-remover')]");
	
	//Method to get Shop By Category drop down option button
	public WebElement get_makeup_category() {
		return isElementFound(makeup_category, "Makeup Category", 90);
	}
	//Method to get nails category	
	public List<WebElement> get_nails_category() {
		return areElementsFound(nails_category, "Header Row" , 90);
	}
	//Method to get product1	
	public WebElement get_Product1() {
		return isElementFound(product1, "Lakme Nail remover", 90);
	}

}
