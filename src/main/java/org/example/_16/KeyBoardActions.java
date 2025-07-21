package org.example._16;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.Set;

/**
 * Keyboard Actions
        * It is also done using Actions class
        * We can perform key actions by the following three methods:
                * keyDown(Keys.key) : presses key and holds the modifier keys (shift, ctrl, alt)
                * keyUp(Keys.key) : releases key
                * sendKeys(key_sequence) : can be string or keys object
        * We can chain our key actions to mimic the complex actions like ctrl+c, shift+t, etc.
 * */

/// always make the actions instance after navigating to the web page otherwise it can cause stale element exception

public class KeyBoardActions {
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        /// compare text in two area boxes using keyboard shortcut keys
//       text_compare_using_shortcuts(driver);

        /// open link in new tab
        open_link_in_new_tab(driver);

        /// from selenium 4 :
        /// open new tab or window along with the current tab
//        driver.switchTo().newWindow(WindowType.TAB).get("https://www.youtube.com/");
//        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.amazon.in/");

        /// url of empty tab
//        System.out.println("URL of empty tab : '"+driver.getCurrentUrl()+"'");

        /// open new tab using keys
//        driver.get("https://www.youtube.com/");
//        Actions actions = new Actions(driver);
//        actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();
        /// this will not work
        /**
         Selenium's keyboard actions are sent to the web page, not the browser chrome (the browser's own UI)
         The Actions class simulates keyboard events at the DOM level, not the browser application leve
         * */
//        driver.quit();
    }
    static void text_compare_using_shortcuts(WebDriver driver){
        /// here sending text to area 1 using send keys
        /// then using the shortcut keys : ctrl+a ctrl+c tab ctrl+v
        /// to copy text navigate to next area and pasting the text

        driver.get("https://text-compare.com/");
        Actions actions= new Actions(driver);

        String validation_string="Welcome to my code";
        WebElement text_area_1= driver.findElement(By.xpath("//textarea[@id=\"inputText1\"]"));
        text_area_1.sendKeys(validation_string);

        /// ctrl+a
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

        /// ctrl+c
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

        /// tab
//        actions.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
        actions.sendKeys(Keys.TAB).perform();

        /// ctrl+v
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


//        WebElement text_area_2= driver.findElement(By.xpath("//textarea[@id=\"inputText2\"]"));
//        boolean is_valid=validation_string.equals(text_area_2.getAttribute("value"));
//        if (is_valid)
//            System.out.println("validation complete");
//        else
//            System.out.println("validation failed");


        WebElement compare_btn=driver.findElement(By.xpath("//div[@class=\"compareButtonText\"]"));
        compare_btn.click();

        WebElement user_message=driver.findElement(By.xpath("//span[@class='messageForUser']"));
        String msg_for_user=user_message.getText();

        assert msg_for_user.equals("The two texts are identical!") : "Validation failed";
        System.out.println("validation passed");



//        driver.quit();

    }
    static void open_link_in_new_tab(WebDriver driver){

        /// for this we will press control key and click on link to open in new tab

        driver.get("https://www.amazon.in/");
        Actions actions= new Actions(driver);

        WebElement link_to_open=driver.findElement(By.xpath("//a[@href=\"/gp/bestsellers/?ref_=nav_cs_bestsellers\"]"));

        System.out.println("link before new tab : "+driver.getCurrentUrl());

        actions.keyDown(Keys.CONTROL).click(link_to_open).keyUp(Keys.CONTROL).perform();

        System.out.println("link after new tab : "+driver.getCurrentUrl());
        Set<String>window_handles=driver.getWindowHandles();
        for (String handle:window_handles){
            if (!handle.equals(driver.getWindowHandle()))
                driver.switchTo().window(handle);

        }//        driver.quit();

    }

}
/**
 * Robot class of awt package will also work to perform key actions using KeyEvent class
 * its working is slightly different compared to selenium

 The java.awt.Robot class is part of Java's AWT (Abstract Window Toolkit) package. It provides:
 Low-level control over input devices (keyboard and mouse)
 Ability to generate native system input events
 Capability to interact with OS-level dialogs and non-browser elements
 Screen capture functionality

 Use the Robot class when:
 You need to handle OS-level dialogs (file upload/download, authentication popups)
 Selenium's keyboard methods don't work for certain special keys
 You need to interact with non-web elements during your test
 You require precise control over key press/release timing

 // Key press/release
 void keyPress(int keycode)
 void keyRelease(int keycode)

 // Delay between actions
 void delay(int milliseconds)

 // Combined key press/release
 void keyPress(KeyEvent keyEvent)
 void keyRelease(KeyEvent keyEvent)

 VK_ENTER - Enter key
 VK_BACK_SPACE - Backspace key
 VK_TAB - Tab key
 VK_CONTROL - Control key
 VK_ALT - Alt key
 VK_SHIFT - Shift key
 VK_ESCAPE - Escape key
 VK_A to VK_Z - Alphabet keys
 VK_0 to VK_9 - Number keys
 VK_F1 to VK_F12 - Function keys

 Advantages of Robot Class
 OS-Level Interaction: Can handle native dialogs and windows
 Precise Timing: Fine control over delays between actions
 Special Keys: Better support for some special key combinations
 Non-Browser Elements: Can interact with Java applets or Flash content

 Limitations and Considerations
 Focus-Sensitive: Requires the correct window to have focus
 Platform-Dependent: Behavior may vary across operating systems
 Brittle Tests: More susceptible to timing and focus issues
 No Element Binding: Doesn't interact directly with DOM elements
 Headless Mode: Doesn't work in headless browser mode

 Best Practices with Robot Class
 Use as Last Resort: Prefer Selenium's built-in methods first
 Add Delays: Include appropriate delays between actions
 Focus Management: Ensure correct window/element has focus
 Error Handling: Implement robust exception handling
 Platform Awareness: Account for OS-specific behaviors
 Combine with Selenium: Use for only the parts that need it

 Practical Use Cases
 File Upload
 Authentication Dialogs
 Download Dialogs
 * */
