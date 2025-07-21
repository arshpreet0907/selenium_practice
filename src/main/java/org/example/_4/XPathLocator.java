package org.example._4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * X Path is mostly preferred to locate the element on web page
 * it is derived from XML path
 * it is generated through DOM (Document Object Model)
 * DOM is the structure of elements in dev tools in inspect
 * we can capture x path of any element from DOM

 * In order to get the X Path inspect the particular element select the element's tag and attribute right click select copy then chose X path or Full X path
 * types of X Path :
        * 1) Absolute X Path (Full X Path) : it gets the actual hierarchy of nodes from html tag to target element, no attributes are used
                * e.g. : /html/body/div[6]/div[1]/div[2]/div[2]/form/input
        * 2) Relative X Path (Partial X Path) : directly jumps to element using attributes, does not navigate from each tag name
                * e.g. : //*[@id="small-searchterms"]
        * 3) Chained X Path : Combination of relative and absolute X path, used when no attribute or inner text is available to write relative X path, then got to parent if relative X path is possible then good otherwise go to its parent again repeat till we have a parent element with a Valid Relative X Path then form absolute X Path from this element to target element
                * e.g. : //div[@id='logo']/a/img
 *  Which X Path is preferred?
        * Relative X Path : because in real world scenario developer may change the hierarchy of elements, introduce new element or reposition the target element, which in turn changes the absolute X Path. This problem is not present in Relative X Path.
 * Difference between absolute and relative X path :
        * 1) Jumps of levels
                * abs starts with single slash / : can navigate only one level
                * relative starts with double slash // : can navigate multiple levels
        * 2) Use of attributes
                * abs does not use any attributes
                * relative uses attributes
        * 3) Searching of element
                * abs goes from each tag(Node) till it finds the element
                * relative can directly jump to element using attributes
 * Performance is almost similar

 * Relative X Path
        * 1) Automatically using Dev tools or Selector hub
        * 2) Manually (own x path) : follow certain syntax
                * //tag_name[@attribute='value']
                * //*[@attribute='value']
                * here tag_name is optional but on its place we put regular expression *
                * here we can give one or multiple attribute with syntax : //tag_name[@attribute1='value1'][@attribute2='value2']

 * Operators on x path : 'and' & 'or' operators
        * //input[@name='q' and @placeholder='Search store'] //input[@name='q' or @placeholder='Search store1']

 * We can also locate element with relative x path without attribute  by using inner text
 * finding element using inner text : by using text() method
        * //tag_name[text()='inner text']
 * Instead of text() we can use . as well:
        * //tag_name[.='inner text']
 * all linked text are inner text but not vice versa

 * Operations possible :
        * on link : click()
        * on label : getText(), isDisplayed()

 * Handling dynamically changing values of attribute and still able to locate element
 * We can use two methods contains and starts-with
 * When the changing values have something in common for the same attribute of an element then this can be used
 * X Path with contains() method :
        * we can identify elements based on partial value of attribute
        * //input[contains(@placeholder,'earch')]
 * X Path with starts-with() method :
        * we can identify elements based on starting value of attribute
        * //input[starts-with(@placeholder,'Sea')]

 * Based on the situation we need to choose the different approached to find the element, since different methods are applicable in different scenarios


 *  */
public class XPathLocator {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.amazon.in/");
        System.out.println(driver.getTitle());
        driver.manage().window().maximize();

        /// 1) relative X path with single attribute
//        driver.findElement(By.xpath("//*[@title='Show products in category Electronics']")).click();

        /// 2) relative x path with multiple attributes
//        driver.findElement(By.xpath("//input[@name='q'][@placeholder='Search store']")).sendKeys("shoes");

        /// 3) operators on x path
        /// and operator both values given should be true then only it can locate the element
//        driver.findElement(By.xpath("//input[@name='q' and @placeholder='Search store']")).sendKeys("shoes");

        /// 4) or operator either of the values given is true, it can locate the element
//        driver.findElement(By.xpath("//input[@name='qw' or @placeholder='Search store']")).sendKeys("shoes");

        /// 5) X path with inner text
//        WebElement text=driver.findElement(By.xpath("//h2[text()='Under ₹499 | Deals on home improvement essentials']"));
//        WebElement text=driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[1]/div[5]/div/div[1]/h2"));
//        WebElement text=driver.findElement(By.xpath("//span[text()='Starting ₹349 | boult']"));
//        WebElement text=driver.findElement(By.xpath("//h2[text()='Automotive essentials | Up to 60% off']"));
        WebElement text=driver.findElement(By.xpath("//h2[.='Automotive essentials | Up to 60% off']"));
        System.out.println(text);
//        text.click();
        String text_value=text.getText();
        System.out.println(text_value);
        // //*[@id="CardInstancexba1bT7kp8CCsUa6eeq-sQ"]/div[1]/h2
        // /html/body/div/div[1]/div[2]/div[1]/div[5]/div/div[1]/h2

       /// 6) X path with contains()
//        driver.findElement(By.xpath("//input[contains(@placeholder,'Amazon')]")).sendKeys("pajamas");
//        driver.findElement(By.xpath("//input[@type='submit'][@value='Go']")).click();

        /// 7) X Path with start-with()
        driver.findElement(By.xpath("//input[starts-with(@placeholder,'Search A')]")).sendKeys("pajamas");
//        driver.findElement(By.xpath("//input[@type='submit'][@value='Go']")).click();

        /// 8) Chained X Path
        driver.findElement(By.xpath("//a[@aria-label='Starting ₹199 | Bedsheets']/div[2]/span")).click();
        // //*[@id="main"]/div/div/div/div/div[3]/div/div[1]/div/div/a/img
        // /html/body/div[6]/div[3]/div/div/div/div/div[3]/div/div[1]/div/div/a/img

    }


}
