package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    String my_account_page_url="https://tutorialsninja.com/demo/index.php?route=account/account";   /// own
    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='My Account']") /// my account page heading
    WebElement msgHeading;

    @FindBy(xpath = "//a[@class='list-group-item' and text()='Logout']")
    WebElement logout_btn;   /// for step 6

    public boolean verifyMyAccountPage(){   /// own
        if (driver.getCurrentUrl()!=null)
            return  driver.getCurrentUrl().equals(my_account_page_url);
        else return false;
    }
    public boolean isMyAccountExists(){
        try {
            return msgHeading.isDisplayed(); /// check if it is displayed or not, if not then it throws exception
        }
        catch (Exception e){
            return false;
        }
    }
    public void clickLogout(){
        logout_btn.click();
    }


}
