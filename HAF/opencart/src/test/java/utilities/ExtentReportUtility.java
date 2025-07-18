package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentReportUtility implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    String report_name;
//    String timestamp= new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
//    String timestamp1 =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));

    /// here local date time is more modern(fast, thread safe, time package) and simple date format is a legacy class(slow, not thread safe, util package)
    public void onStart(ITestContext testContext) {

        /// using time stamp to make the report name unique, so previous reports are not overridden
        report_name = "report_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + report_name);
        sparkReporter.config().setDocumentTitle("Opencart Automation report");
        sparkReporter.config().setReportName("Opencart Functional testing");
        sparkReporter.config().setTheme(Theme.STANDARD);


        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("Application", "opencart");
        extentReports.setSystemInfo("Module", "Admin");
        extentReports.setSystemInfo("Sub Module", "Customer");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");


        /// we are providing the os and browser parameters through xml file
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extentReports.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extentReports.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extentReports.setSystemInfo("Included Groups", includedGroups.toString());
        }

    }

    public void onTestSuccess(ITestResult testResult) {
        extentTest = extentReports.createTest(testResult.getTestClass().getName());
        extentTest.assignCategory(testResult.getMethod().getGroups()); /// to display groups in report
        extentTest.log(Status.PASS, testResult.getName() + " got successfully executed");
    }

    public void onTestFailure(ITestResult testResult) {
        extentTest = extentReports.createTest(testResult.getTestClass().getName());
        extentTest.assignCategory(testResult.getMethod().getGroups());

        extentTest.log(Status.FAIL, testResult.getName() + " got failed");
        extentTest.log(Status.INFO, testResult.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(testResult.getName());  /// we pass the name of the test method to capture screen method and return the file path
            extentTest.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onTestSkipped(ITestResult testResult) {
        extentTest = extentReports.createTest(testResult.getTestClass().getName());
        extentTest.assignCategory(testResult.getMethod().getGroups());

        extentTest.log(Status.SKIP, testResult.getName() + " got skipped");
        extentTest.log(Status.INFO, testResult.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        /// this statement completes the report generation and saves to the location
        extentReports.flush();

        /// if we wish to open the report as soon as the execution is done, and report is generated we can do the following
        String path_of_extent_report = System.getProperty("user.dir") + "\\reports\\" + report_name;
        File extentReport = new File(path_of_extent_report);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /// if we wish to send the report to someone as soon as it is generated following can be done
        /// for this we need to add java email dependency
        /// https://mvnrepository.com/artifact/org.apache.commons/commons-email

//        sendReportEmail();
    }

    private void sendReportEmail() {
        try {
            URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + report_name);

            /// Creating the email message

            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");

            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("arshpreet.singh@gmail.com", "password"));
            email.setSSLOnConnect(true);
            email.setFrom("arshpreet.singh@gmail.com"); //Sender
            email.setSubject("Test Results");
            email.setMsg("Please find Attached Report....");
            email.addTo("arshpreet.singh.to@gmail.com"); //Receiver
            email.attach(url, "extent report", "please check report...");
            email.send(); // send the email
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
