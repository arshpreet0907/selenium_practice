package org.example._15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class DragAndDropTest {
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://demo.guru99.com/test/drag_drop.html");

        WebElement draggable_5000= driver.findElement(By.xpath("//*[@class='block13 ui-draggable' and @data-id='2']"));
        WebElement draggable_Bank= driver.findElement(By.xpath("//*[@class='block14 ui-draggable']"));
        WebElement draggable_Sales= driver.findElement(By.xpath("//*[@class='block15 ui-draggable']"));

        Actions actions= new Actions(driver);

        List<WebElement> placeholders= driver.findElements(By.xpath("//li[@class='placeholder']"));

        actions.clickAndHold(draggable_5000).moveToElement(placeholders.get(1)).release().perform();
        actions.clickAndHold(draggable_5000).moveToElement(placeholders.get(3)).release().perform();
        actions.clickAndHold(draggable_Bank).moveToElement(placeholders.get(0)).release().perform();
        actions.clickAndHold(draggable_Sales).moveToElement(placeholders.get(2)).release().perform();

        String final_msg=driver.findElement(By.xpath("//a[@class='button button-green']")).getText();
        if (final_msg.equalsIgnoreCase("Perfect!")){
            System.out.println("test passed");
        }
        else System.out.println("test failed");
        driver.quit();

    }
}
