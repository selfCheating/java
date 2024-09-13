package CommonMethods;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {
	
	int countstart = 0;
	int countend = 5;

	public boolean retry(ITestResult result) {
		if (countstart < countend) {
        String testname = result.getName();
	System.out.println(testname+ "failed in current iteration " +countstart+ " hence retrying" +(countstart+1));
	countstart++;
	return true;
		 }
		return false;
       }
     }