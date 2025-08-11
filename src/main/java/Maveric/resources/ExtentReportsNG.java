package Maveric.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
public static ExtentReports config() {
		
		//This ExtentSparkReporter is responsible to create a index file of report and to do some configuration of our report file
		String path = System.getProperty("user.dir")+"//reports/html.index";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Arnik Automation Page");
		reporter.config().setDocumentTitle("Test Results");
		
		//Extent class is responsible to execute all the reports
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Arnik");
		return extent;
		
	}
}
