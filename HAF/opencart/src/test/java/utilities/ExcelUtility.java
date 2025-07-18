package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    /**
     * here we will make utility methods to call for all the Excel operations
     * we have methods as :
     * 1) getRowCount
     * 2) getCellCount
     * 3) getCellData
     * */
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public XSSFCellStyle style;
    String filepath;

    ExcelUtility(String filepath){
        this.filepath=filepath;
    }

    public int getRowCount(String sheetname) throws IOException {
        fi=new FileInputStream(filepath);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(sheetname);
        int rowCount=sheet.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }
    public int getCellCount( String sheetname, int rownum) throws IOException{
        fi=new FileInputStream(filepath);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(sheetname);
        row=sheet.getRow(rownum);
        int cellCount=row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }
    public String getCellData( String sheetname, int rownum,int colnum)throws IOException{
        fi=new FileInputStream(filepath);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(sheetname);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        DataFormatter formatter= new DataFormatter();
        String data;
        try {
            data=formatter.formatCellValue(cell); /// always returns the value as string no matter the cell type
        }catch (Exception e){
            data="";
        }
        wb.close();
        fi.close();
        return data;
    }
    public  void setCellData( String sheetname, int rownum,int colnum,String data)throws IOException{

        /// here we first check if the file exists or not, since if file is not found then Exception is thrown
        File f= new File(filepath);
        if (!f.exists()){
            wb= new XSSFWorkbook();
            fo= new FileOutputStream(filepath);
            wb.write(fo);
        }
        /// now open the file
        fi=new FileInputStream(filepath);
        wb=new XSSFWorkbook(fi);

        /// also check if sheet exists or not, if not then create one
        if (wb.getSheetIndex(sheetname)==-1) /// check if sheet not does exist
            wb.createSheet(sheetname);
        sheet=wb.getSheet(sheetname);

        /// check if row does not exist
        if (sheet.getRow(rownum)==null)
            sheet.createRow(rownum);
        row=sheet.getRow(rownum);

        cell=row.createCell(colnum); /// creating a new cell
        cell.setCellValue(data);

        fo=new FileOutputStream(filepath);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }

    public void fillGreenColor( String sheetname, int rownum,int colnum)throws IOException{
        fi=new FileInputStream(filepath);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(sheetname);

        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        style=wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo= new FileOutputStream(filepath);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();

    }
    public void fillRedColor( String sheetname, int rownum,int colnum)throws IOException{
        fi=new FileInputStream(filepath);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(sheetname);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        style=wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo= new FileOutputStream(filepath);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();

    }
}
