package org.example._1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * First Test case:
 * 1) Launch browser
 * 2) open url : https://demo.opencart.com/
 * 3) validate title should be "Your Store"
 * 4) close browser
 * */


public class FirstTestCase {
    public static void main(String[] args) {
        // We will use web driver methods to perform all the tasks.

        // 1) Launch browser
        // first make chrome browser object
//        WebDriver driver_chrome= new ChromeDriver();
//          WebDriver driver_fire= new SafariDriver(); // exception
        WebDriver driver_edge= new EdgeDriver();

        // ChromeDriver driver= new ChromeDriver();
        // just making object it launches browser

        // 2) open url : https:www.youtube.com"
//        driver.get("https:www.youtube.com");
//         driver_edge.get("https://demo.nopcommerce.com");
         driver_edge.get("https://www.homer.com/");
        //need to pass the url as String and opens the url on browser

        // 3) validate title should be "youtube"
        String title=driver_edge.getTitle();
        if (title.equals("nopCommerce demo store. Home page title")){
            System.out.println("test passed");
        }
        else{
//            System.out.println("test failed");
            System.out.println("title : "+title);
        }

        // 4) close browser
        driver_edge.close();
//        driver_edge.quit();
        /**
         * 1. driver.close()
         * Closes the current browser window that the WebDriver is controlling.
         * If only one window/tab is open, it will close the entire browser session.
         * If multiple windows/tabs are open, it will only close the current active window, leaving other windows/tabs open.
         * The WebDriver session (driver object) remains active unless it was the last window.
         *
         * 2. driver.quit()
         * Closes all browser windows/tabs associated with the WebDriver session.
         * Terminates the WebDriver session, releasing system resources.
         * This is the recommended method to use at the end of a test script to ensure cleanup.
         * */
        System.out.println("hello");
    }
}
