package amrutraibagi.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer{
	
	int count=0;
	int maxtry=1;
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxtry) {
			count ++;
			return true;
		}
		return false;
	}

}
