package Maveric.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//This class is for rerun the test case again just for reverifying purpose
public class TestRetry implements IRetryAnalyzer {

	
	int count=0;
	int maxTry=1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry) {
			count++;
			return true;
			//return true means the test case will rerun again and again, dependeing upon the condition
		}
		return false;
	}

}
