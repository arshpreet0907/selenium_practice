package org.example._26;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

//@Listeners({org.example._26.ListenerClassI.class}) /// here it requires the array of class type
public class ListenerT {
    /**
     * we are creating the test methods here
     * */
    WebDriver driver;
    @BeforeClass
    void open_app() throws InterruptedException {
        driver= new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(3000);
    }
    @Test(priority = 1)
    void test_logo(){

        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        boolean is_visible=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt=\"company-branding\"]"))).isDisplayed();
        Assert.assertTrue(is_visible);
    }
    @Test(priority = 2)
    void test_url(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/auth/logi"); /// url is incorrect intentionally
    }
    @Test(priority = 3,dependsOnMethods = {"test_url"}) /// gets skipped when url test fails
    void test_title(){
        Assert.assertEquals(driver.getTitle(),"OrangeHRM");
    }
    @AfterClass
    void teardown(){
        driver.quit();
    }
}