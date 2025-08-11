package Maveric.AbstructComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Maveric.pageObject.MyCartPage;
import Maveric.pageObject.OrderPage;

import org.openqa.selenium.JavascriptExecutor;
public class AbstructComponents {
	
	WebDriver driver;
	public AbstructComponents(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
    //For Explicitly Wit for an Element for By elements
	public void WaitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	//For full Web element Explicit Wait
	public void WaitingForAnElement(WebElement element) {
		WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(10));
		wt.until(ExpectedConditions.visibilityOf(element));
	}
	//For Scroll Down the Page
	public void scrollDownFunction() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,900)");
	}
	//Hence the Cart button is in the top header so it will be common for all the pages
	@FindBy(css="ul li:nth-child(4) button.btn")
	WebElement cartBtn;
	
	@FindBy(xpath="//button[contains(@routerlink,'myorders')]")
	WebElement orderButton;
	
	public MyCartPage goToCartPage() throws InterruptedException {
		Thread.sleep(5000);
		cartBtn.click();
		MyCartPage mcp=new MyCartPage(driver);
	      return mcp;
		}
	public OrderPage goToOrderPage() {
		orderButton.click();
		OrderPage pg=new OrderPage(driver);
		return pg;
		
	}
}
