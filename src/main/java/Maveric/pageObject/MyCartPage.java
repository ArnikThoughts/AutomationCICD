package Maveric.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Maveric.AbstructComponents.AbstructComponents;

public class MyCartPage extends AbstructComponents {
	
	WebDriver driver;
    public MyCartPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements( driver,this);
	
}
@FindBy(css="div.cartSection h3")
List<WebElement>CartProducts;
public boolean ProductsChecking(String ptd) {
	
   boolean result=CartProducts.stream().anyMatch(CartProduct -> CartProduct.getText().equalsIgnoreCase(ptd));
    
    return result;
}


@FindBy(xpath="//button[contains(text(),'Checkout')]")
WebElement checkOutButton;

public CheckOutPage checkOutBtnClick() {
	checkOutButton.click();
	CheckOutPage cop=new CheckOutPage(driver);
	return cop;
	}
		
	
		
}
   


