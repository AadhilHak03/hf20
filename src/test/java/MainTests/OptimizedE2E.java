package MainTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.InfoPage;
import PageObjects.productPage;
import TestComponents.BaseTest;

public class OptimizedE2E extends BaseTest{

	@Test
	public void OE2E()
	{	testCaseID = "1";
	
		productPage pp = lp.loginCode();
		pp.addProduct();
		CartPage cp = pp.goToCart();
		InfoPage ip = cp.goToInfoPage();
		CheckoutPage cp1 = ip.goToCheckout();
		ConfirmationPage cp2= cp1.goToConfirmationPage();
		cp2.backHome();
		Assert.assertTrue(false);
		
	}

}
