package org.example._2;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Locators: identify a web element uniquely
 *          1) locators: can be identified in html directly using By class static methods
 *          2) customized locators : cannot be located in html rather need to specify on our own
 * there is no rule to use particular locator in particular case, we can use any even CSS for xpath
 * */

public class BasicLocators {
    /**
     * used to identify single element
     * specific web elements:
     *  id
     *  name
     *  linktext
     *  partiallinktext
     * group of web elements:
     *  tag_name
     *  class
     * */
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        /// this will full screen the window
        System.out.println(driver.getTitle());

        /// we can locate the element using findElement()
        /// put locator as a parameter which we get from By.name() here By is class with name as a static method
        /// we can only use the available attributes, need to check html for this

        /// 1) name
//        driver.findElement(By.name("search_query")).sendKeys("selenium java");
        /// findElement returns a WebElement object after putting the provided keys
        /// to get multiple element with same attribute we can use findElements()

        /// 2) id
        boolean is_display=driver.findElement(By.id("icp-nav-flyout")).isDisplayed();
        System.out.println(is_display);
        /// check if it is shown or not, works well on images, isDisplayed returns a boolean
        /// it works fine with href tag with link

        /// 3) linked text and partial Linked text
//        driver.findElement(By.linkText("Mobiles")).click();
        /// works only for links helps to locate links in web page
        /// text is not an attribute of a href
        /// this will perform the click action on link


//        driver.findElement(By.partialLinkText("M")).click();
        /// for linked text we need to pass entire text in a href tag but for partial link text we can pass the portion of this a href text.
        /// but prefer the linked text as partial text may generate conflicts on the links, it will select the first element it finds with the text
        /// when multiple matches are available can use findElements() to get the list of web elements

        /// 4) tag_name
        List<WebElement> all_links =driver.findElements(By.tagName("a"));
        System.out.println("total links on web page : "+all_links.size());

        List<WebElement> all_images=driver.findElements(By.tagName("img"));
        System.out.println("total images on web page : "+all_images.size());
        for (WebElement img:all_images){
            //System.out.println(img);
        }
        List<WebElement> all_div=driver.findElements(By.tagName("div"));
        System.out.println("total number of div on web page : "+all_div.size());
        /// cannot be for specific element only for group



        ///5) class

        List<WebElement> category_links=driver.findElements(By.className("nav-li"));
        System.out.println("total number of category links : "+category_links.size());
        /// cannot be for specific element only for group
        /// it captures the visible as well as hidden links
        /// e.g. need: wish to find total number of links on page and compare with expected
        /// here duplication should be allowed so list is used

        for (WebElement link:category_links){
            //System.out.println(link.getLocation());
        }



        driver.close();
        /// this when used to close the last tab to close the socket connect can have a warning which
        /// is not an exception but prints in red, now when multiple tabs are used then this safely
        /// closes current tab. to close the last tab use quit() which safely closes the connection
        /// between selenium and java.net.http



        driver.quit();
    }
    /**
     * Difference between findElement and findElements

     * findElement : returns single web element
     * findElements : returns multiple web element

     * Scenario 1: locator is matching with only one element
     *          findElement : Single web element (WebElement)
     *          findElements : Single web element (List<WebElement>)

     * Scenario 2: locator is matching with multiple web elements
     *          findElement : First web element (WebElement)
     *          findElements : All web elements (List<WebElement>)

     * Scenario 3: locator is not matching with any web element
     *          findElement : Exception (NoSuchElementException)
     *          findElements : Empty List (List<WebElement>)

     * */
}
