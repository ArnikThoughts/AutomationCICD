package Maveric.testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Maveric.pageObject.CheckOutPage;
import Maveric.pageObject.ConfirmationPage;
import Maveric.pageObject.MyCartPage;
import Maveric.pageObject.ProductCatalog;
import Maveric.testComponents.BaseTest;
import Maveric.testComponents.TestRetry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorValidation"},retryAnalyzer=TestRetry.class)
	public void LoginErrorchecking() {
		land.loginActions("minaranisen@gmail.com","Minaranisen34");
		Assert.assertEquals("Incorrect email or password.", land.ErrorChecking());
	}
	
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException{
		
		String ptd="ZARA COAT 3";
		
		ProductCatalog prog=land.loginActions("Cod123@gmail.com","Minausharoy#1");
		//Getting the Product list
		
		List<WebElement>products=prog.productList();
		prog.addToCart(ptd);
		MyCartPage mcp=prog.goToCartPage();
		//My Cart Page
		boolean res=mcp.ProductsChecking("ZARA COAT 33");
		Assert.assertFalse(res);
		
	    
	    
	}
}
