package Maveric.CucumberStepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Maveric.pageObject.CheckOutPage;
import Maveric.pageObject.ConfirmationPage;
import Maveric.pageObject.Landing;
import Maveric.pageObject.MyCartPage;
import Maveric.pageObject.ProductCatalog;
import Maveric.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	public Landing land;
	public ProductCatalog prog;
	public MyCartPage mcp;
	public ConfirmationPage confirm;
@Given("I landed on Ecommerce Page")
public void i_landed_on_ecommerce_page() throws IOException {
	land=launchApplication();
}

@Given ("^Logged in with username (.+) and password (.+)$")
public void loging_with_username_password(String username, String password) {
	 prog=land.loginActions(username,password);
}

@When ("^I add product(.+) to cart$")
public void add_product_to_cart(String prductName) {
	List<WebElement>products=prog.productList();
	prog.addToCart(prductName);
}
@When("^checkOut(.+) and submit the order")
public void checkOut_and_submit_order(String ProductName) throws InterruptedException {
	mcp=prog.goToCartPage();
	//My Cart Page
	boolean res=mcp.ProductsChecking(ProductName);
	Assert.assertTrue(res);
	CheckOutPage cop=mcp.checkOutBtnClick();
    //CheckOutPage
	cop.countrySelection("India");
	confirm=cop.submitOrder();
}

@Then("{string} message is displayed on confirmation page")
public void display_message(String msg) {
	String value=confirm.getConfirmationText();
    Assert.assertTrue(value.equalsIgnoreCase(msg));
}
}
