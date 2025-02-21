package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AC;

public class InfoPage extends AC{

	public InfoPage(WebDriver dr) {
		super(dr);
		this.dr=dr;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(id="first-name")
	WebElement fs;
	String fs1 = "aaa";
	
	@FindBy(id="last-name")
	WebElement ls;
	String ls1 = "bbb";
	
	@FindBy(css="#postal-code")
	WebElement zButton;
	String zipCode1 = "11111";
	
	@FindBy(css="#continue")
	WebElement cButton;
	
	@Test
	public CheckoutPage goToCheckout() {
		fs.sendKeys(fs1);
		ls.sendKeys(ls1);
		zButton.sendKeys(zipCode1);
		cButton.click();
		
		return new CheckoutPage(dr);
	
	
	}
	
}
