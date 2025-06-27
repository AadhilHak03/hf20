package PageObjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
		handles = social.findElements(By.tagName("a"));
		
	}
	
	@FindBy(id="add-to-cart-sauce-labs-bike-light")
	WebElement bikeLight;

	@Test
	public void addProduct() {
		bikeLight.click();
	}
	
	@Test
	public void scrollDown() {
		scroll("window.scrollBy(0, document.body.scrollHeight);");
	}
	
	@FindBy(css=".social")
	WebElement social;
	List<WebElement> handles;
	
	@Test
	public void openLinks() {
		for(int i=0;i<handles.size();i++)
		{
			
			social.findElements(By.tagName("a")).get(i).sendKeys(Keys.CONTROL,Keys.ENTER);
			
		}
	}
	
	@Test 
	public void goIntoHandles() {
		getWindowHandle(dr.getCurrentUrl());

		
	}
	
		
}
