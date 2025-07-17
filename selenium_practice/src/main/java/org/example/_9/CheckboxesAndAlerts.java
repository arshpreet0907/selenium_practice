package org.example._9;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckboxesAndAlerts {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
//        alerts_or_popups(driver);
        create_alert(driver);
    }
    static void check_box(WebDriver driver){
        driver.get("https://testautomationpractice.blogspot.com/");
        List<WebElement> check_boxes=driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
        System.out.println(check_boxes.size());
        Set<String> day_set=new HashSet<>();
        day_set.add("monday");
        day_set.add("wednesday");
        day_set.add("friday");
//        for (WebElement ele:check_boxes){
//            System.out.println(ele.getAttribute("id"));
//                ele.click();
//            }
        for (WebElement ele:check_boxes){
            if (day_set.contains(ele.getAttribute("id"))){
                ele.click();
            }
        }
        for (WebElement ele:check_boxes){
            if (ele.isSelected()){
                ele.click();
            }
        }
    }
    static void alerts_or_popups(WebDriver driver){
        /// alerts are not web elements cannot inspect them, need to switch driver focus to alert and then deal with it using following methods

        driver.get("https://the-internet.herouapp.com/javascript_alerts");

        /// normal alert with ok button
        /// switch driver to current active alert window
        /// <statement to start alert>
        Alert myalert=driver.switchTo().alert();
        System.out.println(myalert.getText());
        myalert.accept();

        /// alert with 2 button (confirmation alert) : ok and cancel button
        /// <statement to start alert>
        Alert myalert_1=driver.switchTo().alert();
        System.out.println(myalert_1.getText());
        myalert_1.dismiss();

        /// alert with input and 2 buttons: prompting alert
        /// <statement to start alert>
        /// put input to input space
        Alert myalert_2=driver.switchTo().alert();
        System.out.println(myalert_2.getText());
        myalert_2.sendKeys("alert msg passed");
        myalert_2.accept();


    }
    static void handle_alert_without_switchTo(WebDriver driver){
        driver.get("https://the-internet.herouapp.com/javascript_alerts");

        /// this can be done using explicit wait

        WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
        /// <statement to start alert>
        Alert myalert=   mywait.until(ExpectedConditions.alertIsPresent());
      /// looks for alert if available it returns
        System.out.println(myalert.getText());
        myalert.accept();
    }
    static void authentication_popup(WebDriver driver){
        /// special type of popup
        /// switch and explicit will not work
        /// we cannot directly locate

        /// need to inject username and password along with url.
        /// URl : https://the-internet.herouapp.com/basic_auth
        /// injected URL : https://username:password@the-internet.herouapp.com/basic_auth
        String url="https://the-internet.herouapp.com/basic_auth";
        String injected_url="https://username:password@the-internet.herouapp.com/basic_auth";
//        driver.get(url);
        driver.get(injected_url);
    }static void create_alert(WebDriver driver){
        driver.get("https://www.youtube.com/");

        JavascriptExecutor js = (JavascriptExecutor)driver;

        /// simple alert
        js.executeScript("alert('This is a simple alert');");
       wait_time(1000);
        Alert element=driver.switchTo().alert();
        element.accept();
        System.out.println("alert accepted");
        /// confirmation

        js.executeScript("var result = confirm('Do you want to continue?');");
        wait_time(1000);
        element=driver.switchTo().alert();
        element.dismiss();
        System.out.println("confirmed");
        /// prompt
        js.executeScript("var name = prompt('Please enter your name', '');");
        wait_time(1000);
        element=driver.switchTo().alert();
        element.sendKeys("hello people");
        wait_time(1000);
//        element.accept();
        System.out.println("keys sent");
//        driver.quit();


    }
    static void wait_time(long n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
/**
 JavaScript Alert Types and Return Values
 1. prompt() Alert

 Accept: Returns the string entered in input box (or empty string if nothing entered)
 Dismiss/Cancel: Returns null

 2. confirm() Alert

 Accept/OK: Returns true
 Dismiss/Cancel: Returns false
 Never returns null - this is key!

 3. alert() Alert

 Accept/OK: Returns undefined
 No cancel button, so only one option
 * */
