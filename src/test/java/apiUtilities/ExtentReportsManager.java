package apiUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest testsExtentTest;
	String repNameString;
	
	public void onStart(ITestContext testContext) {
		String timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repNameString = "Test-Report-"+timeStampString+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\Reports\\"+repNameString);
		
		sparkReporter.config().setDocumentTitle("Rest Assured project");
		sparkReporter.config().setReportName("Pet Store Users API");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extentReports=new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Application", "Pet Store Users API");
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("user", "iEjaz");
			
	}
	public void onTestSuccess(ITestResult result) {
		
		testsExtentTest = extentReports.createTest(result.getName());
		testsExtentTest.assignCategory(result.getMethod().getGroups());
		testsExtentTest.log(Status.PASS, "Test Passed");
	}
public void onTestFailure(ITestResult result) {
		
		testsExtentTest = extentReports.createTest(result.getName());
		testsExtentTest.assignCategory(result.getMethod().getGroups());
		testsExtentTest.log(Status.FAIL, "Test Failed");
		testsExtentTest.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
public void onTestSkip(ITestResult result) {
	
	testsExtentTest = extentReports.createTest(result.getName());
	testsExtentTest.assignCategory(result.getMethod().getGroups());
	testsExtentTest.log(Status.SKIP, "Test SKipped");
	testsExtentTest.log(Status.SKIP, result.getThrowable().getMessage());
	
}
public void onFinish(ITestContext testContext) {
	extentReports.flush();
}
}
