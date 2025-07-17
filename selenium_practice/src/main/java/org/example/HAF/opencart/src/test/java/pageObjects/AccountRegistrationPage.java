package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountRegistrationPage extends BasePage{
    /**
     * We reach this page after clicking to register button
     * need to make every method and constructor public
     * */

    String account_registration_url="https://tutorialsninja.com/demo/index.php?route=account/register"; /// own

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
        /// here we call the base page class to initiate the PageFactory
    }

    /// personal details
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement first_name_input;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement last_name_input;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email_input;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephone_input;

    /// pass
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password_input;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirm_password_input;

    /// newsletter
    @FindBy(xpath = "//label[@class='radio-inline']/input[@value='1']")
    WebElement subscribe_yes_radio;
    @FindBy(xpath = "//label[@class='radio-inline']/input[@value='0']")
    WebElement subscribe_no_radio;
    /// policy
    @FindBy(xpath = "//input[@name='agree']")
    WebElement read_policy_check;
    /// continue
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continue_btn;

    /// confirmation : this will find the element because the confirmation message is on same page as the driver current on.
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement confirmation_msg;

    public boolean verifyRegisterPage() {   /// own
        if (driver.getCurrentUrl()!=null)
            return  driver.getCurrentUrl().equals(account_registration_url);
        else return false;
    }
    public void setFirstLast(String firstName){
        first_name_input.sendKeys(firstName);
    }
    public void setLastName(String lastName){
        last_name_input.sendKeys(lastName);
    }
    public void setEmail(String email){
        email_input.sendKeys(email);
    }
    public void setTelephone(String telephone){
        telephone_input.sendKeys(telephone);
    }
    public void setPassword(String password){
        password_input.sendKeys(password);
    }
    public void setConfirmPassword(String confirmPassword){
        confirm_password_input.sendKeys(confirmPassword);
    }
    /**
     * subscription : true means yes and false means no
     * */
    public void setNewsLetterSubscription(Boolean subscription){   /// own
        if (subscription)
            subscribe_yes_radio.click();
        else subscribe_no_radio.click();
    }
    /**
     * if clicked then read policy check box is set to true
     * */
    public void setReadPolicy(){
        read_policy_check.click();
    }
    public void clickContinue(){
        continue_btn.click();
        /// if click is not working we can use the following :

//        continue_btn.submit();

//        Actions a= new Actions(driver);
//        a.moveToElement(continue_btn).click().perform();

//        JavascriptExecutor js=(JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();",continue_btn);

//        continue_btn.sendKeys(Keys.RETURN);

//        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(continue_btn)).click();

    }
    public String getConfirmationMessage(){
        try{
            return confirmation_msg.getText();
            /// no validation to be done here just return the message
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
