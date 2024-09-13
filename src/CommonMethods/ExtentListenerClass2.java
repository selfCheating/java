package CommonMethods;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass2 implements ITestListener{
	ExtentSparkReporter sparkReporter;
	ExtentReports extentReport;
	ExtentTest test;
	
	public void reportConfigurations() {
		sparkReporter = new ExtentSparkReporter("./Extent-Report/report.html");
		extentReport = new ExtentReports();
		
		extentReport.attachReporter(sparkReporter);
		
		//Adding system/Environment details to the report
		extentReport.setSystemInfo("OS", "Windows 11");
		extentReport.setSystemInfo("user", "91855");
		
		//Configuration for changing look and feel of the report
		sparkReporter.config().setDocumentTitle("RestAssured Extent Report Listener");
	    sparkReporter.config().setReportName("This is my first Extent-Report");
	    sparkReporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onStart(ITestContext context) {
		reportConfigurations();
		System.out.println("On start method invoked");		
		//ITestListener.super.onStart(context);
	}
	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("On start method invoked");
		extentReport.flush();
		//ITestListener.super.onFinish(context);
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Name of the test method failed " +result.getName());
		test = extentReport.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel ("Name of the failed test case is " +result.getName(), ExtentColor.RED));
				//ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Name of the test method Skipped " +result.getName());
		test = extentReport.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel ("Name of the skipped test case is " +result.getName(), ExtentColor.YELLOW));
			//	ITestListener.super.onTestSkipped(result);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Name of the test method started" +result.getName());
		//ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Name of the test method executed successfully " +result.getName());
		test = extentReport.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel ("Name of the skipped test case is " +result.getName(), ExtentColor.GREEN));
		//ITestListener.super.onTestSuccess(result);
	}

	

	
	
}