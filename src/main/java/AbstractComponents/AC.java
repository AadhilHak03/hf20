package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import PageObjects.CartPage;

public class AC {
	public WebDriver dr;
	public AC(WebDriver dr) {
		this.dr=dr;
		PageFactory.initElements(dr, this);
	}
	
	
	
	@Test
	public void webVis(WebElement webLoc) {
		WebDriverWait wait = new WebDriverWait(dr, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOf(webLoc));

	}
	
	@FindBy(css=".shopping_cart_link")
	WebElement cartButton;
	
	@FindBy(css="#checkout")
	public WebElement checkoutButton;

	@Test 
	public CartPage goToCart() {
		cartButton.click();
		return new CartPage(dr);
	}

}
