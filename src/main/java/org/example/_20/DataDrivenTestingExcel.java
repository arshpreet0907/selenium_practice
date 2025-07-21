package org.example._20;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class DataDrivenTestingExcel {
    /**
     * We first need to know how to interact with Excel files
     * Selenium itself doesn't support interactions with MS Excel it can just interact with web elements
     * for this we need to add 3rd party libraries : apache poi (java api for MS documents)
     * we need to add 2 dependencies : apache poi common, apache poi ooxml
     * https://poi.apache.org/
     * hierarchy of Excel file:
            * File
                * Workbook
                    * Sheets
                        * Rows
                            * Cells
     * Data is in cells, but we cannot directly interact with cells
     * we need to open the file go to specific workbook then specific sheet(worksheet) then specific row at last specific cell
     * for this purpose we use the following :
     * File : java.io.FileInputStream and java.io.FileOutputStream, to read and write to a file
     * classes provided by apache poi :
            * XSSFWorkbook
            * XSSFSheet
            * XSSFRow
            * XSSFCell
     * only two operations : reading and writing
     * here we focus on interacting with Excel files
     * we need to understand how it calculates the total number of rows and columns in a sheet and customize accordingly

     * we also needed to add the following dependency to remove a warning : org.apache.logging.log4j (log4j-core) and org.apache.logging.log4j (log4j-api)

     * Excel starts rows index from 0 and cell index from 1, but java starts both from 0.
     *  */
    static FileInputStream data_file;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static int total_rows;
    static int total_cells;

    public static void main(String[] args) {

        /// initialise file, workbook, sheet, row
        initialise_workbook();

        /// get total number of rows and cols
        total_number_of_rows_cols();
//        System.out.println("total number of rows : "+total_rows+"\ntotal number of cols : "+total_cells);
        /// read all data from file
//        read_all_data();

        /// write data into new Excel file
//        write_into_file();

        /// write dynamic data into new Excel file
        write_dynamic_data();

        /// close workbook and file after we are done
        close_file_workbook();

        }
    static void initialise_workbook(){
        try {
            /// file opened in read mode
            data_file = new FileInputStream(System.getProperty("user.dir") + "\\excel files\\data.xlsx");

            /// workbook and file are same thing we are just making an object we can interact with
            /// open the work book of file
            workbook = new XSSFWorkbook(data_file);

            /// extract particular sheet
//             sheet=workbook.getSheet("Sheet1"); // name
            sheet = workbook.getSheetAt(0); // index
        }
        catch (Exception e){
            ///  java.io.FileNotFoundException and java.io.IOException
            System.out.println(e.getMessage());
        }
    }
    static void total_number_of_rows_cols(){
        /// find the rows and cells needed to read
        total_rows=sheet.getLastRowNum(); // returns last row number
        /// extract particular row find number of cells in it
        total_cells=sheet.getRow(0).getLastCellNum();

    }
    static void read_all_data(){
        /// read all data
        for (int r=0;r<=total_rows;r++){

            XSSFRow curr_row=sheet.getRow(r);

            for (int c=0;c<total_cells;c++){

                XSSFCell curr_cell=curr_row.getCell(c);

                System.out.print("| "+curr_cell.toString()+" |");

            }
            System.out.println();
        }
    }
    static void close_file_workbook(){
        try {
            workbook.close();
            data_file.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    static void write_into_file(){
        /// here we will do opposite reading operations
     try {
         ///creating a new workbook, but we will pass our file at last, after writing data
         XSSFWorkbook write_workbook = new XSSFWorkbook();

         /// creating a new sheet
         XSSFSheet write_sheet=write_workbook.createSheet("Data");

         /// creating a new row
        XSSFRow write_row_1=write_sheet.createRow(0);
        write_row_1.createCell(0).setCellValue("Java");
        write_row_1.createCell(1).setCellValue(17);
        write_row_1.createCell(2).setCellValue("Automation");

        XSSFRow write_row_2=write_sheet.createRow(1);
        write_row_2.createCell(0).setCellValue("Python");
        write_row_2.createCell(1).setCellValue(3);
        write_row_2.createCell(2).setCellValue("Automation");

        XSSFRow write_row_3=write_sheet.createRow(2);
        write_row_3.createCell(0).setCellValue("C#");
        write_row_3.createCell(1).setCellValue(5);
        write_row_3.createCell(2).setCellValue("Automation");

         FileOutputStream write_file = new FileOutputStream(System.getProperty("user.dir") + "\\excel files\\write_data.xlsx");
         /// pass data to out file
         write_workbook.write(write_file);

         System.out.println("File is created");

         write_workbook.close();
         write_file.close();

     }
     catch (Exception e){
         System.out.println(e.getMessage());
     }
    }
    static void write_dynamic_data(){

        try {
            /// create objects of scanner, FileOutputStream, workbook, sheet
            Scanner sc= new Scanner(System.in);
            FileOutputStream write_file = new FileOutputStream(System.getProperty("user.dir") + "\\excel files\\write_dynamic_data.xlsx");
            XSSFWorkbook write_workbook = new XSSFWorkbook();
            XSSFSheet write_sheet = write_workbook.createSheet("Data");

            /// get total number of rows and cols from user
            System.out.println("enter number of rows :");
            int rows=sc.nextInt();
            System.out.println("enter number of cols :");
            int cols=sc.nextInt();

            // Consume the leftover newline characters
            sc.nextLine();

            for (int r=0;r<rows;r++){
                /// dynamic row
                XSSFRow row=write_sheet.createRow(r);

                for (int c=0;c<cols;c++){
                    XSSFCell cell= row.createCell(c);
                    System.out.printf("enter value for %d row and %d col\n",(r+1),(c+1));
//                    cell.setCellValue(sc.nextLine()); /// for all types of data
                    cell.setCellValue(sc.nextInt()); /// only for int
                }
            }

            write_workbook.write(write_file);
            write_workbook.close();
            write_file.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("File is created with dynamic data");
    }
    }


