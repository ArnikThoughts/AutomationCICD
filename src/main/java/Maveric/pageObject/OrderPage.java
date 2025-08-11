package Maveric.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Maveric.AbstructComponents.AbstructComponents;

public class OrderPage extends AbstructComponents {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this );
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement>orders;
	public boolean ordersChecking(String ptd) {
	   boolean result=orders.stream().anyMatch(CartProduct -> CartProduct.getText()
			   .equalsIgnoreCase(ptd));
	    return result;
	}
	

}
