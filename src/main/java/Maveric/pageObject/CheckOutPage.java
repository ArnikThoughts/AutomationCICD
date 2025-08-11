package Maveric.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Maveric.AbstructComponents.AbstructComponents;

public class CheckOutPage extends AbstructComponents {

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
	@FindBy(css="a.action__submit")
	WebElement submitButton;
	
	By selectedResult=By.xpath("(//button[contains(@class,'ta-item')])[2]");
	public void countrySelection(String CountryName) {
		Actions a = new Actions(driver);
	    a.sendKeys(Country, CountryName).build().perform();
	    WaitForElementToAppear(selectedResult);
	    SelectCountry.click();
	}
	
	public ConfirmationPage submitOrder() throws InterruptedException {
		scrollDownFunction();
		Thread.sleep(5000);
		submitButton.click();
		ConfirmationPage cnf=new ConfirmationPage(driver);
		return cnf;
	}

}
