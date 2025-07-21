package org.example._11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileWriter;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;



public class DropDownTypes {
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
//        select_dropdown(driver);
//        bootstrap_dropdown(driver);
        hidden_dropdown(driver);
        Scanner s= new Scanner(System.in);
        String a="";
        while (!a.equals("y")&&!a.equals("n")) {
            System.out.println("\nclose driver ? : y/n ");
            a=s.nextLine();
        }
        if (a.equals("y")){
            driver.quit();
        }
    }

    static void select_dropdown(WebDriver driver){
        driver.get("https://testautomationpractice.blogspot.com/");

        WebElement element=driver.findElement(By.xpath("//select[@id='country']"));
        Select select= new Select(element);
//        select.selectByVisibleText("Germany");
//        select.selectByValue("uk");
        select.selectByIndex(9);

        List<WebElement> list=select.getOptions();
        System.out.println("all the "+list.size()+" options in the dropdown are : ");
        int i=1;
        for (WebElement e:list) {
            System.out.println("  ("+i+")  " + e.getText());
            i++;
        }
//        page_source_save_to_file(driver,"dropdown");
    }
    static void page_source_save_to_file(WebDriver driver,String filename){
        String page_source=driver.getPageSource();
        filename=filename+".html";
        try (FileWriter writer = new FileWriter(filename)){
            writer.write(page_source);
            System.out.println("Source code written to file : "+filename+" successfully");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("page source writing to file failed");
        }
    }

    static void bootstrap_dropdown(WebDriver driver){
        driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");
        WebElement dropdown_button= driver.findElement(By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']"));
        System.out.println(dropdown_button.getTagName());
        dropdown_button.click();//opens dropdown

        /// select an option
//        driver.findElement(By.xpath("//input[@value='Java']")).click();

        ///select all elements
//        List<WebElement> checkboxes=driver.findElements(By.xpath("//ul[contains(@class,'multiselect-container')]//label"));
        /// using label also gave text without checkbox, also we cannot check if the checkbox is already selected using label, need to use input tag with value attribute
        List<WebElement> checkboxes=driver.findElements(By.xpath("//ul[contains(@class,'multiselect-container')]//input"));
        System.out.println(checkboxes.size());

        /// printing all labels
        for (WebElement ele:checkboxes){
            System.out.println(ele.getAttribute("value"));
        }

        /// selecting multiple checkboxes
        for (WebElement ele:checkboxes){
            String s=ele.getAttribute("value");
            if (ele.isSelected()){
                System.out.println(s);
                ele.click();
            }
            if (s.equals("Java")||s.equals("csharp")||s.equals("MySQL")){
                ele.click();
            }
        }
    }
    static void hidden_dropdown(WebDriver driver){
        /// options are not visible on html, loaded on runtime
        /// can use selector hub debugger to freeze screen and get all the options visible on html to get the xpath


        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        /// first login
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Admin");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();

        /// click on PIM
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span")).click();

        /// open dropdown
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i")).click();

        /// select option
//        driver.findElement(By.xpath("")).click();

        /// count number of options - write common x path for all options
//        List<WebElement> options=driver.findElements(By.xpath(""));
        /// put thread sleep to wait for elements to load
//        System.out.println("no of options : "+options.size());

        /// print options using getText()



        /// autosuggest box : like google





    }
}
