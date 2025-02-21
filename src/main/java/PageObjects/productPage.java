package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AC;

	public class productPage extends AC{
	WebDriver dr;
	public productPage(WebDriver dr)
	{
		super(dr);
		this.dr=dr;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(id="add-to-cart-sauce-labs-bike-light")
	WebElement bikeLight;

	@Test
	public void addProduct() {
		bikeLight.click();
	}
	
		
}
