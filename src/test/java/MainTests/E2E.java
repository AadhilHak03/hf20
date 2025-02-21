package MainTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2E {
	@Test
	public void E1() throws InterruptedException
	{
		//1
		WebDriver dr = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(dr, Duration.ofSeconds(4));
		//2
		dr.get("https://www.saucedemo.com/");
		dr.findElement(By.id("user-name")).sendKeys("standard_user");
		dr.findElement(By.id("password")).sendKeys("secret_sauce");
		dr.findElement(By.id("login-button")).click();
		//3
		dr.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
	
		dr.findElement(By.cssSelector(".shopping_cart_link")).click();
		//4
		wait.until(ExpectedConditions.visibilityOf(dr.findElement(By.cssSelector("#checkout"))));
		
		List<WebElement> cnum = dr.findElements(By.cssSelector(".cart_item"));
		for(int i =0; i<cnum.size();i++)
		{
			String snum = 	dr.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']")).getText();
			if(snum.equalsIgnoreCase("Sauce Labs Bike Light"))
				{	
				System.out.println(snum);
				dr.findElement(By.id("checkout")).click();			
				}
		}
		//5
	dr.findElement(By.id("first-name")).sendKeys("aaa");
	dr.findElement(By.id("last-name")).sendKeys("bbb");
	dr.findElement(By.cssSelector("#postal-code")).sendKeys("11111");
	dr.findElement(By.cssSelector("#continue")).click();
	//6
	wait.until(ExpectedConditions.visibilityOf(dr.findElement(By.cssSelector("#finish"))));
	dr.findElement(By.cssSelector("#finish")).click();
	//7
	wait.until(ExpectedConditions.visibilityOf(dr.findElement(By.cssSelector(".complete-header"))));
	String cm = dr.findElement(By.cssSelector(".complete-header")).getText();
	if(cm.equalsIgnoreCase("Thank you for your order!"))
	{
		dr.findElement(By.cssSelector("#back-to-products")).click();
	}
	//8
	
	wait.until(ExpectedConditions.visibilityOf(dr.findElement(By.cssSelector(".app_logo"))));
	String ap = dr.findElement(By.cssSelector(".app_logo")).getText();
	Assert.assertEquals(ap, "Swag Labs");
	
	
	
	
	
	}
}
