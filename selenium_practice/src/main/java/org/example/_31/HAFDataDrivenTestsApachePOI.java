public class HAFDataDrivenTestsApachePOI{
    /**
         * 5) Login Test Case :
                * steps :
                         * 1) Create and update page object classes. LoginPage, MyAccountPage - new classes HomePage update by adding login link element
                         * 2) Create Login Test
                         * 3) Add entry testng.xml
                         * we need to add the login element in HomePage class, make another PageObjectClass for login and logged in page, make another test class for the login test
                         * new PageObjectClasses : LoginPage, MyAccountPage
                         * new test class : TC002LoginTest -> click on login button, enter login details, verify the logged in My Account message
         * 6) Data Driven Login Test :
                         * in order to perform login tests for multiple login details -> valid/invalid we will perform data driven testing
                         * steps :
                                 * 1) Prepare test data in Excel, place the Excel file inside the testData folder.
                                 * 2) Create ExcelUtility class under utilities package.
                                 * 3) Update Page Object class MyAccountPage, add logout link element
                                 * 4) Create DataProviders class in utilities package to maintain data providers for data driven tests.
                                 * 5) Create Login DataDriven Test under testCases package.
                                 * 6) Add an Entry in testng.xml file
                         * the ExcelUtility file has methods : getRowCount(), getCellCount(), getCellData(), setCellData(), fillGreenColor(), fillRedColor()
                         * all the methods are nonstatic so we to create the object of class here
                         * we pass the file path in constructor and use it to perform operations on file
         * now we make a DataProviders class in utilities with method which will read the Excel file, put it in 2D array and pass it to test case, we will use the ExcelUtility file here
         * create another test class : TC003DataDrivenLoginTest to use data provider with valid and invalid data, keeping the login and logout order properly
         * */
}