package org.example._21;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import org.example.ExcelUtility;

public class DataDrivenTestingExcelPropertiesFile {
    static class FDCalculator{
    /**
     * for each test case the Excel operations are same so we make utility methods (custom methods) for these common methods
     * this enables code reusability
     * we will use our own ExcelUtility class with static methods
     */
    /**
     * For Data Driven testing :
            * Functionality (Test Case) : need to understand
            * Prepare test data : in Excel file
            * Develop automation script
     * when getting data from Excel cell, if there is a numeric value it will return as a floating value
     * whether getting as string or numeric value to compare with integer we need to type cast the same

     * when initialising the fo object make sure that we have workbook initialised for the same file otherwise the file will be corrupted before reading
     * close the Excel file while running the code
     * */
   static WebDriver driver;
    public static void main(String[] args) {
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        /// verifying simple interest calculation using Excel file data
        verifySimpleInterest(driver);

        /// verify data from excel sheet
//        verify_excel_data(driver);

        /// verify data from excel sheet using utils
//        verify_excel_data_with_utils();
    }
    static void verifySimpleInterest(WebDriver driver){
        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");

        WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));
        mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"wzrk-cancel\"]"))).click();

        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,400);");

        WebElement principal_element=driver.findElement(By.xpath("//input[@id=\"principal\"]"));
        WebElement rate_of_interest_element=driver.findElement(By.xpath("//input[@id=\"interest\"]"));
        WebElement period_element=driver.findElement(By.xpath("//input[@id=\"tenure\"]"));
        WebElement d_m_y_select_element=driver.findElement(By.xpath("//select[@id=\"tenurePeriod\"]"));
        WebElement frequency_element=driver.findElement(By.xpath("//select[@id=\"frequency\"]"));
        WebElement calculate_element=driver.findElement(By.xpath("//a[@onclick=\"return getfdMatVal(this);\"]"));
        WebElement clear_element=driver.findElement(By.xpath("//a[@onclick=\"javascript:reset_fdcalcfrm();\"]"));
        WebElement maturity_amount_element=driver.findElement(By.xpath("//span[@id=\"resp_matval\"]"));

        /// data to enter
        int principal_amount=1000;
        double interest_rate=10;
        int period=2;
        /// select
        String tenure_period="year(s)"; /// day(s); month(s); year(s)
        String frequency="Simple Interest"; /// Simple Interest; Compounded Monthly; Compounded Quarterly; Compounded Half Yearly; Compounded Yearly

        principal_element.sendKeys(principal_amount+"");
        rate_of_interest_element.sendKeys(interest_rate+"");
        period_element.sendKeys(period+"");

        Select tenure_period_select=new Select(d_m_y_select_element);
        tenure_period_select.selectByVisibleText(tenure_period);

        Select frequency_select=new Select(frequency_element);
        frequency_select.selectByVisibleText(frequency);

        calculate_element.click();

        double maturity_amount=Double.parseDouble(maturity_amount_element.getText());
        double final_amount=1200;
        if (maturity_amount==final_amount){
            System.out.println("verification successful");
        }
        else {
            System.out.println("validation failed");
        }
        clear_element.click();
    }

    static void verify_excel_data(WebDriver driver){
        /// tenure period select : day(s); month(s); year(s)
        /// frequency select : Simple Interest; Compounded Monthly; Compounded Quarterly; Compounded Half Yearly; Compounded Yearly
        try {
            initialise_web_elements();
//            System.out.println("web elements initialised");
            long start=System.currentTimeMillis();
            work_book_initialise();
//            System.out.println("workbook initialised");


            int no_of_rows=sheet.getLastRowNum();

            for (int i=1;i<=no_of_rows;i++){
                boolean result=verifyAmount(i);
//                System.out.println(result ? "Welcome back!" : "Please log in");

                sheet.getRow(i).createCell(7).setCellValue(result);
            }
            workbook.write(fo);
            workbook.close();
            fo.close();
            fi.close();
            driver.quit();
            System.out.println("testing execution completed");
            System.out.println((double)(System.currentTimeMillis()-start)/1000);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static FileInputStream fi;
    static FileOutputStream fo;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    static WebElement principal_element;
    static WebElement rate_of_interest_element;
    static WebElement period_element;
    static WebElement d_m_y_select_element;
    static WebElement frequency_element;
    static WebElement calculate_element;
    static WebElement clear_element;
    static WebElement maturity_amount_element;

    static void work_book_initialise()throws IOException{
        fi = new FileInputStream(System.getProperty("user.dir") + "\\excel files\\amount_data.xlsx");
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheetAt(0);
        fo =new FileOutputStream(System.getProperty("user.dir") + "\\excel files\\amount_data.xlsx");

    }
    static void initialise_web_elements(){
        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");

        WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));
        mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"wzrk-cancel\"]"))).click();

        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,400);");
        principal_element=driver.findElement(By.xpath("//input[@id=\"principal\"]"));
        rate_of_interest_element=driver.findElement(By.xpath("//input[@id=\"interest\"]"));
        period_element=driver.findElement(By.xpath("//input[@id=\"tenure\"]"));
        d_m_y_select_element=driver.findElement(By.xpath("//select[@id=\"tenurePeriod\"]"));
        frequency_element=driver.findElement(By.xpath("//select[@id=\"frequency\"]"));
        calculate_element=driver.findElement(By.xpath("//a[@onclick=\"return getfdMatVal(this);\"]"));
        clear_element=driver.findElement(By.xpath("//a[@onclick=\"javascript:reset_fdcalcfrm();\"]"));
        maturity_amount_element=driver.findElement(By.xpath("//span[@id=\"resp_matval\"]"));

    }
    static String pass="Pass";
    static String fail="Fail";
    static boolean verifyAmount(int rownum) throws IOException {

        XSSFRow row=sheet.getRow(rownum);

        int principal_amount=(int)row.getCell(0).getNumericCellValue();
        double interest_rate=row.getCell(1).getNumericCellValue();
        int period=(int)row.getCell(2).getNumericCellValue();
        String tenure_value=row.getCell(3).getStringCellValue();
        String frequency_value=row.getCell(4).getStringCellValue();
        double final_amount=row.getCell(5).getNumericCellValue();
        String pass_fail=row.getCell(6).getStringCellValue();

        principal_element.sendKeys(principal_amount+"");
        rate_of_interest_element.sendKeys(interest_rate+"");
        period_element.sendKeys(period+"");

        Select tenure_period_select=new Select(d_m_y_select_element);
        tenure_period_select.selectByVisibleText(tenure_value);

        Select frequency_select=new Select(frequency_element);
        frequency_select.selectByVisibleText(frequency_value);

        calculate_element.click();

        double maturity_amount=Double.parseDouble(maturity_amount_element.getText());

        boolean result=(maturity_amount==final_amount&&pass_fail.equals(pass))||(maturity_amount!=final_amount&&pass_fail.equals(fail));
        clear_element.click();
        return result;
    }

    static void verify_excel_data_with_utils(){
        initialise_web_elements();

        long start =System.currentTimeMillis();
        String file=System.getProperty("user.dir") + "\\excel files\\amount_data.xlsx";
        String sheet="Sheet1";
        try {
            int rows=ExcelUtility.getRowCount(file,sheet);

            for (int i=1;i<=rows;i++){
                String principal=ExcelUtility.getCellData(file,sheet,i,0);
                String interest=ExcelUtility.getCellData(file,sheet,i,1);
                String period=ExcelUtility.getCellData(file,sheet,i,2);
                String period_tenure=ExcelUtility.getCellData(file,sheet,i,3);
                String frequency=ExcelUtility.getCellData(file,sheet,i,4);
                String maturity=ExcelUtility.getCellData(file,sheet,i,5);
                String expected=ExcelUtility.getCellData(file,sheet,i,6);


                principal_element.sendKeys(principal);
                rate_of_interest_element.sendKeys(interest);
                period_element.sendKeys(period);
                new Select(d_m_y_select_element).selectByVisibleText(period_tenure);
                new Select(frequency_element).selectByVisibleText(frequency);
                calculate_element.click();

                boolean maturity_flag = Double.parseDouble(maturity_amount_element.getText())==Double.parseDouble(maturity);

                boolean test_pass=(maturity_flag&&(expected.equals("Pass")))||(!maturity_flag&&(expected.equals("Fail")));

                System.out.println(test_pass? "test passed for row : "+i:"test failed for row : "+i);

                ExcelUtility.setCellData(file,sheet,i,7,test_pass+"");
                if (test_pass)
                    ExcelUtility.fillGreenColor(file,sheet,i,7);
                else
                    ExcelUtility.fillRedColor(file,sheet,i,7);
                clear_element.click();
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        driver.quit();
        System.out.println((double)(System.currentTimeMillis()-start)/1000);
    }
    }
    static class property_file{
        /**
         * Editing the properties file : xyz.properties
         * it is used to specify the configurations : e.g. browser name, username, password
         * reading properties file
         * */
        public static void main(String[] args) {
            read_properties_file();
        }
        static void read_properties_file(){
            /**
             * It is similar to a text file no syntax of java
             * having a dummy config.properties file
             * common data for all test cases is placed here
             * there should always be data in the form of key and value

             * We need to create a Properties class object it is present in java.util
             * it is a part of java not selenium
             * then open the file and read it
             * then load the properties file

             * here we go for page object model
             * */

            try {

                /// properties object
                Properties properties= new Properties();

                /// file in read mode
                FileInputStream fi=new FileInputStream(System.getProperty("user.dir")+"\\excel files\\config.properties");

                /// load the properties
                properties.load(fi);

                /// reading properties pass the key
                String url=properties.getProperty("appurl");
                String email=properties.getProperty("email");
                String password=properties.getProperty("password");
                String orderid=properties.getProperty("orderid");
                String customerid=properties.getProperty("customerid");

                System.out.println(url+" "+email+" "+password+" "+orderid+" "+customerid);

                /// reading all the keys
                /// set of strings
                Set<String> keys_str =properties.stringPropertyNames();
                System.out.println(keys_str);

                /// set of objects
                Set<Object> keys_obj=properties.keySet();
                System.out.println(keys_obj);

                /// reading all the values
                Collection<Object>values=properties.values();
                System.out.println(values);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
    static class Assignment{
        /**
         * New thing encountered : mat select
         * it is not a normal select we cannot use it with select class
         * for this we need to click using the x path
         * then look for desired mat option using x path
         * it is different from other dropdowns, it is not select nor div
         * */
        public static void main(String[] args) throws IOException {
            WebDriver driver =new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");

            driver.findElement(By.id("onetrust-reject-all-handler")).click(); /// rejecting cookies

            WebElement amount_ele=driver.findElement(By.id("mat-input-0")); /// initial amount
            WebElement months_ele=driver.findElement(By.id("mat-input-1")); /// months
            WebElement interest_ele=driver.findElement(By.id("mat-input-2")); /// interest
            WebElement compound_frequency_ele=driver.findElement(By.xpath("//*[@id=\"mat-select-0\"]")); /// compound frequency
            WebElement calculate_ele=driver.findElement(By.id("CIT-chart-submit")); /// calculate
            WebElement final_amount_ele=driver.findElement(By.id("displayTotalValue")); /// final amount


            String file=System.getProperty("user.dir")+"\\excel files\\assignment_data.xlsx";
            String sheet="Sheet1";
            int total_rows=ExcelUtility.getRowCount(file,sheet);

            for (int i=1;i<=total_rows;i++){
                amount_ele.clear();
                months_ele.clear();
                interest_ele.clear();

                String amount=ExcelUtility.getCellData(file,sheet,i,0);
                String months=ExcelUtility.getCellData(file,sheet,i,1);
                String interest=ExcelUtility.getCellData(file,sheet,i,2);
                String compound_frequency=ExcelUtility.getCellData(file,sheet,i,3);
                String final_amount=ExcelUtility.getCellData(file,sheet,i,4);

                amount_ele.sendKeys(amount);
                months_ele.sendKeys(months);
                interest_ele.sendKeys(interest);
                compound_frequency_ele.click();
                driver.findElement(By.xpath("//mat-option[contains(., '"+compound_frequency+"')]")).click();

                calculate_ele.click();

                boolean result=final_amount.equals(final_amount_ele.getText());
                ExcelUtility.setCellData(file,sheet,i,6,result?"Pass":"Fail");
            }
            System.out.println("test completed");
            driver.quit();

               }
    }
}
