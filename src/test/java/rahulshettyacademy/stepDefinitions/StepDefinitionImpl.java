package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage=launchApplication();
	}
	
	//@Given("Logged in with username <name> and password <password>")
	@Given("^Logged in with username (.+) and password (.+)$")  //for dynamic parameter using regular expression (.+) and starts with ^ and end with $
	public void logged_in_username_and_password(String username, String password)
	{
		productcatalogue=landingpage.loginApplication(username, password);
	}
	
	//@When("I add product <productName> to cart")
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	//And Checkout <productName> and submit the order  //@And and @When both will work for this step.
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match =cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage= cartpage.goToCheckout();
		checkoutpage.SelectCountry("India");
		confirmationpage = checkoutpage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")   //Expecting string so write like this.
	public void message_displayed_confirmationPage(String string ) 
	{
		String confirmMessage= confirmationpage.getConfirmationMessage();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String strArg1)
	{
		Assert.assertEquals(strArg1, landingpage.getErrorMessage());
		driver.close();
	}
			
  
}
