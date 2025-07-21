package org.example._17;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;


/**
 * JavaScriptExecutor is an interface implemented by RemoteWebDriver class
 * */
public class JavaScriptExecutorUploadFiles {
    public static void main(String[] args) {
        ChromeDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        driver.get("https://testautomationpractice.blogspot.com/");
        driver.get("https://davidwalsh.name/demo/multiple-file-upload.php");
        /// to test uploading files

        /// javascript click action
//        javascript_click(driver);

        /// javascript send keys action
//        javascript_sendKeys(driver);

        /// scrolling page
//        javascript_scroll(driver);

        /// zooming in out of page
//        javascript_zooming(driver);

        /// minimize maximize resize move window
//        javascript_minimizing(driver);

        /// upload files : not part of javascript executor
        upload_files(driver);
    }
    static void javascript_click(WebDriver driver){
        WebElement radio_btn=driver.findElement(By.id("male"));
//        radio_btn.click();
        JavascriptExecutor js=(JavascriptExecutor) driver;

        Actions actions= new Actions(driver);
        actions.scrollToElement(radio_btn).perform();

        System.out.println("value of execute :"+js.executeScript("arguments[0].click()",radio_btn));

    }
    static void javascript_sendKeys(WebDriver driver){
        WebElement text_field=driver.findElement(By.id("name"));
//        text_field.sendKeys("arsh");
        JavascriptExecutor js= (JavascriptExecutor) driver;

        System.out.println("value of execute :"+js.executeScript("arguments[0].setAttribute('value','Arsh')",text_field));
        /// here passed element will be stored in arguments[0] (predefined array) and javascript has a method setAttribute which can be used to alter any attribute of that element

    }
    static void javascript_scroll(WebDriver driver){
        /// scroll bar is not a web element not a part of application
        /// we cannot inspect the scroll bar
        /// we can scroll using actions object also (actions scroll vs js scroll)
        /// we can scroll in three ways : till a certain pixel value, till a web element is visible, till the end of page

        JavascriptExecutor js= (JavascriptExecutor) driver;

        /// 1) by pixel no
        System.out.println("y offset before scroll : "+js.executeScript("return window.pageYOffset;"));
//        js.executeScript("window.scrollBy(0,300)","");
        js.executeScript("window.scrollBy(0,300)");  ///here first parameter is of x and second is of y,so currently we are scrolling in y direction so x is 0
        /// both work fine
        System.out.println("y offset after pixel scroll : "+js.executeScript("return window.pageYOffset;"));
//        System.out.println("x offset after scroll : "+js.executeScript("return window.pageXOffset;"));

        /// 2) to bring an element into view
        WebElement text_element=driver.findElement(By.xpath("//h2[@class='title' and text()='Static Web Table']"));
        js.executeScript("arguments[0].scrollIntoView();",text_element);
        System.out.println("y offset after element scroll : "+js.executeScript("return window.pageYOffset;"));

        /// 3) till end of page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        System.out.println("y offset after height scroll : "+js.executeScript("return window.pageYOffset;"));
        // Scroll to far right
//        ((JavascriptExecutor)driver).executeScript("window.scrollBy(document.body.scrollWidth, 0)");

        /// smooth scroll down 10px from current position
        js.executeScript("window.scrollTo({top: 10, left: 0, behavior: 'smooth'})");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> {
//            long currentY = ((Number) js.executeScript("return window.pageYOffset")).longValue();
//            return Math.abs(currentY - 10) < 5; // Allow small tolerance
//        });
        System.out.println("y offset after smooth scroll : "+js.executeScript("return window.pageYOffset;"));

        /**
         When you execute window.scrollTo({top: 10, behavior: 'smooth'}), the scrolling happens asynchronously (gradually over time), but JavaScript returns the offset immediately before the animation completes.
         Solution :
                1) add a small sleep() delay to scroll completion : unreliable sometimes
                2) use explicit wait till y offset is near target offset :
                         js.executeScript("window.scrollTo({top: 10, left: 0, behavior: 'smooth'})");
                         new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> {
                         long currentY = (long)((JavascriptExecutor) driver).executeScript("return window.pageYOffset");
                         return Math.abs(currentY - 10) < 5; // Allow small tolerance
                         });
                         System.out.println("Y offset after smooth scroll: " + js.executeScript("return window.pageYOffset;"));

         * */

        /**

         * scrollBy(x, y):
             Relative scrolling: Adds to current scroll position
             Example: If at y=100, scrollBy(0,10) moves to y=110
             Similar to clicking the arrow in a scrollbar

         * scrollTo(x, y):
             Absolute scrolling: Goes to exact coordinates
             Example: scrollTo(0,10) always goes to y=10
             Similar to clicking a specific point in a scrollbar



         * 3. Scrolling Within Specific Elements
         For scrollable containers (divs, sections):

         Vertical Scrolling in Container

         WebElement container = driver.findElement(By.cssSelector(".scrollable-div"));
         // Scroll to bottom
         ((JavascriptExecutor)driver).executeScript(
         "arguments[0].scrollTop = arguments[0].scrollHeight", container);

         // Scroll down by 100px
         ((JavascriptExecutor)driver).executeScript(
         "arguments[0].scrollTop += 100", container);


         Horizontal Scrolling in Container

         // Scroll to far right
         ((JavascriptExecutor)driver).executeScript(
         "arguments[0].scrollLeft = arguments[0].scrollWidth", container);

         // Scroll right by 100px
         ((JavascriptExecutor)driver).executeScript(
         "arguments[0].scrollLeft += 100", container);

         * Practical Usage Examples

         Scroll to Element with Offset

         WebElement element = driver.findElement(By.id("target-element"));
         int yOffset = -100; // Adjust this value as needed

         ((JavascriptExecutor)driver).executeScript(
         "window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.pageYOffset + " + yOffset + ")",
         element);


         Scroll Horizontally to Element

         ((JavascriptExecutor)driver).executeScript(
         "arguments[0].scrollIntoView({block: 'nearest', inline: 'center'})",
         element);
 * */
//        driver.quit();
    }
    static void javascript_zooming(WebDriver driver){
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JavascriptExecutor js=(JavascriptExecutor) driver;
        /// 1) css zoom
        js.executeScript("document.body.style.zoom='200%'");
        /// we are doing this using browser options
        /// we can only access this using javascript executor

        /// 2) css transform
//        js.executeScript("document.body.style.transform='scale(0.5)'");

        /// by default the transformation point is center, but it may cause important elements to go out of view
        /// to change this we can use the following to change the pivot point of transformations
//        js.executeScript("document.body.style.transformOrigin='0,0'");
        /// this changes the pivot point to top left
//        js.executeScript("document.body.style.transform='scale(1.25)'");

        /// for other pivot points we can use the following
        /// '0 0' : top left
        /// 'center' : center (default)
        /// '100% 100%' : bottom right
        /// '50% 0' : top center
        /// '10px 20 px' : specific pixel

        /// this produces more predictable zooming behavior and prevents content displacement (important elements don't go off the screen)

        /// 3) chrome options
//        ChromeOptions options=new ChromeOptions();
//        options.addArguments("--force-device-scale-factor=1.5");
//        WebDriver driver1=new ChromeDriver(options);

        /// 4) firefox specific
//        js.executeScript("document.body.style.MozTransform='scale(1.5)'");
//        js.executeScript("document.body.style.MozTransformOrigin='0 0'");

        /// 5) actions class : using ctrl + +(zoom in); ctrl + -(zoom out); ctrl + 0(zoom reset)
        /// we can also use Keys.chord()
        /// not working this way
//        Actions actions= new Actions(driver);
//        WebElement body = driver.findElement(By.tagName("body"));
//        actions.click(body).perform();
//
//        actions.keyDown(Keys.CONTROL)
//                .sendKeys(Keys.EQUALS)
//                .keyUp(Keys.CONTROL)
//                .perform();

//        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
//        actions.keyDown(Keys.CONTROL).sendKeys("0").keyUp(Keys.CONTROL).perform();


    }
    static void javascript_minimizing(WebDriver driver){

        Actions actions= new Actions(driver);
        /// multiple ways to minimize maximize or move window location

        /// 1) using web driver window management (recommended)
        /// maximize
//        driver.manage().window().maximize();
        /// minimize
//        driver.manage().window().minimize();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        driver.manage().window().maximize();
        /// full screen
//        driver.manage().window().fullscreen();
        /// set size
//        driver.manage().window().setSize(new Dimension(800,600));
        /// set position
//        driver.manage().window().setPosition(new Point(300,200));

        /// 2) javascript attempts (limited functionality) : not working here
        /// js cannot minimize because of security reasons
        JavascriptExecutor js=(JavascriptExecutor) driver;

//        js.executeScript("window.minimize()"); // Exception in thread "main" org.openqa.selenium.JavascriptException: javascript error: window.minimize is not a function
        /// usually blocked by browser

        /// focus / blur

//        js.executeScript("window.blur()");
//        Boolean isActive = (Boolean) js.executeScript("return document.hasFocus()");
//        System.out.println("Window has focus after blur: " + isActive); // Should be false
        /// output true

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//         isActive = (Boolean) js.executeScript("return document.hasFocus()");
//        System.out.println("Window has focus after focus: " + isActive); // Should be false
        /// output true
        /// check why not working

//        js.executeScript("window.focus()");

        /// resize
//        js.executeScript("window.resizeTo(800,600)");
        /// move
//        js.executeScript("window.moveTo(100,100)");

        /// 3) robot class : can work but now very reliable and not a part of selenium
        try {
//            Robot robot= new Robot();
//            robot.keyPress(KeyEvent.VK_ALT);
//            robot.keyPress(KeyEvent.VK_SPACE);
//            robot.keyRelease(KeyEvent.VK_ALT);
//            robot.keyRelease(KeyEvent.VK_SPACE);
//
//            robot.keyPress(KeyEvent.VK_N);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void upload_files(WebDriver driver){
        /// once we click on upload files button a new window opens of file explorer this is not a part of web page
        /// so we cannot inspect it like web elements
        /// we can handle this using sendKeys()
        /// since this choose files is actually an input field
        /// we can upload single or multiple files together for this we need to use single sendKeys()
        /// inside the method we pass the full file path to upload
        /// for a single file pass single file path normally
        /// for multiple file paths pass the string containing all the file paths separated by '\n'
        /// e.g. file_path_1+"\n"+file_path_2
        /// if we use multiple send keys one after the

        WebElement upload_files_btn=driver.findElement(By.xpath("//input[@id=\"filesToUpload\"]"));
        String file_path_1="D:\\training\\selenium\\IdeaProjects\\selenium_practice\\src\\main\\java\\org\\example\\_1webDriverHierarchy.png";
        String file_name_1="_1webDriverHierarchy.png";
        /// single upload
//        upload_files_btn.sendKeys(file_path_1);

        /// verifying file upload
//        String  uploaded_text=driver.findElement(By.xpath("//ul[@id=\"fileList\"]/li")).getText();
//        if (uploaded_text.equals(file_name_1)){
//            System.out.println("uploaded successfully");
//        }
//        else {
//            System.out.println("upload_failed");
//        }

        String file_path_2="D:\\training\\selenium\\IdeaProjects\\selenium_practice\\src\\main\\java\\org\\example\\_2typesOfLocators.png";
        String file_name_2="_2typesOfLocators.png";

        upload_files_btn.sendKeys(file_path_1+"\n"+file_path_2);

        String  uploaded_text=driver.findElement(By.xpath("//ul[@id=\"fileList\"]/li[1]")).getText()+driver.findElement(By.xpath("//ul[@id=\"fileList\"]/li[2]")).getText();
        if ((file_name_1+file_name_2).equals(uploaded_text)){
            System.out.println("uploaded successfully");
        }
        else {
            System.out.println("upload_failed");
        }
        int no_of_files_uploaded=driver.findElements(By.xpath("//ul[@id=\"fileList\"]/li")).size();
        System.out.println("total number of files uploaded : "+no_of_files_uploaded);


        /**
         File upload with hidden input
         Sometimes the input element might be hidden with CSS. In this case, you can make it visible with JavaScript:

         WebElement fileInput = driver.findElement(By.id("hidden-file-input"));
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].style.display='block';", fileInput);
         fileInput.sendKeys("/path/to/file.txt");
         * */

        /**
         // Using Robot class
         // Copy the file path to clipboard
         StringSelection stringSelection = new StringSelection(filePath);
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

         // Create Robot instance
         Robot robot = new Robot();

         // Paste the file path and press Enter
         robot.keyPress(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
         * */

        /// Alternative: Use hidden input approach for drag-drop areas
        /// Look for hidden input and use sendKeys
    }
}
