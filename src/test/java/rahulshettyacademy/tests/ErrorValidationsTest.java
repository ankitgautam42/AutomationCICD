package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest{
	String productName = "ZARA COAT 3";

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	
	{
		landingpage.loginApplication("Ankit.Gautam@gmail.com", "Ankit@");
		//providing wrong password
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	
	{
		ProductCatalogue productcatalogue=landingpage.loginApplication("Ankit.Gautam@gmail.com", "Ankit@123");
		productcatalogue.addProductToCart(productName);
		CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match =cartpage.verifyProductDisplay("ZARA COAT 33");  //giving not present product.
		Assert.assertFalse(match);
		
	}

}
