package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AC;

public class CartPage extends AC {
	WebDriver dr;
	public CartPage(WebDriver dr) {
		
		super(dr);
		this.dr=dr;
		PageFactory.initElements(dr, this);
	}
	
	
	@FindBy(css=".cart_item")
	List<WebElement> cnum;
	
	@FindBy(xpath="//div[text()='Sauce Labs Bike Light']")
	WebElement snum;
	
	 public String item = "Sauce Labs Bike Light" ;
	
	@Test
	public InfoPage goToInfoPage() {
		webVis(checkoutButton);
		
		for(int i =0; i<cnum.size();i++)
		{
			if(snum.getText().equalsIgnoreCase(item))
				{	
				System.out.println(snum.getText());
				checkoutButton.click();
				}

		}
	
	return new InfoPage(dr);

	}
	
	
	
	
}
