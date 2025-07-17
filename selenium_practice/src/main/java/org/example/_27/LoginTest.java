package org.example._27;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    /**
     * Here we will write the test cases for the web page
     * we will only mention the validations here
     * */
    /**
     * Creating the WebDriver instance in the test class and passing it to the page class is better practice :
             * 1) Separation of Concerns : The test class should be responsible for test setup, execution, and teardown, while the page class should focus solely on page interactions.
             * 2) Flexibility and Reusability : You can easily switch browsers or configurations
             * 3) Multiple Pages in Single Test : Same driver instance across multiple pages
             * 4) Better Error Handling and Debugging : Take screenshot, capture logs, etc.
     * Problems with WebDriver in Page Class :
             * 1) Tight Coupling : Page class tightly coupled to WebDriver creation, Hard-coded browser and url
             * 2) Difficult Testing :
                     * Hard to test with different browsers
                     * Difficult to mock WebDriver for unit testing
                     * Cannot easily switch between environments
             * 3) Resource Management Issues
                     * Who's responsible for closing the driver?
                     * When and how do you quit the driver?
                     * Should page class manage this?
     * */
    /**
     * Flow :
     * Application <-> Page Object Class <-> Test Case <-> Class XML file
     * */

    WebDriver driver;
    @BeforeClass
    void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @Test
    void testLogin() throws InterruptedException {
        LoginPage lp= new LoginPage(driver);
        LoginPagePageFactory lppf= new LoginPagePageFactory(driver);
        lppf.setUserName("Admin");
        lppf.setPassword("admin123");
        lppf.clickLogin();

        /// only available in page factory class
        lppf.getAllLinks();
        Thread.sleep(2000); /// some time to load page
        Assert.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }
    @AfterClass
    void teardown(){
        driver.quit();
    }
}
