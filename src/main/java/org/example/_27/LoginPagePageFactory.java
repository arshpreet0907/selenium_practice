package org.example._27;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPagePageFactory {
    /**
     * Here will be using the PageFactory class
     * now we do not need to use findElement methods
     * 1) creating constructor is quite different adding the PageFactory class
     * 2) for locators we use @FindBy annotation along with xpath attribute (can also use other type of locators)
          to specify the xpath of element, and directly store it in the web element object. combining the locator and findElement together, creating the WebElement objects directly
     * 3) here just refer the web element and perform actions without findElement method
     * */

    /// 1) constructor
    WebDriver driver;
    LoginPagePageFactory(WebDriver driver){ /// get this driver from test
        this.driver=driver;
        /// initializing the PageFactory class of selenium : methods => initElements()->4, proxyFields(), instantiatePage
        PageFactory.initElements(driver,this);
        /// applied to all locators internally, so we can locate elements without driver.findElement()
    }

    /// 2) locators
    /// 2 ways to declare the locator:

    /// 1) simply locator type and value : xpath, tagName, id, css, className, etc
    @FindBy(xpath = "//input[@name='username']")
    WebElement txt_username;

    /// 2) using how and using keywords
    @FindBy(how= How.XPATH,using= "//input[@name='password']")
    WebElement txt_password;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
    WebElement btn_login;

    /// to capture multiple elements e.g. : all links, just specify the List of web element instead of single web element
    @FindBy(tagName = "a")
    List<WebElement> allLinks; /// if we put some other type of Class in generics then it will not put inside the object, allLinks will be null


    /// 3) action methods

    public void setUserName(String userName){
        txt_username.sendKeys(userName);
    }
    public void setPassword(String password){
        txt_password.sendKeys(password);
    }
    public void clickLogin(){
        btn_login.click();
    }
    public void getAllLinks(){

        for (WebElement e:allLinks){
            System.out.println(e.getAttribute("href"));
        }
    }

}
