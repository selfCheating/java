package CommonMethods;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestretryAnalyzerPractice implements IRetryAnalyzer {

	int countstart = 0;
	int countend = 5;
	
	public boolean retry(ITestResult result) {
if(countstart<countend) {
	String testname = result.getName();
	System.out.println(testname+ "not found in current iteration" +countstart+ "hence retrying in" +(countstart+1));
	countstart++;
	return true;
}
		return false;
	}

}
