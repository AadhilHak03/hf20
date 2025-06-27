package MainTests;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v137.emulation.Emulation;
import org.openqa.selenium.devtools.v137.network.Network;
import org.openqa.selenium.devtools.v137.network.model.ConnectionType;
import org.openqa.selenium.devtools.v137.network.model.Request;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

import PageObjects.landingPage;

public class Mocking {
	ChromeOptions options;
	ChromeDriver driver;
	DevTools devTools;
	//WebDriver driver;
	
	@BeforeTest
	public DevTools devToolsCreation() throws MalformedURLException {
		 // Setup ChromeDriver
        options = new ChromeOptions();
        driver = new ChromeDriver(options);

        // Start DevTools session
         devTools = driver.getDevTools();
        devTools.createSession();
		return devTools;
	}
	
	@Test
	public void letssee() throws InterruptedException, SQLException {
       // Define your desired location
      
       double latitude =  40.436269;    // New York City
       double longitude = -3.784605;
       double accuracy = 1;
       // Send geolocation override
       devTools.send(Emulation.setGeolocationOverride(
               Optional.of(latitude),
               Optional.of(longitude),
               Optional.of(accuracy), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()
       ));
      
       devTools.send(Emulation.setDeviceMetricsOverride(500, 1000, 35, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()
       		, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
      
      
       devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
      
       devTools.addListener(Network.requestWillBeSent(), req -> {
       	Request req1 = req.getRequest();
       	System.out.println(req1.getUrl());
       });
       devTools.addListener(Network.responseReceived(), resp -> {
       	org.openqa.selenium.devtools.v137.network.model.Response res = resp.getResponse();
       	System.out.println(res.getStatus());
       	if(res.getStatus().toString().startsWith("4"))
       	{
       		System.out.println(res.getUrl() + " failed status code " + res.getStatus());
       	}
       });
      
       devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg*", "*.css*")));
      
      
       devTools.send(Network.emulateNetworkConditions(Boolean.valueOf(false), Integer.valueOf(5000), Integer.valueOf(10000000), Integer.valueOf(10000000), Optional.of(ConnectionType.ETHERNET), Optional.empty(), Optional.empty(),
       Optional.empty()));
      
      LogEntries entries =  driver.manage().logs().get(LogType.BROWSER);
       List<org.openqa.selenium.logging.LogEntry> log = entries.getAll();
       for(org.openqa.selenium.logging.LogEntry b : log) {
       	System.out.println(b.getMessage());
       }
      
      
       // Open a site that uses geolocation
       landingPage lp = new landingPage(driver);
		lp.goTo();
		lp.loginCode();
		
		/*driver.navigate().to("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Netflix", Keys.ENTER);*/
		
       // Keep browser open for a few seconds
       try {
           Thread.sleep(10000); // 10 sec
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
	}

	
	
	
	
	
	
	/*@Test
	public void sqlConn() throws SQLException {
		String host = "localhost";
		String port = "3306";
		
		Connection con = DriverManager.getConnection("jdbc:mysql://" +host+ ":" +port+ "/DB1", "root", "Meliodas99@!");
		
		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery("select * from UserInfo where Username = 'standard_user';");
		while(rs.next())
		{
			WebDriver dr = (WebDriver) new ChromeDriver();
			dr.get("https://www.saucedemo.com/");
			
			dr.findElement(By.cssSelector("#user-name")).sendKeys(rs.getString("Username"));
			dr.findElement(By.cssSelector("#password")).sendKeys(rs.getString("Password"));
			dr.findElement(By.id("login-button")).click();
		}
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
