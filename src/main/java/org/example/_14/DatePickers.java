package org.example._14;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

/**
 * Design for date pickers varies
 * thus it is dynamic in nature
 * usually in same application design for date picker is same, enabling code reusability

 * Types :
        * 1) JQuery Date picker :
                    * here if input box is given, we can use send keys to type the date in specified format
                    * otherwise we need to implement logic to select from date picker
        * 2) Custom Modern Date Picker :
 * */
public class DatePickers {
    /**
     * Normally date picker have the elements in form of send keys,div,li,table
     * the month and year can be in form of navigation buttons or dropdowns
     * the dates in a month can be in form of table rows or nested div tags
     * similarly time is also usually in form of dropdowns or send keys
     * */


    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        /// 1) jquery date picker
//        jqueryDatepicker.jquery_datepicker(driver);
//        jqueryDatepicker.simple_jquery_date_selector(driver);

        /// 2) dropdown year date picker
//        dropdownYearDatepicker.dropdown_date_picker_date_month_year(driver);
//        dropdownYearDatepicker.dropdown_date_picker_date_time(driver);

        /// 3) comparing Months using month objects
//        Month_comparison.main(new String[]{});

        /// 4) Assignment date picker
        AssignmentDatePicker.dummy_ticket_picker(driver);

    }
static class Month_comparison{
        public static void main(String[] args) {
            String curr_month="January";
            String expected_month="December";
            /// we can convert this month into month object to compare with other months
            /// expected_month > curr_month -> future
            /// expected_month < curr_month -> past

            initialise_month_map();

            Month curr_month_obj=string_to_month(curr_month);
            Month expected_month_obj=string_to_month(expected_month);

            int result=expected_month_obj.compareTo(curr_month_obj);
            /// returns negative zero or positive integer comparing the enum order of month objects
            /// if negative then past, if positive then future, if 0 then same
            System.out.println(result);
        }

        static HashMap<String,Month> monthMap;
        static void initialise_month_map(){
            monthMap= new HashMap<>();
            monthMap.put("January",Month.JANUARY);
            monthMap.put("February",Month.FEBRUARY);
            monthMap.put("March",Month.MARCH);
            monthMap.put("April",Month.APRIL);
            monthMap.put("May",Month.MAY);
            monthMap.put("June",Month.JUNE);
            monthMap.put("July",Month.JULY);
            monthMap.put("August",Month.AUGUST);
            monthMap.put("September",Month.SEPTEMBER);
            monthMap.put("October",Month.OCTOBER);
            monthMap.put("November",Month.NOVEMBER);
            monthMap.put("December",Month.DECEMBER);
        }
        static Month string_to_month(String month_str){
            Month vmonth=monthMap.get(month_str);
            if (vmonth==null){
                System.out.println("invalid month");
            }        return vmonth;
        }
    }

static class jqueryDatepicker{
    static void jquery_datepicker(WebDriver driver){

        int date,month,year;
        date=31;
        month=12;
        year=2110;

        driver.get("https://jqueryui.com/datepicker/");
        WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));

        /// here we have a frame so switch to it
        driver.switchTo().frame(0);

        /// 1) using input box to send keys for date in specified format
//        mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='hasDatepicker']"))).sendKeys("11/24/2025"); // mm/dd/yyy

        /// 2) using logic for date picker

        mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='hasDatepicker']"))).click();

        /// we use while loop to compare the month and year with date picker if same then move to comparing date, otherwise use arrow buttons to move
        /// select month and year
        select_month_year(driver,month,year);
        /// in real applications either past or future date selection is allowed not both

        select_date(driver,date);

    }
    static void select_month_year(WebDriver driver,int month,int year){
        boolean greater=false;
        boolean smaller =false;
        while (true){
            int current_month=month_to_int(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
            int current_year=Integer.parseInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());

            if (current_year==year&&current_month==month)
            /// select date now
                break;

            else if(!greater&&!smaller){
                greater=is_greater(month,year,current_month,current_year);
                smaller=!greater;
            }
            if(greater)
            /// click next month
                driver.findElement(By.xpath("//span[@class=\"ui-icon ui-icon-circle-triangle-e\"]")).click();

            else
            /// click previous month
                driver.findElement(By.xpath("//span[@class=\"ui-icon ui-icon-circle-triangle-w\"]")).click();
        }
    }
    static void select_date(WebDriver driver,int date){

        WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));

        /// capturing all the dates then looping to get the exact date
//        List<WebElement> dates=mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td")));  /// also captures the empty spaces
//        List<WebElement> dates=mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td/a")));  /// no empty spaces included
//
//        for(WebElement ele:dates){
//            if(ele.getText().equals(date+"")){
//                ele.click();
//                break;
//            }
//        }

        /// getting the exact date directly
//        mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td[text()='"+date+"']"))).click(); /// doesn't work with normal text
        mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td/a[text()='"+date+"']"))).click();  /// go to anchor tag to use normal text comparison
//        mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td[normalize-space()='"+date+"']"))).click(); /// can use normalize-space to compare the text more flexibly without anchor tag


    }
    static boolean is_greater(int month_1,int year_1,int month_2,int year_2){

        if (year_1>year_2)
            return true;
        else if (year_1<year_2)
            return false;
        else {
            return month_1 > month_2;
        }
    }
    static int month_to_int(String month){
        switch (month){
            case "January":return 1;
            case "February":return 2;
            case "March":return 3;
            case "April":return 4;
            case "May":return 5;
            case "June":return 6;
            case "July":return 7;
            case "August":return 8;
            case "September":return 9;
            case "October":return 10;
            case "November":return 11;
            case "December":return 12;
            default:return 0;

        }    }

    static void simple_jquery_date_selector(WebDriver driver){
        driver.get("https://jqueryui.com/datepicker/");
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//input[@class='hasDatepicker']")).click();

        String date,month,year;
        date="31";
        month="December";
        year="2023";

//        future_date_selector(driver,date,month,year);
        past_date_selector(driver,date,month,year);
    }
    static void future_date_selector(WebDriver driver,String date,String month,String year){
        while (true){
            String current_month=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
            String current_year=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

            if (current_month.equals(month)&&current_year.equals(year))
                break;

            driver.findElement(By.xpath("//span[@class=\"ui-icon ui-icon-circle-triangle-e\"]")).click();

        }
        driver.findElement(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td/a[text()='"+date+"']")).click();

    }
    static void past_date_selector(WebDriver driver,String date,String month,String year){
        while (true){
            String current_month=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
            String current_year=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

            if (current_month.equals(month)&&current_year.equals(year))
                break;


            driver.findElement(By.xpath("//span[@class=\"ui-icon ui-icon-circle-triangle-w\"]")).click();
        }
        driver.findElement(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td/a[text()='"+date+"']")).click();
    }
}

static class dropdownYearDatepicker{
        static void test_automation(WebDriver driver){
            driver.get("https://testautomationpractice.blogspot.com/");

            /// select date 1
            driver.findElement(By.xpath("//input[@id=\"start-date\"]")).sendKeys("25-06-0000");
            /// select date 2
            driver.findElement(By.xpath("//input[@id=\"end-date\"]")).sendKeys("25-06-9999999999");
            /// click on submit button
            driver.findElement(By.xpath("//button[@class='submit-btn' and @onClick='calculateRange()']")).click();
            /// print the number of days
            System.out.println(driver.findElement(By.xpath("//div[@id='result']")).getText());

            driver.quit();
        }
        static void dropdown_date_picker_date_month_year(WebDriver driver){
            driver.get("https://demoqa.com/date-picker");

            String date="13";
            String month="April";
            String year="2028";

            /// 1) selecting date month year
            driver.findElement(By.xpath("//input[@id='datePickerMonthYearInput']")).click();

            /// 1.1) Selecting month
            WebElement month_drop=driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
            Select month_select=new Select(month_drop);
            month_select.selectByVisibleText(month);
//            month_select.selectByIndex(3); /// zero based index
//            month_select.selectByValue("0");


            /**
             * printing all months from select
            List<WebElement>month_list= month_select.getOptions();
            for (WebElement month:month_list){
                System.out.println(month.getText());
            }
             */

            /// 1.2) Selecting year
            WebElement year_drop=driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
            Select year_select=new Select(year_drop);
            year_select.selectByVisibleText(year);

            /// 1.3) Selecting date
            List<WebElement> all_dates=driver.findElements(By.className("react-datepicker__day"));
            int i;

            for(i=0;i<all_dates.size();i++){
                if (all_dates.get(i).getText().equals("1"))
                    break;
            }
            for (int j=i;j<all_dates.size();j++){
                if (all_dates.get(j).getText().equals(date)){
                    all_dates.get(j).click();
                    break;
                }
            }

        }
        static void dropdown_date_picker_date_time(WebDriver driver){
            driver.get("https://demoqa.com/date-picker");

            String date="25";
            String month="September";
            int year=2003;
            String time="03:45";

            /// 2) selecting date month year
            driver.findElement(By.xpath("//input[@id='dateAndTimePickerInput']")).click();

            /// 2.1) Selecting month
            driver.findElement(By.xpath("//div[@class=\"react-datepicker__month-read-view\"]")).click();
            List<WebElement>month_list=driver.findElements(By.className("react-datepicker__month-option"));
            for(WebElement mon:month_list){
                if (mon.getText().equals(month)){
                    mon.click();
                    break;
                }
            }

            /// 2.2) Selecting year
            driver.findElement(By.xpath("//div[@class='react-datepicker__year-read-view']")).click();

            int max_year=Integer.parseInt(driver.findElements(By.className("react-datepicker__year-option")).get(1).getText());

            if (year>max_year){
                WebElement up_button=driver.findElements(By.className("react-datepicker__year-option")).get(0);
                int clicks=year-max_year;
                for(int i=0;i<clicks;i++){
                    up_button.click();
                }
                driver.findElements(By.className("react-datepicker__year-option")).get(1).click();
            }
            else if(year<(max_year-10)){
                WebElement down_button=driver.findElements(By.className("react-datepicker__year-option")).get(12);
                int clicks=max_year-year-10;
                for(int i=0;i<clicks;i++){
                    down_button.click();
                }
                driver.findElements(By.className("react-datepicker__year-option")).get(11).click();
            }
            else{
                int year_index=max_year-year+1;
                driver.findElements(By.className("react-datepicker__year-option")).get(year_index).click();
            }



            /// 2.3) Selecting date
            List<WebElement> all_dates=driver.findElements(By.className("react-datepicker__day"));
            int i;
            for(i=0;i<all_dates.size();i++){
                if (all_dates.get(i).getText().equals("1"))
                    break;
            }
            for (int j=i;j<all_dates.size();j++){
                if (all_dates.get(j).getText().equals(date)){
                    all_dates.get(j).click();
                    break;
                }
            }
            /// 2.4) Selecting time
            driver.findElement(By.xpath("//ul[@class='react-datepicker__time-list']/li[text()='"+time+"']")).click();
        }



/**  here footer is covering the widget card so using javascript to scroll down and then clicking it
 driver.get("https://demoqa.com/");
 //            driver.get("https://demoqa.com/date-picker");
 WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(20));
 //            mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='category-cards']/div[4]"))).click();
 //            mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id=\"item-2\"]/span"))).click();
 //      _13DynamicPagination.pagesourcefile(driver,"demoqa_page_source");


 WebElement widgetCard = mywait.until(ExpectedConditions.presenceOfElementLocated(
 By.xpath("//div[@class='category-cards']/div[4]")));
 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", widgetCard);
 widgetCard.click();

 widgetCard=mywait.until(ExpectedConditions.presenceOfElementLocated(
 By.xpath("//li[@id=\"item-2\"]/span")));
 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", widgetCard);
 widgetCard.click();
 * */

    }

static class AssignmentDatePicker{

    static void dummy_ticket_picker(WebDriver driver){
//        driver.get("https://dummy-tickets.com/buyticket");
        driver.get("https://www.dummy-tickets.com/dummy-ticket-for-visa-application/");

    }
    }
}
/**
 normalize-space():
     Trims Whitespace:
     Removes leading/trailing spaces
     Converts multiple internal spaces to single spaces
     Example: " 5 " becomes "5"

     Handles Hidden Formatting:
     Eliminates non-breaking spaces (&nbsp;)
     Removes line breaks (\n) and tabs (\t)

     Normalizes Text:
     Makes text comparison more forgiving
     Accounts for inconsistent DOM formatting
 */