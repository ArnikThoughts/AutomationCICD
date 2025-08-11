package Maveric.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Maveric.AbstructComponents.AbstructComponents;

public class Landing extends AbstructComponents {

	WebDriver driver;
	
	public Landing(WebDriver driver) {
		//initialization 
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	//WebElement elment=driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submitButton;
	
	// .ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement Error;
	
	//To perform the actions on the locators.
	public ProductCatalog loginActions(String uerEmail, String pasword) {
		userEmail.sendKeys(uerEmail);
		password.sendKeys(pasword);
		submitButton.click();
		
		ProductCatalog pg=new ProductCatalog(driver);
		return pg;
	}
	
	public String ErrorChecking() {
		
		//Wait
		WaitingForAnElement(Error);
		return Error.getText();
	}
	
	//Opening the Website
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
}
