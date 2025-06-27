package AbstractComponents;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.JavascriptExecutor;
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

	/*public void impWait() {
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}*/
	
	public void scroll(String jss) {
		JavascriptExecutor js = (JavascriptExecutor)dr;
		js.executeScript(jss);
		
	}
	
	public void getWindowHandle(Object command) {
		Set<String> windows =dr.getWindowHandles();
		Iterator<String> it = windows.iterator();

		while(it.hasNext())
		{
			dr.switchTo().window(it.next());
			System.out.println(command);
		}
		
	}
	
	
}
