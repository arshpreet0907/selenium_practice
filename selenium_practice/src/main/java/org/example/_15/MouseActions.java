package org.example._15;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Objects;

/**
 * Mouse Actions
    * We can perform mouse actions using inbuilt Actions class
    * make its object and use method to perform operations
    * possible operations are as follows:
        * 1) Move to Element (Hover) - simple and with offset
        * 2) Click
        * 3) Right Click
        * 4) Double Click
        * 5) Drag and Drop - simple and with offset
        * 6) Click and Hold
        * 7) Release
        * 8) Move by Offset
        * 9) Chaining
 * For simple actions we can perform actions directly
 * For complex actions we can chain the operations
 * For performing actions in future we can build actions and store in Action object and perform latter
 * */
/**
 * Action vs Actions
 * */
public class MouseActions {
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        operations(driver);
    }

    static void operations(WebDriver driver){

//        driver.get("https://testautomationpractice.blogspot.com/");
        driver.get("https://demoqa.com/slider");
//        driver.get("https://www.youtube.com");

        /// creating actions class object
        Actions actions=new Actions(driver);

       /// 1) Move to Element (Hover) - simple and with offset
//        hover_action(driver,actions);

        /// 2) Click
//        click_action(driver,actions);

        /// 3) Right Click
//        context_click_action(driver,actions);

        /// 4) Double Click
//        double_click_action(driver,actions);

        /// 5) Drag and Drop - simple and with offset
//        drag_and_drop_action(driver,actions);

        /// 6) Click and Hold
//        click_and_hold_action(driver,actions);

        ///7) Release
//        release_action(driver,actions);

        /// 8) Move by Offset
//        move_by_offset_action(driver,actions);

        /// 9) Chaining
//        chaining_action(driver,actions);

        /// Slider operation
//        slider_action(driver,actions);

        /// Demoqa Interactions
        interactions_demoga(driver,actions);

    }
    static void hover_action(WebDriver driver, Actions actions){

        ///demoqa
//        WebElement slider=driver.findElement(By.xpath("//input[@class='range-slider range-slider--primary']"));
//        actions.moveToElement(slider).perform();

        /// test auto
        WebElement point_me_btn=driver.findElement(By.xpath("//button[@class=\"dropbtn\"]"));
        WebElement laptops=driver.findElement(By.xpath("//div[@class='dropdown-content']/a[2]"));
        actions.moveToElement(point_me_btn).moveToElement(laptops).click().perform();

        WebElement gui_text=driver.findElement(By.xpath("//*[@id=\"Blog1\"]/div[1]/div/div/div/div/h3/a"));
        actions.moveToElement(gui_text).perform();
    }
    static void click_action(WebDriver driver, Actions actions){
        /// demoga
//        driver.get("https://demoqa.com/progress-bar");
//        String percent="1%";
//        WebElement progress_button=driver.findElement(By.xpath("//button[@id=\"startStopButton\"]"));
//        WebElement progress_bar=driver.findElement(By.xpath("//div[@id=\"progressBar\"]"));
//        actions.click(progress_button).perform();
//
//        while (true){
//            if (progress_bar.getText().equals(percent))
//                break;
//        }
//        actions.click(progress_button).perform();
//        System.out.println("progress : "+progress_bar.getText());

        /// test auto
        WebElement button_click=driver.findElement(By.xpath("//button[@name=\"start\"]"));
        actions.moveToElement(button_click).perform();
        actions.pause(Duration.ofSeconds(2)).perform();
        actions.click(button_click).perform();
        actions.pause(Duration.ofSeconds(2)).perform();
        actions.moveByOffset(-30,-30).perform();

    }
    static void context_click_action(WebDriver driver, Actions actions){
        /// demoga
        /*
        WebElement body = driver.findElement(By.tagName("body"));
//        actions.moveByOffset(0, 0)
//                .moveByOffset(30, 30)
//                .contextClick()
//                .perform();
        actions.moveToElement(body,30,30).contextClick().perform();
        // Use Robot to navigate menu
        try {
               Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);  // Navigate down to menu item
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_DOWN);  // Navigate down to menu item
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_DOWN);  // Navigate down to menu item

            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER); // Select item
        }catch (Exception e){}

*/

        /// custom file for button
        driver.get("file:/D:\\training\\selenium\\IdeaProjects\\selenium_practice\\context_button.html");

        WebElement button=driver.findElement(By.xpath("//button[@id='mainButton']"));

        actions.moveToElement(button).perform();
        actions.pause(Duration.ofSeconds(2));
        actions.contextClick(button);

        System.out.println("right clicked");
    }
    static void double_click_action(WebDriver driver, Actions actions){
        driver.get("file:/D:\\training\\selenium\\IdeaProjects\\selenium_practice\\double_click_button.html");

        WebElement input_field_1=driver.findElement(By.id("field1"));
        WebElement input_field_2=driver.findElement(By.id("field2"));

        input_field_1.clear();
        input_field_1.sendKeys("arshpreet");

        /// here getText() will not will for input fields, since it captures the inner text of element
        /// we need to use getAttribute("value") for input fields to get the input text

        WebElement double_click_button=driver.findElement(By.xpath("//button[.='Copy Text']"));
        System.out.println("text in field one is : '"+input_field_1.getAttribute("value")+"'");
        System.out.println("text in field two is : '"+input_field_2.getAttribute("value")+"'");
        actions.pause(Duration.ofSeconds(2));

        actions.click(double_click_button).perform();
        System.out.println("field two text after normal click : '"+input_field_2.getAttribute("value")+"'");
        actions.pause(Duration.ofSeconds(2));

        actions.doubleClick(double_click_button).perform();
        System.out.println("field two text after double click : '"+input_field_2.getAttribute("value")+"'");
        boolean validation= Objects.equals(input_field_2.getAttribute("value"), input_field_1.getAttribute("value"));
        /// .equals() produces null pointer exception so here we used Objects.equals()

        if (validation)
            System.out.println("success");
        else{
            System.out.println("failed");
        }
        driver.quit();
    }
    static void drag_and_drop_action(WebDriver driver, Actions actions){
        /// here we need to first capture the source and target element

        /// test auto
        WebElement source=driver.findElement(By.id("draggable"));
        WebElement target=driver.findElement(By.id("droppable"));

        actions.moveToElement(driver.findElement(By.xpath("//h2[@class='title' and text()='Slider']"))).perform();
        actions.pause(Duration.ofSeconds(2));
        actions.dragAndDrop(source,target).perform();



        /// city to country
//        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

//        WebElement source=driver.findElement(By.xpath("//div[@id=\"box6\"]"));
//        WebElement target=driver.findElement(By.xpath("//div[@id=\"box106\"]"));
//
//        actions.pause(Duration.ofSeconds(2));
//        actions.dragAndDrop(source,target).perform();
    }
    static void click_and_hold_action(WebDriver driver, Actions actions){

    }
    static void release_action(WebDriver driver, Actions actions){}
    static void move_by_offset_action(WebDriver driver, Actions actions){}
    static void chaining_action(WebDriver driver, Actions actions){}
    static void slider_action(WebDriver driver, Actions actions){

//        auto_test_slider(driver,actions);
//demoqa_slider(driver,actions);
    }
    static void auto_test_slider(WebDriver driver,Actions actions){

        WebElement slide_start= driver.findElement(By.xpath("//div[@id=\"slider-range\"]/span[1]"));
        /// getting current location of element
        Point min_start=slide_start.getLocation();
        System.out.println("default location of min slider : "+min_start);

        actions.moveToElement(slide_start).perform();

        WebElement price_range=driver.findElement(By.id("amount"));
        String min_amount;
        do{
            actions.dragAndDropBy(slide_start, -1, 0).perform();
            min_amount=price_range.getAttribute("value").substring(0,2);
        }
        while (!min_amount.equals("$0"));

        Point min_after=slide_start.getLocation();

        System.out.println("location of min slider after moving : "+min_after);



        WebElement slide_end= driver.findElement(By.xpath("//div[@id=\"slider-range\"]/span[2]"));
        Point max_start=slide_end.getLocation();
        /// getting current location of element
        System.out.println("default location of max slider : "+max_start);

        String max_amount;
        do{
            actions.dragAndDropBy(slide_end, 2, 0).perform();
            /// moving by 1 pixel will not work because of minimum movement required to register event, so use a minimum of 2 pixel offset
//            actions.clickAndHold(slide_end)
//                    .moveByOffset(2,0)
//                    .release()
//                    .perform();
            /// here forward offset of 1 pixel doesn't work, it is because of minimum movement required to register event in the browser
            /// using single moveByOffset(1,0) will not work so using two inorder to move the element or just use minimum offset of 2 pixels
            /// using small pauses in between of actions will also not work for single 1 pixel movement

            max_amount=price_range.getAttribute("value");
            assert max_amount != null;
            int len=max_amount.length();
            max_amount=max_amount.substring(len-4,len);
        }
        while (!max_amount.equals("$500"));

        Point max_after=slide_end.getLocation();

        System.out.println("location of max slider after moving : "+max_after);

//        driver.quit();
    }
    static void demoqa_slider(WebDriver driver,Actions actions){

        WebElement slider=driver.findElement(By.xpath("//input[@class=\"range-slider range-slider--primary\"]"));

        String min_value=slider.getAttribute("min");
        String max_value=slider.getAttribute("max");

        System.out.println("min value : "+min_value);
        System.out.println("max value : "+max_value);

        System.out.println("value of slider before : "+slider.getAttribute("value"));
        System.out.println("slider location before : "+slider.getLocation());
//        actions.dragAndDropBy(slider,210,0);
        actions.clickAndHold(slider)
                .moveByOffset(0,0)
                .release()
                .perform();

        /// here slider is of range input type not any drag element, so its location is fixed no matter what
        /// for each move by offset a new value has to be given, since default value is fixed so multiple
        /// offset of 10 will result in same spot on slider
        ///
        System.out.println("Slider size : "+slider.getSize());
        System.out.println("value of slider after : "+slider.getAttribute("value"));
        System.out.println("slider location after : "+slider.getLocation());

//        driver.quit();

    }

    static void interactions_demoga(WebDriver driver,Actions actions){
        /// sortable
        /// selectable
        /// Resizable
        /// Droppable
        /// Draggable
    }
}
