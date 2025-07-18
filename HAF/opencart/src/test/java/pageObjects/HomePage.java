package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    /**
     * This is for the home page
     * need to make every method and constructor public
     * */

    String home_page_url="https://tutorialsninja.com/demo/"; /// own

    public HomePage(WebDriver driver){
        super(driver);
        /// here we call the base page class to initiate the PageFactory
    }

    @FindBy(xpath = "//ul[@class=\"list-inline\"]/li[2]")
    WebElement my_account_drop;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement register_btn;

    /// for login test step 5
    @FindBy(xpath = "//a[text()='Login']")
    WebElement login_btn;

    public boolean verifyHomePage(){  /// own
        if (driver.getCurrentUrl()!=null)
            return  driver.getCurrentUrl().equals(home_page_url);
        else return false;
    }
    public void clickMyAccount(){
        my_account_drop.click();
    }
    public void clickRegister(){
        register_btn.click();
    }
    public void clickLogin(){
        login_btn.click();
    }


}
