package org.example._6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.lang.System;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

/**
 * We can interact with different elements in different ways using web driver methods
 * different elements allow different actions to be performed on them

 * Methods :
        * 1) get methods
        * 2) conditional methods
        * 3) browser methods
        * 4) navigational methods
        * 5) wait methods
 * */
public class WebDriverMethods {
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
//        get_methods(driver);
//        conditional_methods(driver);
          browser_methods(driver);
    }
    static void get_methods(WebDriver driver){


        /// 1) Get methods

        /// 1) get(url)
        driver.get("https://www.yash.com/");
        out.println("1) successfully window opened");
        /// 2) getTitle()
        String title=driver.getTitle();
        out.println("2) Title of page : "+title);
        /// 3) getCurrentUrl()
        String current_url = driver.getCurrentUrl();
        out.println("3) current url : "+current_url);
        /// 4) getPageSource() source code of web page
        String page_source=driver.getPageSource();
        String file_name="yash_page_source.html";
        try (FileWriter writer = new FileWriter(file_name)){
            writer.write(page_source);
            out.println("4) Source code written to file : "+file_name+" successfully");
        }
        catch (Exception e){
            out.println(e);
            out.println("4) page source writing to file failed");
        }
        /// 5) getWindowHandle() changes dynamically
        String current_window_id=driver.getWindowHandle();
        out.println("5) current window id : "+current_window_id);
        /// 6) getWindowHandles()
        driver.findElement(By.xpath("//*[@id=\"customHeader\"]/div/nav/div[1]/div/a[2]")).click();
        Set<String> window_ids=driver.getWindowHandles();
        out.println("6) All the window ids are : "+window_ids);

//        List<String> list=new ArrayList<>(window_ids);
//        for (String l: list)
//            System.out.println(l);

//        driver.manage().window().maximize();


        driver.quit();
    }
    static  void conditional_methods(WebDriver driver){
        driver.get("https://demo.nopcommerce.com/register");

        /// 2) Conditional methods

        /// 1) isDisplayed()
       boolean is_displayed= driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img")).isDisplayed();
        out.println("element is displayed : "+is_displayed);

       /// 2) isEnabled()
        boolean is_enabled=driver.findElement(By.xpath("//*[@id=\"FirstName\"]")).isEnabled();
        out.println("element is enabled : "+is_enabled);

       /// 3) isSelected()
        WebElement button=driver.findElement(By.xpath("//*[@id=\"gender-male\"]"));
        boolean is_selected=button.isSelected() ;
       out.println("element is selected before click : "+is_selected);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        button.click();
        is_selected=button.isSelected() ;
        out.println("element is selected after click : "+is_selected);
        driver.quit();
    }
    static void browser_methods(WebDriver driver){
        long start_time=currentTimeMillis();
        driver.get("https://www.yash.com/");
        long open_time=currentTimeMillis();
        out.println("time open : "+(open_time-start_time));
        /// 3) Browser methods

        /// 1) close()
        try {
            driver.close();
            out.println("time to close : "+(currentTimeMillis()-open_time));
            open_time=currentTimeMillis();
        }
        catch (Exception e){
            out.println(e.getMessage());
        }
        /// 2) quit()
        if (driver!=null){
            driver.quit();

            out.println("time to close after error: "+(currentTimeMillis()-open_time));
        }
    }
}
