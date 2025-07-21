package org.example._22;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRMTest {

    /**
     * Open the app
     * test logo presence
     * log in
     * log out
     * */

    WebDriver driver;
    @Test(priority = 1)
    void open_app(){
        driver= new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("app opened");
    }
    @Test(priority = 2)
    void test_logo(){

        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        boolean is_visible=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt=\"company-branding\"]"))).isDisplayed();
        System.out.println("is image visible : "+is_visible);
    }
    @Test(priority = 3)
    void login(){
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        System.out.println("logged in");
    }
    @Test(priority = 4)
    void logout(){
        driver.findElement(By.xpath("//img[@alt=\"profile picture\"]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        System.out.println("logged out");
        driver.quit();
    }

}
