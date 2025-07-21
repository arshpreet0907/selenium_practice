package org.example._7;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitMethods {
    /**
     * To solve synchronisation problem

     * java method
            * 1)Thread.sleep()
     * wait commands
            * 1) implicit wait
            * 2) explicit wait \ fluent wait

     * NoSuchElementExecption
     */
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
//        thread_sleep(driver);
//        implict_wait(driver);
//        explict_wait(driver);
   fluent_wait(driver);
    }
    static  void thread_sleep(WebDriver driver){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Arsh");
//        driver.quit();

    }
    static void implict_wait(WebDriver driver){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        /// syntax for implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Arsh");
//        driver.quit();
    }
    static void explict_wait(WebDriver driver){

        /// declaring
        WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();


        /// syntax for using explicit wait
        WebElement user_text_field=mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")));

        WebElement pass_text_field=mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")));

        WebElement submit_button=mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")));

        user_text_field.sendKeys("Admin");
        pass_text_field.sendKeys("admin123");
        submit_button.click();
//        driver.quit();
    }
    static void fluent_wait(WebDriver driver){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        /// declaration
        Wait<WebDriver> mywait=new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        /// usage
        WebElement txt_username=mywait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//input[@placeholder=\"Username\"]"));
            }
        });
        txt_username.sendKeys("Admin");


//        driver.quit();
    }
}

