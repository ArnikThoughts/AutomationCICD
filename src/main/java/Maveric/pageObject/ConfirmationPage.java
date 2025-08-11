package Maveric.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Maveric.AbstructComponents.AbstructComponents;

public class ConfirmationPage extends AbstructComponents {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="h1.hero-primary")
	WebElement text;
	By sd=By.cssSelector("h1.hero-primary");
	public String getConfirmationText() {
		WaitForElementToAppear(sd);
		String texq=text.getText();
		return texq;
	}
}
