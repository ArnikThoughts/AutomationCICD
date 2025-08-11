package Maveric.testCases;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Maveric.pageObject.Landing;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class StandAloneCase {
public static void main(String[] args) throws InterruptedException {
	
	String ptd="ZARA COAT 3";
	WebDriver driver = new ChromeDriver();
	//Login
	
	driver.get("https://rahulshettyacademy.com/client");
	driver.findElement(By.id("userEmail")).sendKeys("minaranisen@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Minaranisen#1");
	driver.findElement(By.id("login")).click();
	Landing land=new Landing(driver);
	driver.manage().window().maximize();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	
	List<WebElement>products=driver.findElements(By.cssSelector("div.mb-3"));
	//Iteration from the Listed products
	//Now a days for loops are back dated, not preferable..mummy...mm..hmm.(crying)
	/*for(int i=0;i<products.size();i++) {
		String productName=products.get(i).getText();
		if(productName=="ZARA COAT 3") {
			
		}
	}*/
	
	WebElement element=products.stream().filter(product -> product
.findElement(By.cssSelector("b")).getText().equals(ptd)).findFirst().orElse(null);
	
	element.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
	//WebDriverWait wt= new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
	Thread.sleep(5000);
	driver.findElement(By.cssSelector("ul li:nth-child(4) button.btn")).click();
    List<WebElement> CartProducts= driver.findElements(By.cssSelector("div.cartSection h3"));
    boolean result=CartProducts.stream().anyMatch(CartProduct -> CartProduct.getText().equalsIgnoreCase(ptd));
    Assert.assertTrue(result);
    driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
    
    WebElement store=driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
    Actions a = new Actions(driver);
    a.sendKeys(store, "India").build().perform();
    
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(@class,'ta-item')])[2]")));
    
    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
    JavascriptExecutor js=(JavascriptExecutor)driver;
    js.executeScript("window.scrollBy(0,900)");
    driver.findElement(By.cssSelector("a.action__submit")).click();
    String value=driver.findElement(By.cssSelector("h1.hero-primary")).getText();
    Assert.assertTrue(value.equalsIgnoreCase("Thankyou for the order."));
    
    
}
}
