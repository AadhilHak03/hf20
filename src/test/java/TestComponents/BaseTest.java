package TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import PageObjects.landingPage;

public class BaseTest {
	
	public WebDriver dr;
	public landingPage lp;
	
	public WebDriver startDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/GlobalData/BrowserSelection.properties");
		prop.load(fis);
		String browser = "FireFox";
		
		String browserName= System.getProperty("browserName")!=null ? System.getProperty("browserName") : prop.getProperty(browser);
		
		if(browserName.contains("Chrome")) {
		dr = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("FireFox")) {
			dr = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("Edge")) {
			dr = new EdgeDriver();
		}
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		return dr;
	}
	
	@BeforeMethod(alwaysRun=true)
	public landingPage getPage() throws IOException
	{
		dr = startDriver();
		lp = new landingPage(dr);
		lp.goTo();
		return lp;
	}
	
}
