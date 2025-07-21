import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Practice {
    public static void main(String[] args) {
        alert();

    }
    static void alert(){
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-web-security");
//        options.addArguments("--allow-running-insecure-content");
//        options.addArguments("--disable-features=VizDisplayCompositor");

        options.addArguments("--headless");  // disables the ui of browser but functionality works fine

        WebDriver driver=new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("file:D:\\training\\selenium\\IdeaProjects\\selenium_practice\\src\\main\\java\\org\\example\\prompt_test.html");
//        driver.get("https://www.youtube.com");
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("window.promptResult = prompt('Please enter name', 'hello');");

//        js.executeScript("window.confirmResult = confirm('Do you want to continue?');");
//        js.executeScript("window.Result =alert('This is a simple alert');");

        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert detected!");

        String currentText = alert.getText();
        System.out.println("Alert text: " + currentText);

//        alert.sendKeys("\u0008"); // Backspace
        alert.sendKeys("hello people saying good morning");
        System.out.println("Keys sent successfully");

       wait_time(2000);
        alert.accept();
        System.out.println("Alert accepted");


        Object result = js.executeScript("return window.promptResult;");
        System.out.println("result: " + result);
        wait_time(2000);
        driver.quit();

    }
    static void wait_time(long n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static void string_intern(){
        String b= new String("hello").intern();
        String a="hello";
        String c="hello";
        System.out.println(a==b);
        System.out.println(a==c);
    }
}
/**
 Browser Security: Modern browsers limit how much control external scripts have over native dialogs

 WebDriver Limitation: Selenium can send the keys but can't force the browser to update the visual display

 Still Functional: The important part (getting the correct value) works perfectly
 * */

/**
 // Create a simple test page programmatically
 String html = """
 <html>
 <body>
 <h1>Prompt Test</h1>
 <script>
 setTimeout(function() {
 window.promptResult = prompt('Please enter name', 'default text');
 console.log('Prompt result:', window.promptResult);
 }, 2000);
 </script>
 </body>
 </html>
 """;

 driver.get("data:text/html;charset=utf-8," + html);
 * */
