package Utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

	public class ExtentReportManager implements ITestListener {
		
		public ExtentSparkReporter sparkReporter; // UI of the report
		public ExtentReports extent; // populate common Info of the report.
		public ExtentTest test; // creating test case entries in the report and update status of the test methods
		
		public String repo_Name;
		
		public void onStart(ITestContext context) {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); // simpleDateformat is a class from java.text package which is used for genrating date in desired format.
			Date dt =new Date(); 
			String DateAndTimeStamp= df.format(dt); // this method will generate the date along with time stamp.
			
			repo_Name= "Test-Report"+DateAndTimeStamp+".html";
			
			sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/"+repo_Name);
		  
			sparkReporter.config().setDocumentTitle("Open Cart Automation Report"); // Title of the report
			sparkReporter.config().setReportName("Open Cart Functional Testing"); // name of the report
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			
			extent.setSystemInfo("Application", "OpenCart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("sub Module", "customers");
			extent.setSystemInfo("User Name",System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			
			//getting OS name from xml file dynamically.
			String OsName= context.getCurrentXmlTest().getParameter("OS");
			extent.setSystemInfo("Operating System", OsName);
			
			//getting Browser name from xml file dynamically.
			String browserName= context.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser Name", browserName);
			
			//getting groups name from xml file dynamically.
			List<String> includedGroups= context.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty()) 
			{
				extent.setSystemInfo("groupName", includedGroups.toString());
			}
			
		  }
		
		 public void onTestStart(ITestResult result) {
			
			  }
		 
		 public void onTestSuccess(ITestResult result) {
			 test=  extent.createTest(result.getTestClass().getName()); // create a new entry in the report. and getTestClass() method will fetch the particular class and getName() method will fetch the name of the particular class.
			 test.assignCategory(result.getMethod().getGroups()); // To display groups in report.
			 test.log(Status.PASS,result.getName()+" got successfully executed"); // updating test result.
			  }
		 
		 public void onTestFailure(ITestResult result) {
			 test=  extent.createTest(result.getTestClass().getName()); // create a new entry in the report.
			 test.assignCategory(result.getMethod().getGroups()); // To display groups in report.
			 test.log(Status.FAIL,result.getName()+" got failed"); // updating test result.
			 test.log(Status.FAIL,result.getThrowable().getMessage());
			 
			 try {
			 BaseClass base= new BaseClass(); // creating object of base class for accessing the capture screenshot method.
			 String imgPath=base.CaptureScreenShot(result.getName());
			 test.addScreenCaptureFromPath(imgPath);
			 }
			 catch(Exception e1)
			 {
				 e1.printStackTrace();
			 }
			    
		}
		 
		 public void onTestSkipped(ITestResult result) {
			 test=  extent.createTest(result.getTestClass().getName()); // create a new entry in the report.
			 test.assignCategory(result.getMethod().getGroups()); // To display groups in report.
			 test.log(Status.SKIP,result.getName()+" got Skipped"); // updating test result.
			 test.log(Status.SKIP,result.getThrowable().getMessage());
			  }
		 
		 public void onFinish(ITestContext context) {
			  extent.flush();
			  }

	}



