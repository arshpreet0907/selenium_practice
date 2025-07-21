package org.example._19;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class BrokenLinksSVGElementsShadowDOM {
    static class HandlingBrokenLinks{
        /**
         * Broken links are the urls which do not have a corresponding resource at the server side to return
         * in such case we get some sort of error message along with some code
         * if a link has resource at server it will return that resource also it will return some return code
         * if the status code is >= 400 : it is a broken link
         * if the status code is < 400 : it is not a broken link
         * steps to check if a link is broken or not :
                    * for each link get the href attribute and its value, each link must contain both these
                    * hit the href value (url) to the server
                    * get the status code and check if it is a broken link or not using the above, mentioned condition


         * */
        public static void main(String[] args) {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("http://www.deadlinkcity.com/");
//            List<WebElement> links=driver.findElements(By.xpath("//*[@id=\"maintext\"]/div[1]/ul/li/a")); // all the error codes
            List<WebElement> links=driver.findElements(By.xpath("//a")); // all the links on page
            System.out.println("total links : "+links.size());

            /// we need 3 filters :

            int i=1;
            int no_of_broken_links=0,no_of_non_broken_links=0,no_of_errors=0;
            for (WebElement link:links){
                System.out.print((i++)+" ");
                String href_value=link.getAttribute("href");
                /// 1) check if href has value or not
                if (href_value==null||href_value.isEmpty()){
                    System.out.println("href attribute is null or empty. so not possible to check");
                    continue;
                }
                else {
                    System.out.print(href_value+" ==> ");
                }
                /// 2) some url is present at href value, send it to server
                    /// convert string to url object
                    /// get the HttpURLConnection object from java.net
                    /// now connect the server using this object to hit the url to server (request to server)
                try {
                    URL linkurl=new URL(href_value);
                    HttpURLConnection conn=(HttpURLConnection) linkurl.openConnection();
                    conn.connect();
                    /// 3) compare the response code to check if link is broken or not
                    int response_code=conn.getResponseCode();
                    if (response_code>=400){
                        System.out.println("Broken link : "+response_code);
                        no_of_broken_links++;
                    }
                    else{
                        System.out.println("not a broken link : "+response_code);
                    no_of_non_broken_links++;}

                }  catch (Exception e) {
                    System.out.println(e.getMessage());
                    no_of_errors++;
                }
            }

            System.out.println("\nnumber of broken links : "+no_of_broken_links);
            System.out.println("\nnumber of non broken links : "+no_of_non_broken_links);
            System.out.println("\ntotal number of errors : "+no_of_errors);
            driver.quit();

        }


        /**
         Status codes for broken links :
         Errorcode 400 - Bad Request
         Errorcode 401 - Unauthorized
         Errorcode 402 - Payment Required
         Errorcode 403 - Forbidden
         Errorcode 404 - Not Found
         Errorcode 405 - Method Not Allowed
         Errorcode 406 - Not Acceptable
         Errorcode 407 - Proxy Authentication Required
         Errorcode 408 - Request Timeout
         Errorcode 409 - Conflict
         Errorcode 410 - Gone
         Errorcode 411 - Length Required
         Errorcode 412 - Precondition Failed
         Errorcode 413 - Request Entity Too Large
         Errorcode 414 - Request-URI Too Long
         Errorcode 415 - Unsupported Media Type
         Errorcode 416 - Requested Range Not Satisfiable
         Errorcode 417 - Expectation Failed
         Errorcode 420 - Enhance Your Calm
         Errorcode 422 - Unprocessable Entity
         Errorcode 423 - Locked
         Errorcode 424 - Failed Dependency
         Errorcode 429 - Too Many Requests
         Errorcode 431 - Request Header Fields Too Large
         Errorcode 450 - Blocked by Windows Parental Controls
         Errorcode 500 - Internal Server Error
         Errorcode 501 - Not Implemented
         Errorcode 502 - Bad Gateway
         Errorcode 503 - Service Unavailable
         Errorcode 504 - Gateway Timeout
         Errorcode 505 - HTTP Version Not Supported
         Errorcode 506 - Variant Also Negotiates
         Errorcode 507 - Insufficient Storage
         Errorcode 509 - Bandwidth Limit Exceeded
         Errorcode 510 - Not Extended
         Non-existent page link to http://www.deadlinkcity.com/page-not-found.asp
         Non-existent domain link to http://www.domaindoesnot.exist
         301 redirection to a valid link on this site
         302 redirection to a valid link on this site
         301 redirection to an invalid link
         302 redirection to an invalid link
         Double 301 redirection to an invalid link
         Double 302 redirection to an invalid link
         Missing, disallowed by robots.txt
         Missing, allowed by robots.txt
         Inline Style, background image exists
         Inline Style, background image missing
         HTML Style element, background image exists
         HTML Style element, background image missing
         External Style element, background image exists
         External Style element, background image missing
         * */
    }
    static class SVGElements{
        /**
         * These are geometric shapes usually seen as logos
         * they look like tags on html but direct x path won't work with them
         * locating them is quite different compared to other type of elements
         * here direct x path won't work
         * we need to use the name() method only
         * */
        public static void main(String[] args) {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")).click();

            /// x path locator will not work here
            /// abs x path
//            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[3]/a/svg")).click();
            /// .NoSuchElementException

//            WebElement svg=driver.findElement(By.xpath("//svg[@xmlns='http://www.w3.org/2000/svg']"));
            /// NoSuchElementException

            /// relative x path using axes to locate the svg element
//            WebElement svg=driver.findElement(By.xpath("//span[text()='Time']/preceding-sibling::*"));
//            String svg_tag=svg.getTagName();
//            System.out.println("tag name is : '"+svg_tag+"'");
//            svg.click();
            /// works

            /// here we need to use name() method only to locate the svg elements
//            driver.findElement(By.xpath("//(a[@class=\"oxd-main-menu-item\"])[4]//svg")).click(); // InvalidSelectorException
            WebElement element=driver.findElement(By.xpath("(//a[@class='oxd-main-menu-item'])[4]//*[name()='svg']"));
            System.out.println(element.getTagName());
            element.click();
            /// works
            /// locating a tag then using name method to locate svg tag

        }
    }
    static class ShadowDOM{
        /**
         * Shadow DOM, Shadow Host, Shadow Root, Shadow Element
         * */
        public static void main(String[] args) {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            /// getting shadow host then root, then actual element
            three_level_shadow_elements(driver);

            /// practicing shadow elements
//            new_shadow(driver);
        }
        static void three_level_shadow_elements(WebDriver driver){
            driver.get("https://dev.automationtesting.in/shadow-dom");
            /// x path won't work for shadow elements we need to use css selector
            /// here we have shadow element, nested shadow element and multi nested shadow element

            /// 1) getting element inside single shadow dom
            SearchContext shadow=driver.findElement(By.cssSelector("#shadow-root")).getShadowRoot();

            shadow.findElement(By.cssSelector("#shadow-element"));

            /// 2) getting nested shadow element
            SearchContext shadow_0=driver.findElement(By.cssSelector("#shadow-root")).getShadowRoot();
            SearchContext shadow_1=shadow_0.findElement(By.cssSelector("#inner-shadow-dom")).getShadowRoot();
            shadow_1.findElement(By.cssSelector("#nested-shadow-element"));

            /// 3) getting multi nested shadow element
            SearchContext shadow__0=driver.findElement(By.cssSelector("#shadow-root")).getShadowRoot();
            SearchContext shadow__1=shadow__0.findElement(By.cssSelector("#inner-shadow-dom")).getShadowRoot();
            SearchContext shadow__2=shadow__1.findElement(By.cssSelector("#nested-shadow-dom")).getShadowRoot();
            String multi_nested_shadow_text=shadow__2.findElement(By.cssSelector("#multi-nested-shadow-element")).getText();
            System.out.println("multi_nested_shadow_text : "+multi_nested_shadow_text);

        }
        static void new_shadow(WebDriver driver){

            driver.get("https://books-pwakit.appspot.com/");

            /// trying to find the shadow element without shadow dom
//            driver.findElement(By.cssSelector("#input")).sendKeys("hello world");
            /// NoSuchElementException

            /// x path doesn't work with shadow dom
            SearchContext shadow_1=driver.findElement(By.xpath("//body/book-app")).getShadowRoot();
            shadow_1.findElement(By.cssSelector("#input")).sendKeys("hello world");
        }
    }
}
