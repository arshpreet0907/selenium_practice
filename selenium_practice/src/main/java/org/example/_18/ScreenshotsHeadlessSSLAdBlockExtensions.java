package org.example._18;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ScreenshotsHeadlessSSLAdBlockExtensions {
    static class  ScreenShots{
        /**
         * TakesScreenshot is an interface which is implemented using RemoteWebDriver class
         * using this we can take full page screenshots
         * from selenium 4 onwards we can take screenshots of specific area and of specific element as well

         * Types of screenshot :
         * 1) full page
         * 2) specific area on screen
         * 3) specific element
         * We can use TakesScreenshot interface, WebElement methods to capture screenshot
         * for more system level control we can use robot class (not a part of selenium)
         * Editing of screenshot is not possible using selenium
         * after getting it in img we can edit it latter

         * Whenever our test case fails we take screenshot : post actions, using testng listeners
         * To take specific size screenshot resize window and take screenshot
         * */
        public static void main(String[] args) {
            WebDriver driver= new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://testautomationpractice.blogspot.com/");

            /// 1) full page screenshot
//        full_page_screenshot(driver);

            /// 2) specific region screenshot
//        specific_region_screenshot(driver);

            /// 3) specific element screenshot
//            specific_element_screenshot(driver);

            /// 4) bytes array screenshot
            byte_array_screenshot(driver);

            /// 5) base 64 screenshot
            base_64_screenshot(driver);

            /// not inbuilt selenium:

            /// 6) take screenshot using robot class
//        take_screenshot_robot_class(driver);

            /// 7) time stamp screenshot
//            time_stamp_screenshot(driver);

            /// 8) full page scrolling screenshot
//            full_page_scrolling_screenshot(driver);
        }
        static void full_page_screenshot(WebDriver driver){
            TakesScreenshot ts=(TakesScreenshot) driver;
            File src=ts.getScreenshotAs(OutputType.FILE);
            /// capture screenshot as file type
            /// gets only the webpage screenshot

            File target=new File(System.getProperty("user.dir")+"\\screenshots\\full_page.png");
            /// instead of hardcoding the entire file path we can give this way
            /// this takes the path of project folder dynamically and adds the provided path

            /// copy the source file to target file
            src.renameTo(target);
        }
        static void specific_region_screenshot(WebDriver driver){

            /// we are calling getScreenshotAs() from WebElement object but have the same functionality

            WebElement target_region=driver.findElement(By.xpath("//div[@id=\"HTML1\"]"));
            File src=target_region.getScreenshotAs(OutputType.FILE);
            File target=new File(System.getProperty("user.dir")+"\\screenshots\\specific_region.png");
            src.renameTo(target);
        }
        static void specific_element_screenshot(WebDriver driver){

            /// here instead of group of elements we are taking screenshot of specific element
            WebElement logo_img=driver.findElement(By.xpath("//img[@class=\"wikipedia-icon\"]"));

            File src=logo_img.getScreenshotAs(OutputType.FILE);
            File target=new File(System.getProperty("user.dir")+"\\screenshots\\specific_element.png");
            src.renameTo(target);
        }
        static void byte_array_screenshot(WebDriver driver){
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte []arr=screenshot.getScreenshotAs(OutputType.BYTES);
            System.out.println(arr.length);
//            driver.quit();

        }

        static void base_64_screenshot(WebDriver driver){
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            String out=screenshot.getScreenshotAs(OutputType.BASE64);
            System.out.println(out.length());
            driver.quit();
        }
        /// not inbuilt selenium
        static void take_screenshot_robot_class(WebDriver driver){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Robot robot = null;
            try {
                robot = new Robot();
                robot.keyPress(KeyEvent.VK_WINDOWS);
                robot.keyPress(KeyEvent.VK_PRINTSCREEN);
                robot.keyRelease(KeyEvent.VK_WINDOWS);
                robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }
        static void time_stamp_screenshot(WebDriver driver){

            TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
            File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String destPath = System.getProperty("user.dir")+"\\screenshots\\fullpage_" + timestamp + ".png";
            try {
                FileUtils.copyFile(srcFile, new File(destPath));
                /// this is from a new dependency
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Screenshot saved: " + destPath);
                /**
                 <dependencies>
                 <!-- Selenium Java -->
                 <dependency>
                 <groupId>org.seleniumhq.selenium</groupId>
                 <artifactId>selenium-java</artifactId>
                 <version>4.14.1</version> <!-- Use latest version -->
                 </dependency>
                 <!-- Apache Commons IO (for file operations) -->
                 <dependency>
                 <groupId>commons-io</groupId>
                 <artifactId>commons-io</artifactId>
                 <version>2.11.0</version>
                 </dependency>
                 </dependencies>
                 * */
        }
        static void full_page_scrolling_screenshot(WebDriver driver){


            try {
            Screenshot fullPageScreenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000)) // Scroll delay
                    .takeScreenshot(driver);

            ImageIO.write(fullPageScreenshot.getImage(), "PNG",
                        new File(System.getProperty("user.dir")+"\\screenshots\\full_scroll.png"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static class BrowserOptions{
        /**
         * Specific Options class for each browser is available
         * ChromeOptions, EdgeOptions, FireFoxOptions, etc.
         * For different browsers different classes are there but the methods are same
         * We can control settings of a browser using these classes : browser specific settings
         * Specific use cases of such settings
            * 1) Headless testing : our code runs in background, but we do not see ui and get final output.
                        * Ad :
                            * 1) We can do multiple task (since execution happened background), normally our other tasks disturb normal execution of tests
                            * 2) Faster execution
                        * Dis :
                            * 1) User cannot see any actions, other people cannot understand what is happening
                        * When :
                            * 1) Do not use during initial phase of tests writing
                            * 2) When need to run same thing multiple times or in future when everything is set up, we know it works.
            * 2) SSL handling : secure socket layer handling
                        * every website need such certificate to run and access data
                        * when error occurs you connection is not secure/private
                        * we can select to proceed saying we accept its SSL
                        * it is a security kind of thing

            * 3) Remove chrome controlled by automated software :
                         * whenever we launch browser using selenium we see a message : "Chrome is being controlled by automated test software."
                         * what if we don't want to see this message on screen
            * 4) Run test in incognito mode : run tests privately
            * 5) Enable extensions : usually the extensions do not appear in automation browser window, but can enable it
         */

        /**
         Common Customizations:

         Functionality  |    Code Example	|   Purpose
         Headless Mode	|   options.addArguments("--headless=new");	Run browser without GUI (for server/CI).
         Maximize Window    |   options.addArguments("start-maximized");	Open browser in maximized view.
         Incognito Mode |   options.addArguments("--incognito");	Launch in private browsing.
         Disable Infobars   |   options.addArguments("disable-infobars");	Hide "Chrome is controlled..." notification.
         Disable Notifications  | options.addArguments("--disable-notifications");	Block browser notifications.
         Set Window Size    |   options.addArguments("window-size=1280,800");	Define explicit window dimensions.
         Disable Extensions |   options.addArguments("--disable-extensions");	Turn off all extensions.
         Disable GPU    |   options.addArguments("--disable-gpu");	Bypass GPU requirements (for headless).
         Set Language   |   options.addArguments("--lang=en-US");	Force browser language (e.g., English-US).
         Disable Popup Blocking |   options.addArguments("--disable-popup-blocking");	Allow all popups.
         * */
        /**
         Browser Behavior Control: You can run Chrome in headless mode, set window sizes, disable security features for testing, and control basic browser functionality.

         Performance Optimization: Disable images, JavaScript, plugins, and extensions to speed up page loading and reduce resource consumption. This is particularly useful for web scraping or automated testing where you don't need full rendering.

         Download Management: Configure where files download, disable download prompts, and automatically handle PDF files. This is essential for testing file download functionality.

         Security and Privacy: Disable password managers, autofill, location services, camera, and microphone access. These settings help create consistent test environments.

         Mobile Testing: Emulate different mobile devices with specific screen resolutions, pixel ratios, and user agents. Chrome can simulate various smartphones and tablets.

         Proxy and Network: Configure proxy servers, handle SSL certificates, and manage network-related settings for testing in different network environments.

         Extension Management: Add Chrome extensions programmatically or disable all extensions for clean testing environments.

         Common Use Cases
         CI/CD Environments: In continuous integration, you typically use headless mode with sandbox disabled and optimized performance settings to run tests faster in containerized environments.

         Web Scraping: Disable images, CSS, and JavaScript when you only need to extract text content, significantly improving scraping speed.

         Cross-browser Testing: Use different user agents and device emulation to test how your application behaves across various browsers and devices.

         Download Testing: Configure specific download directories and disable prompts to test file download functionality automatically.

         Security Testing: Disable various security features temporarily to test specific scenarios, although this should only be done in controlled test environments.
         * */
        public static void main(String[] args) {
            WebDriver driver;
//            driver= new ChromeDriver();
//            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            /// 1) headless testing
//            head_less_testing();

            /// 2) handling ssl
//            handling_SSL();

            /// 3) removing automated message
//            remove_automated_message();

            /// 4) work in incognito mode
//            work_in_incognito_mode();

            /// 5) enable extensions
            enable_extensions();
        }
        static void add_arguments_demo(){
            ChromeOptions options= new ChromeOptions();

            // 1. BASIC BROWSER BEHAVIOR

            // Run in headless mode (no GUI)
            options.addArguments("--headless");

            // Disable web security (for testing purposes only)
            options.addArguments("--disable-web-security");

            // Disable CORS policy
            options.addArguments("--disable-features=VidyoDisplayCapture");

            // Start maximized
            options.addArguments("--start-maximized");

            // Set window size
            options.addArguments("--window-size=1920,1080");

            // Disable extensions
            options.addArguments("--disable-extensions");

            // Disable plugins
            options.addArguments("--disable-plugins");

            // Disable images loading (faster page load)
            options.addArguments("--disable-images");

            // Disable JavaScript
            options.addArguments("--disable-javascript");

            // Disable GPU hardware acceleration
            options.addArguments("--disable-gpu");

            // Disable sandbox (useful for Docker/CI environments)
            options.addArguments("--no-sandbox");

            // Disable /dev/shm usage (useful for Docker)
            options.addArguments("--disable-dev-shm-usage");


            // 2. NOTIFICATION AND POPUP HANDLING

            // Disable notifications
            options.addArguments("--disable-notifications");

            // Disable popup blocking
            options.addArguments("--disable-popup-blocking");

            // Disable infobars
            options.addArguments("--disable-infobars");

            // 3. DOWNLOAD PREFERENCES

            Map<String, Object> prefs = new HashMap<>();

            // Set download directory
            prefs.put("download.default_directory", "/path/to/download/directory");

            // Disable download prompt
            prefs.put("download.prompt_for_download", false);

            // Disable PDF viewer (auto-download PDFs)
            prefs.put("plugins.always_open_pdf_externally", true);

            // Set download behavior
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("profile.default_content_setting_values.automatic_downloads", 1);

            // 4. SECURITY AND PRIVACY SETTINGS

            // Disable password save prompt
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            // Disable autofill
            prefs.put("profile.default_content_setting_values.autofill", 2);

            // Disable location sharing
            prefs.put("profile.default_content_setting_values.geolocation", 2);

            // Disable camera
            prefs.put("profile.default_content_setting_values.media_stream_camera", 2);

            // Disable microphone
            prefs.put("profile.default_content_setting_values.media_stream_mic", 2);

            // 5. PERFORMANCE OPTIMIZATIONS

            // Disable logging
            options.addArguments("--disable-logging");
            options.addArguments("--log-level=3");

            // Disable default apps
            options.addArguments("--disable-default-apps");

            // Memory optimization
            options.addArguments("--memory-pressure-off");
            options.addArguments("--max_old_space_size=4096");

            // Apply preferences
            options.setExperimentalOption("prefs", prefs);

            // 6. USER AGENT CUSTOMIZATION

            // Set custom user agent
            options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

            // 7. PROXY SETTINGS

            // Set proxy server
             options.addArguments("--proxy-server=http://proxy-server:port");

            // 8. DEBUGGING AND DEVELOPMENT OPTIONS

            // Enable verbose logging
             options.addArguments("--enable-logging");
             options.addArguments("--v=1");

            // Remote debugging port
            options.addArguments("--remote-debugging-port=9222");

            // 9. MOBILE EMULATION

            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "iPhone X");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);

            // Or custom mobile settings
            Map<String, Object> customMobile = new HashMap<>();
            customMobile.put("width", 375);
            customMobile.put("height", 812);
            customMobile.put("pixelRatio", 3.0);

            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", 375);
            deviceMetrics.put("height", 812);
            deviceMetrics.put("pixelRatio", 3.0);

            // mobileEmulation.put("deviceMetrics", deviceMetrics);
             mobileEmulation.put("userAgent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X)");
             options.setExperimentalOption("mobileEmulation", mobileEmulation);

            // 10. EXTENSION MANAGEMENT

            // Add extension (provide path to .crx file)
             options.addExtensions(new File("/path/to/extension.crx"));

            // Load unpacked extension
             options.addArguments("--load-extension=/path/to/unpacked/extension");

            // 11. CERTIFICATE AND SSL HANDLING

            // Ignore certificate errors
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--ignore-ssl-errors");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--ignore-certificate-errors-spki-list");

            // 12. ADVANCED EXPERIMENTAL OPTIONS

            // Disable Chrome's crash reporter
            options.addArguments("--disable-crash-reporter");

            // Disable background timer throttling
            options.addArguments("--disable-background-timer-throttling");

            // Disable renderer backgrounding
            options.addArguments("--disable-renderer-backgrounding");

            // Disable backgrounding of occluded windows
            options.addArguments("--disable-backgrounding-occluded-windows");

            // Create WebDriver instance
            WebDriver driver = new ChromeDriver(options);

            try {
                // Navigate to a website
                driver.get("https://www.example.com");

                // Get page title
                System.out.println("Page title: " + driver.getTitle());

                // Additional operations can be performed here

            } finally {
                // Close the browser
                driver.quit();
            }
        }
        static void head_less_testing(){

            ChromeOptions c_options=new ChromeOptions();
            c_options.addArguments("--headless=new");
            /// this statement is for headless mode execution

            WebDriver driver=new ChromeDriver(c_options);

            /// taking a simple title confirmation test
            driver.get("https://www.youtube.com");
            if (driver.getTitle().equals("YouTube")){
                System.out.println("test passed");
            }
            else {
                System.out.println("test failed");
            }
            driver.quit();

        }
        static void handling_SSL(){
            ChromeOptions c_options=new ChromeOptions();
            c_options.setAcceptInsecureCerts(true);
            /// accept insecure certificates : by default it is false we make it true

            WebDriver driver=new ChromeDriver(c_options);
            driver.get("https://expired.badssl.com/");
            System.out.println("Page title : "+driver.getTitle());
            /// privacy error without chrome options
        }
        static void remove_automated_message(){
            ChromeOptions c_options=new ChromeOptions();
            c_options.setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});
            /// this statement will remove automated message

            WebDriver driver=new ChromeDriver(c_options);

            /// taking a simple title confirmation test
            driver.get("https://www.youtube.com");
            if (driver.getTitle().equals("YouTube")){
                System.out.println("test passed");
            }
            else {
                System.out.println("test failed");
            }
            driver.quit();
        }
        static void work_in_incognito_mode(){
            ChromeOptions chromeOptions= new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            WebDriver driver= new ChromeDriver(chromeOptions);
            driver.get("https://www.youtube.com/");
            /// taking a simple title confirmation test
            driver.get("https://www.youtube.com");
            if (driver.getTitle().equals("YouTube")){
                System.out.println("test passed");
            }
            else {
                System.out.println("test failed");
            }
//            driver.quit();

        }
        static void enable_extensions(){
            /// normally we don't see our extensions and see a number of adds
            /// we cannot handle adds using automation but if we enable our ad blocker extension enable it can handle them
            /// similarly we can enable other extensions
            /// 4 steps are there
            /// 1) add CRX Extractor/Downloader to Chrome Browser (manually)
            /// 2) add extensions plugin to chrome bowser (manually)
            /// 3) capture crx file for particular extension
            /// 4) pass crx file path in automation script in chrome options

            ChromeOptions chromeOptions= new ChromeOptions();
            chromeOptions.addExtensions(new File("path_to_crx_file.crx"));
            /// crx : Chromium browser extensions file

            WebDriver driver= new ChromeDriver(chromeOptions);
            driver.get("https://text-compare.com/");


        }
        /**
         cannot add Chrome options to a WebDriver instance after it has been created.
         Chrome options must be set before creating the WebDriver object because they are used
         during the browser initialization process.

         Chrome options are initialization parameters that are passed to the Chrome browser process when
         it starts. Once the browser process is running, these fundamental settings cannot be changed
         because they control:
             Process-level configurations (sandbox, GPU acceleration)
             Browser startup behavior (headless mode, window size)
             Security policies (CORS, web security)
             Network settings (proxy configuration)

         Recreate the Driver: The most straightforward approach is to quit the existing driver
         and create a new one with the desired options. This is the
         standard practice when you need different configurations.

         Runtime Modifications (Limited): Some browser behaviors can be modified after creation
         using JavaScript execution or WebDriver commands, but
         these have significant limitations and don't provide
         the same level of control as Chrome options.

         Factory Pattern: Use a factory pattern to create different types of drivers with
         predefined configurations. This makes it easy to switch between different setups.

         Driver Manager: Implement a driver manager class that handles the lifecycle of recreating
         drivers when new options are needed.
         * */

    }

}
