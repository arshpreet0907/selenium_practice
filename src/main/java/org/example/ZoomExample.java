package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
/// not working
public class ZoomExample {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com");

        driver.manage().window().maximize();
        Actions actions = new Actions(driver);
        WebElement body = driver.findElement(By.tagName("body"));

        // Ensure the page has focus
        body.click();

        // Zoom in multiple times
        for (int i = 0; i < 3; i++) {
            actions.sendKeys(body, Keys.chord(Keys.CONTROL, Keys.ADD)).perform();
            Thread.sleep(1000);
        }

        // Zoom out multiple times
        for (int i = 0; i < 3; i++) {
            actions.sendKeys(body, Keys.chord(Keys.CONTROL, Keys.SUBTRACT)).perform();
            Thread.sleep(1000);
        }

        // Reset zoom
        actions.sendKeys(body, Keys.chord(Keys.CONTROL, "0")).perform();

//        driver.quit();
    }
}