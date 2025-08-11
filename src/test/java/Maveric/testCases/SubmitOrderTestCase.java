package Maveric.testCases;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Maveric.pageObject.CheckOutPage;
import Maveric.pageObject.ConfirmationPage;
import Maveric.pageObject.Landing;
import Maveric.pageObject.MyCartPage;
import Maveric.pageObject.OrderPage;
import Maveric.pageObject.ProductCatalog;
import Maveric.testComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SubmitOrderTestCase extends BaseTest {
	
	String ptd="ZARA COAT 3";
         @Test(dataProvider="getData", groups={"Purchase"}) 
    public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException{
	
	ProductCatalog prog=land.loginActions(input.get("email"), input.get("password"));
	//Getting the Product list
	
	List<WebElement>products=prog.productList();
	prog.addToCart(input.get("productName"));
	MyCartPage mcp=prog.goToCartPage();
	//My Cart Page
	boolean res=mcp.ProductsChecking(input.get("productName"));
	Assert.assertTrue(res);
	CheckOutPage cop=mcp.checkOutBtnClick();
    //CheckOutPage
	cop.countrySelection("India");
	ConfirmationPage confirm=cop.submitOrder();
    //Thank you page(Confirmation Page)
    String value=confirm.getConfirmationText();
    Assert.assertTrue(value.equalsIgnoreCase("Thankyou for the order."));
       
}
         
         @Test(dependsOnMethods={"submitOrder"})
         public void ordersValidationTest() {
        	 ProductCatalog prog=land.loginActions("minaranisen@gmail.com","Minaranisen#1");
        	 OrderPage pg=land.goToOrderPage();
        	 Assert.assertTrue(pg.ordersChecking(ptd));
         }
         
         
        
         @DataProvider
         public Object[][] getData() throws IOException {
        	 /*HashMap<String, String> map=new HashMap<String,String>();
        	 map.put("email", "minaranisen@gmail.com");
        	 map.put("password", "Minaranisen#1");
        	 map.put("productName", "ZARA COAT 3");
        	 
        	 HashMap<String, String> map1=new HashMap<String,String>();
        	 map1.put("email", "Cod123@gmail.com");
        	 map1.put("password", "Minausharoy#1");
        	 map1.put("productName", "ADIDAS ORIGINAL");*/
        	 
        	 //Taking 2D Array  mandatorily for Data Provider as HashMap
        	 //How Many Data will be provided to the 2D Array, That many times the Test Will Run with those Data Sets
        	List<HashMap<String, String>> data= JsonToMapData(System.getProperty("user.dir")+"\\src\\test\\java\\Maveric\\data\\PurchaseOrder.json");
        	return new Object[][] {{data.get(0)},{data.get(1)}};
        	
        	// new Object[][]{{"minaranisen@gmail.com","Minaranisen#1","ZARA COAT 3"},
        	//{"Cod123@gmail.com","Minausharoy#1","ADIDAS ORIGINAL"}}
       
         }
}
