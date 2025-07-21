package org.example._3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CSSLocators {
    /**
     * CSS Selector Locator
     * collection of multiple attributes hence customized locator
     * we can create own locator

     * types of locators available:
            * tag id : tag#id
            * tag class : tag.classname
            * tag attribute : tag[attribute='value']
            * tag class attribute : tag.classname[attribute='value']
     * tag here is also optional but #,.,[] are compulsory

     *
    */
     public static void main(String[] args) {

         WebDriver driver=new ChromeDriver();
         driver.manage().window().maximize();
//         driver.get("https://demo.nopcommerce.com/");
//         driver.get("https://www.amazon.in");
         System.out.println(driver.getTitle());

         /// here we use By.cssSelector()

         /// 1) tag id
//         driver.findElement(By.cssSelector("input#small-searchterms")).sendKeys("T-shirts");
//         driver.findElement(By.cssSelector("#small-searchterms")).sendKeys("T-shirts");
         /// both works same in this case because id unique for each element

         /// 2) tag classname
//         driver.findElement(By.cssSelector("button.button-1 search-box-button")).click();
//         driver.findElement(By.cssSelector("button.button-1")).click();
         /// when class name is lengthy with multiple parts separated by comma/space then just keep the first part and leave the rest.

//         driver.findElement(By.cssSelector("input.search-box-text")).sendKeys("pants");
         /// here since class can be used to get group of elements following can also be used
//         List<WebElement> input_elements=driver.findElements(By.cssSelector(".search-box-text"));
//         System.out.println("num of search-box-text are : "+input_elements.size());



         /// 3) tag attribute
         /// this is used when multiple attributes other than id and class are there
         /// we can use any attribute of an element
//         driver.findElement(By.cssSelector("input[placeholder=\"Search store\"]")).sendKeys("lower");
         /// in java to put "" inside outer "" then put \ before inner ""
         /// or simply use ''
//         driver.findElement(By.cssSelector("input[placeholder='Search store']")).sendKeys("lower");
//         driver.findElement(By.cssSelector("[placeholder=\"Search store\"]")).sendKeys("lower");
         /// this also works but [] is compulsory so it will return every element where the given attribute has this value, in this case we get only one element

         /// 4) tag class attribute
         /// when multiple elements have same tag and class so it helps to uniquely identify elements
//         driver.findElement(By.cssSelector("input.search-box-text[name='q']")).sendKeys("jacket");

         //         driver.quit();
            driver.get("https://www.youtube.com/");
            driver.findElement(By.cssSelector("input[name='search_query']")).sendKeys("Selenium java");
            driver.findElement(By.cssSelector("button.ytSearchboxComponentSearchButton")).click();




     }
     /**
      * We can also use Selector hub to automatically get the css selector
      * Selector hub is a chrome extension
      * */
}
