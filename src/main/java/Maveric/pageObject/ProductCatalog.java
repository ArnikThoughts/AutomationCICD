package Maveric.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Maveric.AbstructComponents.AbstructComponents;

public class ProductCatalog extends AbstructComponents {

	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		//initialization 
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	
	@FindBy(css="div.mb-3")
	List<WebElement> products;
	
	By byProducts=By.cssSelector("div.mb-3");
	By addCartProduct = By.cssSelector("div.card-body button:last-of-type");
	By waitElement = By.xpath("//div[@id='toast-container']");
	
	public List<WebElement> productList() {
		WaitForElementToAppear(byProducts);
		return products;
	}
	
	//Searching and Filtering the Expected Product name
	public WebElement productName(String elementName) {
		WebElement element=productList().stream().filter(product -> product
	.findElement(By.cssSelector("b")).getText().equals(elementName)).findFirst().orElse(null);
		return element;
	}
	
	// After Searching clicking on the product
	public void addToCart(String elementName) {
		productName(elementName).findElement(addCartProduct).click();
		WaitForElementToAppear(waitElement);
      
	}

	
	
	
	
}
