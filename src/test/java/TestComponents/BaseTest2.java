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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.testrailmanager.TestRailManager;

import PageObjects.landingPage;

public class BaseTest2 {

    public WebDriver dr;
    public landingPage lp;
    protected String testCaseID;

    public WebDriver startDriver() throws IOException, URISyntaxException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "/src/main/java/GlobalData/BrowserSelection.properties");
        prop.load(fis);
        String browsers = "FireFox";

        String browserName = System.getProperty("browserName") != null
                ? System.getProperty("browserName")
                : prop.getProperty(browsers);

        URL gridUrl = new URI("http://selenium-hub:4444/wd/hub").toURL();

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                dr = new RemoteWebDriver(gridUrl, chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                dr = new RemoteWebDriver(gridUrl, firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                dr = new RemoteWebDriver(gridUrl, edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        dr.manage().window().maximize();

        return dr;
    }

    public String getScreenShot(String testCaseName, WebDriver dr) throws IOException {
        TakesScreenshot src = (TakesScreenshot) dr;
        File srcFile = src.getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
        FileUtils.copyFile(srcFile, destFile);
        return destFile.getAbsolutePath();
    }

    @BeforeTest(alwaysRun = true)
    public landingPage getPage() throws IOException, URISyntaxException {
        dr = startDriver();
        lp = new landingPage(dr);
        lp.goTo();
        return lp;
    }

    /*@AfterMethod(alwaysRun = true)
    public void addResultsToTestRail(ITestResult result) {
        if (testCaseID == null || testCaseID.isEmpty()) return;

        if (result.getStatus() == ITestResult.SUCCESS) {
            TestRailManager.addResultsForTestcase(testCaseID, TestRailManager.TEST_CASE_PASS_STATUS, "");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String message = "Test case failed: " + result.getName();
            TestRailManager.addResultsForTestcase(testCaseID, TestRailManager.TEST_CASE_FAIL_STATUS, message);
        }
    }*/

    @AfterTest(alwaysRun = true)
    public void quit() {
        if (dr != null) {
            dr.quit();
        }
    }
}
