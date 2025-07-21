package org.example._13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class DynamicPagination {
    public static void main(String[] args) {

        WebDriver driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        /// dynamic pagination table :
//        show_case(driver);
//data_tables(driver);
//pagination_assignment(driver);
bootstrap_table(driver);
    }
    static void show_case(WebDriver driver){
        driver.get("https://showcase.primefaces.org/ui/data/datatable/paginator.xhtml?jfwid=e1dc3");
        List<WebElement> pages=driver.findElements(By.xpath("//span[@class='ui-paginator-pages']/button"));
        System.out.println("total number of pages"+pages.size());
    }
    static void data_tables(WebDriver driver){
        driver.get("https://datatables.net/examples/data_sources/server_side.html");

        int entires_per_page=10;  /// 10,25,50,100
        /// change number of entries per page using select
        new Select(driver.findElement(By.xpath("//select[@id=\"dt-length-0\"]"))).selectByVisibleText(entires_per_page+"");

        /// 1) total number pages

        ///by button count

        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        String x_path="//*[@id=\"example_wrapper\"]/div[3]/div[2]/div/nav/button";
//        String x_path="//button[@class='dt-paging-button']";
        List<WebElement> pages=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(x_path)));
        System.out.println("total number of pages "+(pages.size()-4));


        /// getting buttons might work but is unreliable in long run
        /// for such task we will get the string showing the total number of items and perform pattern matching

        String num_pages=driver.findElement(By.xpath("//div[@id='example_info']")).getText();

        /// number of pages here by default is 10
        /// using regular expression

        Pattern pattern = Pattern.compile("of (\\d+) entries");
        Matcher matcher = pattern.matcher(num_pages);
        if (matcher.find()) {
            int totalEntries = Integer.parseInt(matcher.group(1));
            int pageCount = (int) Math.ceil((double) totalEntries / entires_per_page);
            System.out.println("Total pages: " + pageCount);
        }

        /// using substring
        int number_of_pages=(int)Math.ceil(Double.parseDouble(pages_string(num_pages))/entires_per_page);
        System.out.println("Total pages substring: " + number_of_pages);


        /// 2) click on different pages

        System.out.println("navigating to different pages : ");
        /// using common x path
        WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));
        for(int p=1;p<=number_of_pages;p++){
            if(p>1) {
                WebElement curr_page = mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='dt-paging-button' and text()='" + p + "']")));
//                System.out.println("page number " + curr_page.getText());
                curr_page.click();
                // Wait for the table to be refreshed after navigation
                mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='example']/tbody")));

                // Additional wait to ensure DOM is stable
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }


            /// 3) Reading table data
        /// find number of rows using tbody/tr
        /// using for loop
            System.out.println("displaying all page : "+p);
            // Wait for table to be present and stable before getting row/column counts
            mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='example']")));


            int num_of_rows=mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='example']/tbody/tr"))).size();
        int num_of_cols=mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='example']/thead/tr/th"))).size();

            System.out.print("|\t");
        for(int i=1;i<=num_of_cols;i++){
            WebElement colname=mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='example']/thead/tr/th["+i+"]/div/span")));
            System.out.print(colname.getText()+"\t|");
        }
            System.out.println();

        for(int i=1;i<=num_of_rows;i++){
            System.out.print("|\t");
            for(int j=1;j<=num_of_cols;j++){
                WebElement cell_data=mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='example']/tbody/tr["+i+"]/td["+j+"]")));
                System.out.print(cell_data.getText()+"\t|");

            }
            System.out.println();
        }
                        wait_time(2000);
        }

        /// using list
//        pages=driver.findElements(By.xpath("//div[@class='dt-paging']/*/*"));
//        System.out.println(pages);
//        System.out.println(pages.size());
//        for(int i=0;i<pages.size();i++){
//            pages.get(i).click();
//            System.out.println("list page number "+pages.get(i).getText());
//            wait_time(3000); /// StaleElementReferenceException
//        }

        /// the issue occurs because when you click a pagination button, the DOM gets refreshed, making all previously located elements stale. Here's how to fix it:
        /// When you do driver.findElements() and store the elements in a list (pages), these are references to elements at that moment. After clicking any pagination button:
        /// The page content refreshes
        /// The old elements become detached from the DOM
        /// Your stored references becomes stale.
        /// always relocate element at each step to avoid staleness


        driver.quit();
    }
    static String pages_string(String s){
        int i;
        for(i=0;i<s.length();i++)
            if(s.charAt(i)=='f')
                break;
        int j;
        for(j=++i;j<s.length();j++)
            if (s.charAt(j)=='e')
                break;
        return s.substring(i,j).trim();

    }
static void wait_time(int n){
    try {
        Thread.sleep(n);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
static void pagination_assignment(WebDriver driver){
        /// navigate to all pages, read all data and click on each checkbox

    driver.get("https://testautomationpractice.blogspot.com/");

    /// get the number of pages
    int pages=driver.findElements(By.xpath("//ul[@id='pagination']/li")).size();
    System.out.println("The number of pages are : "+pages);

    /// navigating to different pages
    WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
    for(int j=1;j<=pages;j++){
        WebElement curr_page=mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='pagination']/li["+j+"]")));
        curr_page.click();
        System.out.println("current page number : "+curr_page.getText());
//        wait_time(2000);
    }

    /// printing all data and clicking on checkboxes
    System.out.println("\nPrinting all table data");
    int cols=mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='productTable']/thead/tr/th"))).size();
    System.out.println(cols);
    for(int i=1;i<cols;i++){
        String col_name=mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='productTable']/thead//th["+i+"]"))).getText();
        System.out.print("\t"+col_name+"\t|");
    }
    System.out.println("table name complete");
    System.out.println();
    for(int j=1;j<pages+1;j++){
        WebElement curr_page=mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='pagination']/li["+j+"]")));
        curr_page.click();
        int rows=mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='productTable']/tbody/tr"))).size();
        for(int k=1;k<=rows;k++){
            for(int i=1;i<cols;i++){
                String cell_data=mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='productTable']/tbody/tr["+k+"]/td["+i+"]"))).getText();
                System.out.print("\t"+cell_data+"\t|");
            }
            mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='productTable']/tbody/tr["+k+"]/td[4]/input"))).click();
            System.out.println();
        }
//        wait_time(1000);
    }

//    wait_time(3000);
    driver.quit();

}
static void bootstrap_table(WebDriver driver){
    /// Bootstrap table : table where table tag is not used instead div is used for table head and body, as well as each row and column
    /// modern and advanced version of tables

    /// OrangeHRM -> admin

    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
    /// log in
    driver.findElement(By.name("username")).sendKeys("Admin");
    driver.findElement(By.name("password")).sendKeys("admin123");
    mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\"]"))).click();

    /// goto admin
    driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span")).click();

    /// goto nationalities
    driver.findElement(By.xpath("//a[text()='Nationalities']")).click();



    /// total number of records
    String records= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[2]/div/span")).getText();
    int total_records=string_to_records(records);
    System.out.println("total records are : "+total_records);

    /// checkboxes
//    System.out.println("writing page source to file : "+pagesourcefile(driver,"orange_nationality_table"));
    List<WebElement> checkboxes=driver.findElements(By.xpath("//div[@class='oxd-checkbox-wrapper']"));
    for(int i=1;i<checkboxes.size();i+=2){
        checkboxes.get(i).click();
    }
    /// checkboxes were hidden in inspect for the web page, but could be fetched from the page source

    /// total pages
    int pages=driver.findElements(By.xpath("//ul[@class='oxd-pagination__ul']/li")).size();
    System.out.println("total number of pages are : "+(pages-1));

    /// navigating to each page
    System.out.println("\nprinting all data");
    String heading=mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Nationality']"))).getText();
    System.out.println(heading);
    for(int i=1;i<=pages;i++){
        WebElement curr_page=driver.findElement(By.xpath("//ul[@class='oxd-pagination__ul']/li["+i+"]"));
        curr_page.click();
        System.out.println("current page number : "+curr_page.getText());
        if(i==2)
            i++;

        /// printing all data
         List<WebElement> cells = driver.findElements(By.xpath("//div[@data-v-6c07a142]"));

         int m=1;
        for(WebElement ele:cells){
            System.out.println("\t ("+m+")"+ele.getText());
            m++;
        }

//        wait_time(1000);
    }

driver.quit();
    }
static boolean pagesourcefile(WebDriver driver,String filename){
    String page_source=driver.getPageSource();
    String file_name=filename+".html";
    try (FileWriter writer = new FileWriter(file_name)){
        writer.write(page_source);
        out.println("Source code written to file : "+file_name+" successfully");
        return true;
    }
    catch (Exception e){
        out.println(e);
        out.println("page source writing to file failed");
        return false;
    }
}
static int string_to_records(String records){


        int i;
        for(i=0;i<records.length();i++) {
            if(records.charAt(i)=='('){
                break;
            }
        }
        int j;
        for (j=++i;j<records.length();j++){
            if(records.charAt(j)==')')
                break;
        }

        return Integer.parseInt(records.substring(i,j).trim());
}
static boolean is_number(String s){
        try{
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
}
}

/**
 Great question! The choice between presenceOfElementLocated and visibilityOfElementLocated is crucial for handling dynamic content and avoiding stale element exceptions. Let me explain the key differences:
 presenceOfElementLocated vs visibilityOfElementLocated
 presenceOfElementLocated

 What it checks: Element exists in the DOM (Document Object Model)
 Visibility requirement: NO - element can be hidden, have display: none, visibility: hidden, etc.
 Performance: Faster - only checks DOM presence
 Use case: When you just need to confirm element exists and will interact with it programmatically

 visibilityOfElementLocated

 What it checks: Element exists in DOM AND is visible to user
 Visibility requirement: YES - element must have height > 0, width > 0, not hidden by CSS
 Performance: Slower - checks DOM presence + visibility calculations
 Use case: When user needs to see/interact with element visually

 // PROBLEM SCENARIO: Table pagination with dynamic loading

 // ❌ PROBLEMATIC - Your Original Approach
 WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));

 // After clicking pagination button...
 curr_page.click();

 // This can fail because:
 // 1. New page elements might not be fully "visible" yet (still loading/rendering)
 // 2. Elements might be temporarily hidden during DOM update
 // 3. Visibility check is more complex and takes longer
 int num_of_rows = mywait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
 By.xpath("//table[@id='example']/tbody/tr"))).size();

 // ✅ BETTER APPROACH - Using presenceOfElementLocated
 // Wait for DOM to be updated (faster, more reliable)
 mywait.until(ExpectedConditions.presenceOfElementLocated(
 By.xpath("//table[@id='example']/tbody")));

 // Then get fresh elements
 List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
 int num_of_rows = rows.size();

 // ✅ DETAILED EXAMPLE - When to use each condition

 public class WaitConditionsExample {

 // Use presenceOfElementLocated when:
 public void waitForDOMUpdate() {
 // 1. Waiting for AJAX content to load
 mywait.until(ExpectedConditions.presenceOfElementLocated(By.id("ajax-content")));

 Asynchronous JavaScript and XML (Ajax, or AJAX) is a web development technique in which a web app fetches content from the server by making asynchronous HTTP requests, and uses the new content to update the relevant parts of the page without requiring a full page load.

 // 2. Waiting for dynamic table rows after pagination
 mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[1]")));

 // 3. Waiting for elements that might be hidden but need to exist
 mywait.until(ExpectedConditions.presenceOfElementLocated(By.className("hidden-data")));
 }

 // Use visibilityOfElementLocated when:
 public void waitForUserInteraction() {
 // 1. Waiting for modal dialog to appear
 WebElement modal = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal")));

 // 2. Waiting for button user needs to click
 WebElement button = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit-btn")));
 button.click();

 // 3. Waiting for error message to be shown to user
 WebElement error = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-msg")));
 System.out.println("Error: " + error.getText());
 }

 // COMBINED APPROACH - Most robust for dynamic content
 public void robustTableHandling() {
 // Step 1: Wait for DOM structure to exist
 mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='example']/tbody")));

 // Step 2: Wait for first row to be visible (content loaded)
 mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']/tbody/tr[1]")));

 // Step 3: Now safely get all elements
 List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
 }
 }


 * */