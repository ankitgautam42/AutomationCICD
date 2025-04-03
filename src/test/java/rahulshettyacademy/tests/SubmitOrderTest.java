package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups="purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	
	{
		ProductCatalogue productcatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match =cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage= cartpage.goToCheckout();
		checkoutpage.SelectCountry("India");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage= confirmationpage.getConfirmationMessage();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistroyTest()
	{
		ProductCatalogue productcatalogue=landingpage.loginApplication("Ankit.Gautam@gmail.com", "Ankit@123");
		OrderPage ordersPage =productcatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*
		 HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "Ankit.Gautam@gmail.com");
		map.put("password", "Ankit@123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "akg421995@gmail.com");
		map1.put("password", "Ankit@0987");
		map1.put("product", "ADIDAS ORIGINAL");
		
		*/
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*
	 * @DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"Ankit.Gautam@gmail.com","Ankit@123","ZARA COAT 3"},{"akg421995@gmail.com","Ankit@0987","ADIDAS ORIGINAL"}};
	}
	
	*/
	
	

}
