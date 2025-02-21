package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AC;

public class landingPage extends AC {
	
	WebDriver dr;
	 public landingPage(WebDriver dr)
	 {	super(dr);
		 this.dr=dr;
		 PageFactory.initElements(dr, this);
	 }
	 
	 
	public void goTo()
	{
		dr.get("https://www.saucedemo.com/");
	}
	
	
	@FindBy(id="user-name")
	WebElement user;
	@FindBy(id="password")
	WebElement pass;
	@FindBy(id="login-button")
	WebElement login;
	
	public productPage loginCode() {
		user.sendKeys("standard_user");
		pass.sendKeys("secret_sauce");
		login.click();
		return new productPage(dr);
	}
	
	
	
	
	
	
	
	
}
