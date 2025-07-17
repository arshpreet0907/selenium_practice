package org.example._26;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

public class ExtentReportWithListener implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;


    public void onStart(ITestContext context){
        /// here we are passing the name of the report through xml file as a parameter
        /// we cannot direct put @Parameters as annotation here since the testng will pass parameter to test file not the listener so are getting the parameter from test case itself
        String report_name = context.getCurrentXmlTest().getParameter("report_name");

        sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+report_name+".html"); /// need to specify the location of our report files
        /// one limitation with hardcoding the name of the file is it overrides the previous report with the new one
        /// to overcome this we can create it generate report name with time stamp
        sparkReporter.config().setDocumentTitle("Automation report"); /// title of report
        sparkReporter.config().setReportName("Functional testing"); /// name of the report
        sparkReporter.config().setTheme(Theme.STANDARD);


        extentReports= new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        /// specifying the common data
        /// currently hardcoded but in real time it using the methods
        extentReports.setSystemInfo("Computer name","localhost");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("Tester name","ArshPreet");
        extentReports.setSystemInfo("Browser","Chrome");
        extentReports.setSystemInfo("OS","Windows 10");
    }

    public void onTestSuccess(ITestResult result){
        extentTest=extentReports.createTest(result.getName()); /// creating a new entry in the report
        extentTest.log(Status.PASS, "Test case passes is : "+result.getName()); /// update the status p/f/s
        /// instead of method name we should specify the class and test name or test case ID

    }
    public void onTestFailure(ITestResult result){
        extentTest=extentReports.createTest(result.getName());
        extentTest.log(Status.FAIL, "Test case failed is : "+result.getName());
        extentTest.log(Status.FAIL, "Test case failed cause is : "+result.getThrowable());
        /// we can also add the screenshot of failed tests
    }
    public void onTestSkipped(ITestResult result){
        extentTest=extentReports.createTest(result.getName());
        extentTest.log(Status.SKIP, "Test case skipped is : "+result.getName());
    }
    public void onFinish(ITestContext context){
        extentReports.flush();
    }
}