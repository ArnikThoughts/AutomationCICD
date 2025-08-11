package Maveric.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Maveric.pageObject.Landing;

public class BaseTest {

	public WebDriver driver;
	public Landing land;
	public WebDriver initializeDriver() throws IOException {
		//properties class
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Maveric\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//This line of code will decide the test case should be triggered from Terminal or from code/properties(Global Data properties)
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions option=new ChromeOptions();
		   if(browserName.contains("headless")) {
			   option.addArguments("headless");
		   }
			driver = new ChromeDriver(option);
		    driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.equalsIgnoreCase("FireFox")){
			System.setProperty("webdriver.gecko.driver", "C:\\\\Users\\\\arnik\\\\Desktop\\\\Maven Folder\\\\geckodriver-v0.33.0-win64\\\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")){
			//edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	//Reading & Storing Data as String from a file
	   public List<HashMap<String, String>> JsonToMapData( String FilePath) throws IOException {
		  @SuppressWarnings("deprecation")
		  //Converting JSON File data into String JSON data
		String jsonContent= FileUtils.readFileToString(new File(FilePath));
		   
		  //String to HashMap by using Jackson databind external Utility
		  //TypeReference<List<HashMap<String,String>>> typeRef = new TypeReference<List<HashMap<String,String>>>(){};
		  ObjectMapper mapper = new ObjectMapper();
		  
		
		 List<HashMap<String,String>> data =  mapper.readValue(jsonContent, 
				 new TypeReference<List<HashMap<String,String>>>(){});
		 
		
		 return data;
		   
	}
	   
	   public String getScreenshot(String testCase, WebDriver driver) throws IOException {
      	 TakesScreenshot screenshot= (TakesScreenshot)driver;
      	File tc= screenshot.getScreenshotAs(OutputType.FILE);
      	File file= new File(System.getProperty("user.dir")+"//reports//"+ testCase+".png");
      	FileUtils.copyFile(tc, file);
      	return System.getProperty("user.dir")+"//reports//"+ testCase+".png";
       }
	
	@BeforeMethod(alwaysRun=true)
	public Landing launchApplication() throws IOException {
		driver=initializeDriver();
	  land=new Landing(driver);
	  land.goTo();
		return land;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closure() {
		driver.close();
	}
}
