package PageObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AC;

public class landingPage extends AC {
	
	WebDriver dr;
	 public landingPage(WebDriver dr)
	 {	super(dr);
		 this.dr=dr;
		 PageFactory.initElements(dr, this);
	 }
	 
	@Test 
	public void goTo()
	{
		dr.get("https://www.saucedemo.com/");
	}
	
	
	@FindBy(id="user-name")
	WebElement user;
	@FindBy(id="password")
	WebElement pass;
	@FindBy(id="login-button")
	WebElement login;
	
	@Test
	public productPage loginCode() throws SQLException {
		String host = "localhost";
		String port = "3306";
		
		Connection con = DriverManager.getConnection("jdbc:mysql://" +host+ ":" +port+ "/DB1", "root", "Meliodas99@!");
		
		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery("select * from UserInfo where Username = 'problem_user';");
		
		while(rs.next()) {
			
			user.sendKeys(rs.getString("Username"));
			pass.sendKeys(rs.getString("Password"));
			login.click();
		
		}
		
		return new productPage(dr);
	}
	
	
	
	
	
	
	
	
}
