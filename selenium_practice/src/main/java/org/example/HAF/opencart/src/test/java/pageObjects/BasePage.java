package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;
    /**
     * This class contains only constructor since every page object class will need a constructor for Page Factory init elements
     * */
    BasePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
