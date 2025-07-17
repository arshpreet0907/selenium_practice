package org.example._8;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NavigationalMethods {
    /**
     * Navigational methods, used via driver object

     * navigate.to(url)
     * navigate.back()
     * navigate.forward()
     * navigate.refresh()
     * */
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        handle_browser_handles(driver);
//        driver.quit();
    }
    static void navigationMethods(WebDriver driver){

        /// 1) driver.navigate.to()

//        driver.get("https://www.yash.com/");
        ///  get takes url only as String

        /// navigate.to() can take String or URL object
        try {
            URL url=new URL("https://www.yash.com/");
            driver.navigate().to(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.navigate().to("https://www.amazon.in/");
        /// implementation of get and to is different but working is similar


        /// 2) driver.navigate().back()

        System.out.println("current url before back is : "+driver.getCurrentUrl());
        driver.navigate().back();
        System.out.println("current url after back is : "+driver.getCurrentUrl());

        /// 3) driver.navigate().forward()
        driver.navigate().forward();
        System.out.println("current url after forward is : "+driver.getCurrentUrl());

        /// 4) driver.navigate().refresh()
        driver.navigate().refresh();
        System.out.println("current url after refresh is : "+driver.getCurrentUrl());

    }
    static void handle_browser_handles(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        WebElement element=driver.findElement(By.xpath("//a[text()='OrangeHRM, Inc']"));
        System.out.println(element.getAttribute("href"));
        element.click();

        Set<String> window_ids=driver.getWindowHandles();
        System.out.println(window_ids);



        /// switch between 1 page to another to shift driver focus, since opening another window doesn't automatically change its focus to new window

        /// approach 1 convert to list
//        List<String> window_id_list=new ArrayList<>(window_ids);
//        String parent_id=window_id_list.get(0);
//        String child_id=window_id_list.get(1);
//
//        System.out.println("before switching title : "+driver.getTitle());
//        driver.switchTo().window(child_id);
//        System.out.println("after switching title : "+driver.getTitle());
//        driver.switchTo().window(parent_id);
//        System.out.println("after again switching title : "+driver.getTitle());

        /// approach 2 traverse over ids and perform actions
        System.out.println("title before switch : "+driver.getTitle());
        for (String id:window_ids){
            driver.switchTo().window(id);
            System.out.println("title : "+driver.getTitle());
            System.out.println("url : "+driver.getCurrentUrl());

            if (driver.getTitle().equals("Human Resources Management Software | OrangeHRM HR Software")){
                driver.close();
            }

            /// can add any validation based on title of each window or other attribute we can perform actions
        }

    }
}
