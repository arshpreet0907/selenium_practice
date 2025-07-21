package org.example._12;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AutoSuggestDropdown_StaticWebTables {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        /// auto suggest dropdown
//        auto_suggest_dropdown(driver);
//        search_in_url_area(driver);

        /// web tables
        /// types :
        /// static web table
        /// dynamic web table
        /// pagination web table

        /// 1) static web page
//        static_web_tables(driver);
//        driver.quit();

        /// assignment
        assignment(driver);
    }
    static void auto_suggest_dropdown(WebDriver driver)  {
        /// auto suggest dropdown is a dynamic dropdown where the options depend on the keys dent
        /// example google search
//        driver.get("https://www.google.com/");//causes human verify checks
        driver.get("https://www.bing.com/");

        /// clicking on keep signed out
//        driver.findElement(By.xpath("//*[@id=\"stUuGf\"]/div/div[2]/div/div/div/div[2]/div/promo-button-text[1]/div/div")).click();

        /// sending keys to search box
//        driver.findElement(By.xpath("//textarea[@aria-label=\"Search\"]")).sendKeys("selenium"); //google
        driver.findElement(By.xpath("//textarea[@name=\"q\"]")).sendKeys("selenium"); //bing


        /// capturing all the suggestions in options
//        List<WebElement>options=driver.findElements((By.xpath("//ul[@role=\"listbox\"]//li//div[@role=\"option\"]"))); //google
        List<WebElement>options=driver.findElements((By.xpath("//ul[@role=\"listbox\"]//li//div[@class='sa_tm']"))); //bing
//
        System.out.println("all the options are : "+options.size());
        System.out.println("all the options are : ");
        for (WebElement ele:options){
            System.out.println("     "+ele.getText());
        }
        Scanner s= new Scanner(System.in);
        System.out.println("enter what to search");
        String choice=s.nextLine();
        for (WebElement ele:options){
            String eletxt=ele.getText();
            if (eletxt.equals(choice)){
                ele.click();
            }
        }
    }
    static void search_in_url_area(WebDriver driver){
//        driver.get("https://www.google.com/");
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .sendKeys("L")
                .keyUp(Keys.CONTROL)
                .perform();
    }
    static void amazon_search(WebDriver driver){}

    static void static_web_tables(WebDriver driver){

        /// using table, tbody, tr,th,td tags of html

        driver.get("https://testautomationpractice.blogspot.com/");

        /// get all the rows
        List<WebElement> table_rows=driver.findElements(By.xpath("//table[@name=\"BookTable\"]//tr"));

        /// print all the rows
        int rows=table_rows.size();
        System.out.println("total number of rows : "+rows);
        for (WebElement ele:table_rows){
//            System.out.println(ele.getText());
        }

        /// get all the cols
        List<WebElement> table_cols=driver.findElements(By.xpath("//table[@name=\"BookTable\"]//th"));
        int cols=table_cols.size();
        System.out.println("no of cols : "+cols);
        /// get particular cell data e.g. : [5,1]
        System.out.println("value at 5th row 1st column : "+driver.findElement(By.xpath("//table[@name=\"BookTable\"]//tr[5]//td[1]")).getText());


        /// print all the data of table :

        /// use th for cols and td for data and print.
        /// print table col names :
/*
        System.out.println("table data : ");
        for (int i=0;i<table_cols.size();i++){
            System.out.print(table_cols.get(i).getText()+"   ");
        }
        System.out.println();
        /// print data for each col in each row

        List<WebElement> rows_data=driver.findElements(By.xpath("//table[@name=\"BookTable\"]//td"));
        for(int i=1;i<=rows_data.size();i++){
            System.out.print(rows_data.get(i-1).getText()+"   ");
            if(i%cols==0){
                System.out.println();
            }
        }
*/
        /// use parameterised x paths
        /// first know the number of rows and cols
        /// here we have 7 rows and 4 cols

        /*
        System.out.println("table data : ");
        /// cols
        for (int i=0;i<table_cols.size();i++){
            System.out.print(table_cols.get(i).getText()+"   ");
        }
        System.out.println();
        /// data

        for(int r=2;r<=rows;r++){
            for(int c=1;c<=cols;c++){
                System.out.print(driver.findElement(By.xpath("//table[@name=\"BookTable\"]//tr["+r+"]//td["+c+"]")).getText()+"\t");//produces a tab space
            }
            System.out.println();
            /// to put parameterised x path syntax is "+r+", here r is variable

        }
        */


        /// conditional retrieval of data
        /// books whose author is mukesh
        /// traverse authors, if match get the book
        /// print only author

        /*
        System.out.println("all books with author mukesh : ");
        for(int i=2;i<=rows;i++){
            String author =driver.findElement(By.xpath("//table[@name=\"BookTable\"]//tr["+i+"]//td[2]")).getText();
//            System.out.println(author);
       if(author.equals("Mukesh")){
           String book=driver.findElement(By.xpath("//table[@name=\"BookTable\"]//tr["+i+"]//td[1]")).getText();
           System.out.println(book);
       }
        }

         */
        /// total prices of all books
        int price=0;
        for(int i=2;i<=rows;i++){
            price+=Integer.parseInt(driver.findElement(By.xpath("//table[@name=\"BookTable\"]//tr["+i+"]//td[4]")).getText());
        }
        System.out.println("total price : "+price);
    }
    static void assignment(WebDriver driver){
        driver.get("https://blazedemo.com");
        if(validation(driver))
            System.out.println("validation successful");

        else
            System.out.println("validation failed");
        driver.quit();

    }
    static boolean validation(WebDriver driver){

        Map<String,String> data_map=data_to_map();
        /// 1) select city one
        WebElement cityone=driver.findElement(By.xpath("//select[@name=\"fromPort\"]"));
        Select cityone_select=new Select(cityone);
        cityone_select.selectByVisibleText(data_map.get(city_1));

        /// 2) select city two
        WebElement citytwo=driver.findElement(By.xpath("//select[@name=\"toPort\"]"));
        Select citytwo_select=new Select(citytwo);
        citytwo_select.selectByVisibleText(data_map.get(city_2));

        /// 3) click find flights
        wait_time(0);
        driver.findElement(By.xpath("//input[@value=\"Find Flights\"]")).click();

        /// 4) chose the cheapest one

        float cheapest_price=Integer.MAX_VALUE;
        int cheapest_price_index=0;
        int rows=driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();

        for(int r=1;r<=rows;r++){
            float price=Float.parseFloat(driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+r+"]/td[6]")).getText().substring(1));
            if (price<cheapest_price){
                cheapest_price=price;
                cheapest_price_index=r;
            }
        }
        wait_time(0);
        driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+cheapest_price_index+"]/td[1]/input")).click();

        /// 5) fill the personal details

        driver.findElement(By.xpath("//input[@id=\"inputName\"]")).sendKeys(data_map.get(name));

        driver.findElement(By.xpath("//input[@id=\"address\"]")).sendKeys(data_map.get(address));

        driver.findElement(By.xpath("//input[@id=\"city\"]")).sendKeys(data_map.get(city));

        driver.findElement(By.xpath("//input[@id=\"state\"]")).sendKeys(data_map.get(state));

        driver.findElement(By.xpath("//input[@id=\"zipCode\"]")).sendKeys(data_map.get(zipcode));

        Select card_type_select=new Select(driver.findElement(By.xpath("//select[@id=\"cardType\"]")));
        card_type_select.selectByVisibleText(data_map.get(card_type));

        driver.findElement(By.xpath("//input[@id=\"creditCardNumber\"]")).sendKeys(data_map.get(card_number));

        WebElement card_month=driver.findElement(By.xpath("//input[@id=\"creditCardMonth\"]"));
        card_month.clear();
        card_month.sendKeys(data_map.get(month));

        WebElement card_year=driver.findElement(By.xpath("//input[@id=\"creditCardYear\"]"));
        card_year.clear();
        card_year.sendKeys(data_map.get(year));

        driver.findElement(By.xpath("//input[@id=\"nameOnCard\"]")).sendKeys(data_map.get(name_on_card));

        driver.findElement(By.xpath("//input[@id=\"rememberMe\"]")).click();

        /// 6) click on purchase flight
        wait_time(0);
        driver.findElement(By.xpath("//input[@value=\"Purchase Flight\"]")).click();

        /// 7) check if the validation is complete

        String validity_string=driver.findElement(By.xpath("//div[@class=\"container hero-unit\"]/h1")).getText();
        boolean is_valid=validity_string.toLowerCase().contains("thank");

        /// 8) print all the table data
        if(is_valid) {
            int thank_rows = driver.findElements(By.xpath("//table[@class=\"table\"]/tbody/tr")).size();
            int thank_cols = driver.findElements(By.xpath("//table[@class=\"table\"]/tbody/tr[1]/td")).size();

            System.out.println("Details of flight");
            for (int r = 1; r <= thank_rows; r++) {
                for (int c = 1; c <= thank_cols; c++) {
                    String data = driver.findElement(By.xpath("/html/body/div[2]/div/table/tbody/tr[" + r + "]/td[" + c + "]")).getText();
                    /// /html/body/div[2]/div/table/tbody/tr[1]/td[1]
                    System.out.print("\t" + data + "\t");
                }
                System.out.println();
            }
            System.out.println(driver.findElement(By.xpath("/html/body/div[2]/div/pre")).getText());
        }
        return is_valid;
    }
static String city_1="city1";
static String city_2="city2";
static String name="name";
static String address="address";
static String city="city";
static String state="state";
static String zipcode="zipcode";
static String card_type="card_type";
static String card_number="card_number";
static String month="month";
static String year="year";
static String name_on_card="name_on_card";
static String remember_me="remember_me";
static String yes="yes";
static String no="no";

    static Map<String,String> data_to_map(){
        Map<String,String> data_map=new HashMap<>();

        /// cities
        data_map.put(city_1,"Mexico City"); /// Paris, Philadelphia, Boston, Portland, San Diego, Mexico City, São Paolo
        data_map.put(city_2,"Rome"); /// Buenos Aires, Rome, London, Berlin, New York, Dublin, Cairo

        /// passenger details
        data_map.put(name,"Arsh");
        data_map.put(address,"Super Corridor Indore");
        data_map.put(city,"Indore");
        data_map.put(state,"Madhya Pradesh");
        data_map.put(zipcode,"401001");
        data_map.put(card_type,"Diner's Club"); /// Visa, American Express, Diner's Club
        data_map.put(card_number,"12345678910");
        data_map.put(month,"6");
        data_map.put(year,"2030");
        data_map.put(name_on_card,"Arsh");
        data_map.put(remember_me,yes);
        return data_map;

    }
    static void wait_time(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
