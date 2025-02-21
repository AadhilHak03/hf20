package TestComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import PageObjects.landingPage;

public class BaseTest {
	
	public WebDriver dr;
	public landingPage lp;
	
	public WebDriver startDriver() {
		dr = new ChromeDriver();
		return dr;
	}
	
	@BeforeMethod
	public landingPage getPage()
	{
		dr = startDriver();
		lp = new landingPage(dr);
		lp.goTo();
		return lp;
	}
	
}
