package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    /**
     * This page is for login, we need to provide the email and password then click on login button
     * */

    String login_page_url="https://tutorialsninja.com/demo/index.php?route=account/login"; /// own

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement user_email_input;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password_input;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement login_btn;

    public boolean verifyLoginPage(){  /// own
        if (driver.getCurrentUrl()!=null)
            return  driver.getCurrentUrl().equals(login_page_url);
        else return false;
    }
    public void setEmail(String email){
        user_email_input.clear();  /// own
        user_email_input.sendKeys(email);
    }
    public void setPassword(String password){
        password_input.clear();  /// own
        password_input.sendKeys(password);
    }
    public void clickLogin(){
        login_btn.click();
    }
}
