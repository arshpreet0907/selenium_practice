package org.example._27;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginPage {
    // "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"

    /// here we are doing it without PageFactory class
    /**
     * 3 parts
     * 1) constructor : initiate the driver
     * 2) locators : By objects for elements with x paths
     * 3) action methods : perform actions on elements
     */

    /// 1) constructor
    WebDriver driver;
    LoginPage(WebDriver driver){ /// get this driver from test
        this.driver=driver;
    }

    /**
     * driver.findElement(By.xpath("//input[@placeholder='Username']"));
     * we break this statement into two parts :
     * 1) locator (By type object) : By loc=By.xpath("//input[@placeholder='Username']");
     * 2) finding element : driver.findElement(loc); then using this we can perform the actions in action methods
     * */

    /// 2) locators
    By txt_username_loc=By.xpath("//input[@name='username']");
    By txt_password_loc=By.xpath("//input[@name='password']");
    By btn_login_loc=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");

    /// to generate the x paths for all the elements at once we can use selector hub :
    /// set driver command to append on selectors : specify the format , select the element and copy the x path
    /// click to generate locators page and multiple selectors -> arrow mark -> click on element (xpath gets generated)

    /// 3) action methods

    public void setUserName(String userName){
        driver.findElement(txt_username_loc).sendKeys(userName);
    }
    public void setPassword(String password){
        driver.findElement(txt_password_loc).sendKeys(password);
    }
    public void clickLogin(){
        driver.findElement(btn_login_loc).click();
    }


}
