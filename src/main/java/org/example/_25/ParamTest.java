package org.example._25;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class ParamTest {
    WebDriver driver;
    @BeforeClass
    @Parameters({"browser","url"})
    void setup(String br,String url) throws Exception {
        switch (br) {
            case "chrome":   driver = new ChromeDriver();break;
            case "edge":     driver=new EdgeDriver();break;
            case "firefox":  driver= new FirefoxDriver(); break;
            default:
//                return;
                throw new Exception("invalid browser name");
        }
//        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000); /// some time for logo to load

    }
    @Test(priority = 1)
    void testLogo()  {
        boolean status=driver.findElement(By.xpath("//img[@alt=\"company-branding\"]")).isDisplayed();
        Assert.assertTrue(status);
    }
    @Test(priority = 2)
    void testTitle(){
        Assert.assertEquals(driver.getTitle(),"OrangeHRM");
    }
    @Test(priority = 3)
    void testURL(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @AfterClass
    void teardown(){
        System.out.println(((RemoteWebDriver)driver).getSessionId());
        if (((RemoteWebDriver)driver).getSessionId()!=null) ///  to check if driver is active or not
            driver.quit();
    }
}
