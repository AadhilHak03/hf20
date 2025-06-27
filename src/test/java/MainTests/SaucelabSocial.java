package MainTests;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import PageObjects.productPage;
import TestComponents.BaseTest;

public class SaucelabSocial extends BaseTest{
	@Test
	public void E2E( ) throws SQLException {
		
		impWait();
		productPage pp = lp.loginCode();
		pp.scrollDown();
		pp.openLinks();
		pp.goIntoHandles();	
	}
}
