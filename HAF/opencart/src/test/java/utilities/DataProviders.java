package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    /**
     * Create all data providers here with unique data provider name attribute
     * */
    /// DataProvider 1

    @DataProvider(name="LoginData" )
    public String [][] getLoginData() throws IOException {
        String filepath=".\\testData\\Opencart_LoginData.xlsx";

        ExcelUtility excelUtility= new ExcelUtility(filepath);
        String sheet="Sheet1";

        /// output array must have same number of rows and columns
        int total_rows=excelUtility.getRowCount(sheet);
        int total_cells=excelUtility.getCellCount(sheet,1);

        String[][] loginData= new String[total_rows][total_cells]; /// 2D array for data to pass to test case
        for (int i=1;i<=total_rows;i++)  ///  must start from 1; at index 0 heading is there
            for (int j=0;j<total_cells;j++) /// starts from 0 till last index
                loginData[i-1][j]=excelUtility.getCellData(sheet,i,j);

        return loginData;
    }
    /// DataProvider 2

    /// DataProvider 3

    /// DataProvider 4


}
