package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.testrailmanager.TestRailManager;

import PageObjects.landingPage;

public class BaseTest {
	
	public WebDriver dr;
	public landingPage lp;
	protected String testCaseID;

	
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
	
	public String getScreenShot(String testCaseName, WebDriver dr) throws IOException {
		TakesScreenshot src = (TakesScreenshot)dr;
		File src1 = src.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(src1, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
		
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public landingPage getPage() throws IOException
	{
		dr = startDriver();
		lp = new landingPage(dr);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
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
		
	}
	
}
