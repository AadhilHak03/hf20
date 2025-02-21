package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import AbstractComponents.AC;

public class CheckoutPage extends AC{

	public CheckoutPage(WebDriver dr) {
		super(dr);
		
	}

	@FindBy(css="#finish")
	WebElement finishButton;
	
	@Test
	public ConfirmationPage goToConfirmationPage() {
		webVis(finishButton);
		finishButton.click();
		return new ConfirmationPage(dr);
	}
			
	
	
	
}
