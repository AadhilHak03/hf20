package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AC;

public class ConfirmationPage extends AC{

	public ConfirmationPage(WebDriver dr) {
		super(dr);
		this.dr=dr;
		PageFactory.initElements(dr, this.dr);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css=".complete-header")
	WebElement header;
	
	 //public String hs;
	@Test
	public String validate() {
		webVis(header);
		String hs = header.getText();
		return hs;
	}
	
	@FindBy(css="#back-to-products")
	WebElement bhb;
	
	@Test 
	public void backHome() {
		ConfirmationPage con = new ConfirmationPage(dr);
		String hs = con.validate();
		if(hs.equalsIgnoreCase("Thank you for your order!"))
		{
			bhb.click();
		}
	}
		
	
	
	
	
	
	
	
	
	
}
