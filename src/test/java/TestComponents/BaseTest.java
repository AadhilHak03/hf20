package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.testrailmanager.TestRailManager;

import PageObjects.landingPage;

public class BaseTest {
	
	public WebDriver dr;
	public landingPage lp;
	protected String testCaseID;

	
	public WebDriver startDriver() throws IOException, URISyntaxException {
		Properties prop = new Properties();
		DesiredCapabilities dc = new DesiredCapabilities();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/GlobalData/BrowserSelection.properties");
		prop.load(fis);
		String browsers = "FireFox";
		
		String browserName= System.getProperty("browserName")!=null ? System.getProperty("browserName") : prop.getProperty(browsers);
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			
		} else if(browserName.equalsIgnoreCase("Firefox")) {
			
			dc.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		
		} else if (browserName.equalsIgnoreCase("Edge")) {
			
			dc.setCapability(CapabilityType.BROWSER_NAME, "edge");
		}
		
		URI uri = new URI("http://localhost:4444/wd/hub");
		URL url = uri.toURL();
		dr = new RemoteWebDriver(url, dc);		
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));		
		
		return dr;
	}
	
	public String getScreenShot(String testCaseName, WebDriver dr) throws IOException {
		TakesScreenshot src = (TakesScreenshot)dr;
		File src1 = src.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(src1, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
		
	}
	
	
	@BeforeTest(alwaysRun=true)
	public landingPage getPage() throws IOException, URISyntaxException
	{
		dr = startDriver();
		lp = new landingPage(dr);
		lp.goTo();
		return lp;
	}
	
	/*@AfterMethod(alwaysRun=true)
	public void addResultsToTestRail(ITestResult result) {
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			TestRailManager.addResultsForTestcase(testCaseID, TestRailManager.TEST_CASE_PASS_STATUS, "");
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			TestRailManager.addResultsForTestcase(testCaseID, TestRailManager.TEST_CASE_FAIL_STATUS, "test was failed..."
		+ result.getName() + " :FAILED");
		}
		
	}*/
	
	@AfterTest
	public void quit() {
		dr.quit();
	}
	
}
