package org.example._10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FramesiFrames {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        frames(driver);
    }
    static void frames(WebDriver driver){
        driver.get("https://ui.vision/demo/webtest/frames/");
//        WebElement ele=driver.findElement(By.xpath("//*[@id=\"id2\"]/div/input"));
//        System.out.println(ele.getTagName());
        /// switch to frame first
        /// frame 1
        WebElement frame_1=driver.findElement(By.xpath("//frame[@src=\"frame_1.html\"]"));

//        driver.switchTo().frame("");//id
//        driver.switchTo().frame("");//name

        driver.switchTo().frame(frame_1);//WebElement
        driver.findElement(By.xpath("//input[@name=\"mytext1\"]")).sendKeys("Arshpreet");
//        driver.switchTo().frame("");//FrameIndex  //use when only one frame is present otherwise getting right index is tricky


        /// come back to page
        driver.switchTo().defaultContent();

        /// switch to frame two
        /// frame 2

        WebElement frame_2=driver.findElement(By.xpath("//frame[@src=\"frame_2.html\"]"));
        driver.switchTo().frame(frame_2);
        driver.findElement(By.xpath("//input[@name=\"mytext2\"]")).sendKeys("Singh");

        driver.switchTo().defaultContent();

        /// go to frame 3
        /// frame 3
        WebElement frame_3=driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
        driver.switchTo().frame(frame_3);
        driver.findElement(By.xpath("//input[@name=\"mytext3\"]")).sendKeys("gulati");

        /// inner frame within frame 3
        driver.switchTo().frame(0);//only 1 inner frame
        driver.findElement(By.xpath("//*[@id=\"i9\"]/div[3]/div")).click();


        driver.switchTo().defaultContent();
    }

    /**
     The main difference between <iframe> (inline frame) and <frame> is that <iframe> is used to embed another document within the current HTML document, while <frame> is used to divide the browser window into multiple sections, each displaying a separate document. Frames are part of a frameset, which is used to create a layout with multiple frames. Iframes, on the other hand, can be placed anywhere within the <body> of a page, and they are considered a content-adding element, not a layout element.
     Here's a more detailed breakdown:
     Frames:
     Part of frameset:
     Frames are used within a frameset element, which defines the layout of the page with multiple frames, typically arranged in rows or columns.
     Layout-defining:
     Frames are primarily used to divide the browser window into distinct sections, each displaying a separate HTML document.
     Obsolete:
     Framesets and frames are largely considered obsolete in modern web development and are not recommended for new projects due to various limitations and issues, especially with accessibility and usability.
     Iframes:
     Inline within content:
     An iframe is an inline frame, meaning it can be placed directly within the content of a webpage, just like any other HTML element.
     Content-adding:
     Iframes are used to embed content from another source (another webpage, an ad, a video, etc.) into the current page.
     Commonly used:
     Iframes are still widely used for embedding content from external sources like YouTube videos, social media feeds, and advertisements.
     Can pose security risks:
     Iframes can pose security risks if not used carefully, as they can potentially load malicious content. It's important to validate and whitelist iframe sources and use the sandbox attribute to restrict their capabilities.
     In essence:
     Frames are for dividing the browser window into separate areas, while iframes are for embedding content within a page.
     Frames are part of a deprecated layout structure, while iframes are a standard way to embed content.
     Iframes are generally preferred over frames for embedding content due to their flexibility and wider support.
     * */
}
