package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.Logger; /// this is important to note (multiple logger are there)
import org.apache.logging.log4j.LogManager;

import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BaseClass {
    /// we can keep this class inside the testBase package
    /// we will write method to load the log4j2.xml file so all test classes can use it
    public Logger logger;
    public Properties properties;
    public static WebDriver driver;  /// it is static to avoid conflict when new BaseClass is called in ExtentReportUtility
    @BeforeClass(groups = {"Main"}) /// groups added in step 7
    @Parameters({"os","browser"})   /// getting the parameters from master.xml file to dynamically choose which browser and os to open (os is used with grid)
    public void setup(String os, String br) throws Exception {

        /// loading config.properties file
        FileReader fr= new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
        properties= new Properties();
        properties.load(fr);

        /// creating logger object for the particular class under Execution
        logger= LogManager.getLogger(this.getClass()); /// it returns the log manager for specific class under execution
        /// this variable will be used to perform logging


        /// in order to add the grid functionality we need to change the way we instantiate the web driver object
        /// we added a property in the properties file based on which we will make changes in driver object

        /// following way can be used for grid if we are using standalone, separate hub and node, or docker image. same code can be implemented
        String execution_env=properties.getProperty("execution_env");
        if (execution_env.equalsIgnoreCase("local")){
            getLocalDriver(br);
        } else if (execution_env.equalsIgnoreCase("remote")) {
            getRemoteDriver(os,br);
        }
        if (driver==null)  /// own, if both the methods result in default case and driver is not initialised properly
            throw new Exception("driver didn't got properly initialised");

        driver.manage().deleteAllCookies(); /// deletes all cookies for the current domain
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        driver.get("https://tutorialsninja.com/demo/"); /// we can hard code our url as well, since we are using same website for all tests
        driver.get(getURL()); /// we can pass the url this way if different websites are used for different tests

    }

    public String getURL(){
//        return "https://tutorialsninja.com/demo/";
        return properties.getProperty("tutorialNinjaURL");  /// both are valid but this one takes the value from properties file
    }   /// own, can override this method in child class to pass different website url for different test cases


    @AfterClass(groups = {"Main"})   /// groups added in step 7
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        return RandomStringUtils.randomAlphabetic(5); /// it is in commons library, 5 characters in the string
    }
    public String randomNumber(){
        return RandomStringUtils.randomNumeric(10);
    }
    public String randomAlphaNumeric(){
        return RandomStringUtils.randomAlphabetic(5)+RandomStringUtils.randomNumeric(10);
    }

    public String captureScreen(String test_method_name) {  ///  in step 8 Extent Reports for screenshot file path
        /// we pass the test method name as parameter and return the path of the screenshot
        String timeStamp= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));

        TakesScreenshot ts= (TakesScreenshot) driver;
        File sourceFile=ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+test_method_name+"_"+timeStamp+".jpg";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
    private void getLocalDriver(String browser_name){  /// own, to keep code clean
        switch (browser_name.toLowerCase()) { /// opening browser based on parameter dynamically
            case "chrome" ->   driver = new ChromeDriver();
            case "edge" ->     driver = new EdgeDriver();
            case "firefox" ->  driver = new FirefoxDriver();
            case "safari" ->   driver = new SafariDriver();
            default ->
                System.out.println("No matching browser on local machine");
        }
    }
    private void getRemoteDriver(String os, String browser_name) throws MalformedURLException {   /// own, to keep the code clean
    /// url will be hub_ip_address+hub_port+/wd/hub
        String hub_url="http://10.4.8.251:4444/wd/hub";   /// edit the url as per hub instance
//        String hub_url="http://localhost:4444/wd/hub";
        /// both are correct if working on same machine

        DesiredCapabilities capabilities= new DesiredCapabilities();

        /// we get the operating system using parameter from xml file
        /// we are using switch case, can also use if else ladder to use equalsIgnoreCase() for better comparisons
        switch (os.toLowerCase()){
            case "windows" ->capabilities.setPlatform(Platform.WIN10);
            case "mac" ->capabilities.setPlatform(Platform.MAC);
            case "linux" -> capabilities.setPlatform(Platform.LINUX);
            default -> {
                System.out.println("No matching os on grid");
                return;
            }
        }
        switch (browser_name.toLowerCase()){
            case "chrome" -> capabilities.setBrowserName("chrome"); /// either hardcode browser name like this or
            case "edge" -> capabilities.setBrowserName(Browser.EDGE.browserName()); ///  or use such way to get the browser name
            case "firefox" -> capabilities.setBrowserName(Browser.FIREFOX.browserName());
            default -> {
                System.out.println("No matching browser on grid");
                return;
            }
        }
        driver= new RemoteWebDriver(new URL(hub_url),capabilities);
    }
}
