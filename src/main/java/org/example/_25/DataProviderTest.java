package org.example._25;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.Duration;

public class DataProviderTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }
    /**
     * here to pass the data from data provider to our test method we added the dataProvider attribute with specified name of the data method
     * here to pass the data to test method we add the arguments in our test methods in the order we passed the data values in the array
     * here we need to pay attention to the order of arguments, it should match the data provided in the data provider method
     * */

    @Test(dataProvider = "2D_data")
    void testLogin(String username, String password) throws InterruptedException {
            /// to web page
            driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
            /// login
            driver.findElement(By.xpath("//input[@id=\"input-email\"]")).sendKeys(username); /// instead of hardcoding the data here we pass the parameters
            driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys(password); /// used for 2D array
//        driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys("test@123"); /// used for 1D array
            driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

            /// waiting for elements to load
            Thread.sleep(2000);

            /// open dropdown
            /// logout button available
            driver.findElement(By.xpath("//ul[@class='list-inline']/li[2]")).click();
            boolean status = driver.findElement(By.xpath("//ul[@class='list-inline']/li[2]/ul/li[5]")).isDisplayed();
            if (status) {
                /// logout btn
                driver.findElement(By.xpath("//ul[@class='list-inline']/li[2]/ul/li[5]")).click();
                Assert.assertTrue(true);
            } else
                Assert.fail();

    }


    /// this method is used for parallel test execution
    /// this is used when the data provider has an attribute parallel=true
//    @Test(dataProvider = "2D_data_parallel")
    void testLogin_parallel(String username, String password) throws InterruptedException {

        WebDriver driver = new ChromeDriver();;
        try {

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.manage().window().maximize();
            driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
            driver.findElement(By.xpath("//input[@id=\"input-email\"]")).sendKeys(username);
            driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys(password);
            driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//ul[@class='list-inline']/li[2]")).click();
            boolean status = driver.findElement(By.xpath("//ul[@class='list-inline']/li[2]/ul/li[5]")).isDisplayed();
            if (status) {
                driver.findElement(By.xpath("//ul[@class='list-inline']/li[2]/ul/li[5]")).click();
                Assert.assertTrue(true);
            } else
                Assert.fail();
        }
        finally {
            driver.quit();
        }



    }


    @AfterClass
    void teardown() {
        driver.quit();
    }

    @DataProvider(name="1D_data",indices = {1,2,3}) /// this enables us to choose which index from the data provider will be passed to the test method.
    Object[] loginData_1(){
        return new Object[]{
                "abc@gamil.com",
                "xyz@gamil.com",
                "john@gmail.com",
                "dummy1221@gmail.com",
                "johncanedy@gmail.com"};
    }

    @DataProvider(name = "2D_data") /// only index is provided not the range
    Object[][] loginData() {
        return new Object[][]{
                {"abc@gamil.com","test@123"},
                {"xyz@gamil.com","test@13"},
                {"john@gmail.com","test@12"},
                {"dummy1221@gmail.com","test@123"},
                {"johncanedy@gmail.com","test"}};


    }
    /// this type method must return an array or an iterator
    /// it's only job is to create and return the data
    /// here we are hardcoding the data in real scenario we get it from the Excel file
    /// add both valid and invalid data
    /// we made Object array since test data can be anything
    /// inorder to use this data provider we add dataprovider attribute in the @Test annotation
    /// in this attribute we must pass the name, pass this name value in the test method
    /// to use method in different class we can do the following :
            /// 1) Using dataProviderClass Attribute : along with name of data provider as attribute of @Test we can also add this attribute to specify the data provider cass e.g. dataProviderClass = TestDataProvider.class
            /// 2) Creating Local Data Provider Methods (Wrapper Approach) : returning the instance of data method using a local data provider
            /// 3)  Inheritance Approach : inherit the data provider class in test class use the data provider as a
    /// Always make your data provider methods static when they're in separate classes to avoid instantiation issues.

    /// if same name is given to multiple data providers then the defined latter (bottom) is used by testng
    /// TestNG follows this resolution order:
        /// Local class data providers (within the same test class)
        /// Inherited data providers (from parent classes)
        /// External data providers (specified via dataProviderClass)


    /**
     * here the array returned is interpreted by the testNG in the following manner:
     * A) Array of Object
             * 1) if 1D array is used then it passes each object in different test cases (1 parameter per test case)
             * 2) if 2D array is used then it uses the row for each test case and columns for multiple parameters in each test case
             * 3) TestNg cannot understand arrays like 3D,4D,etc.
             * 4) for achieving similar behavior we use custom objects as parameters or collections
     * B) Iterator of Object
             * 1) if Iterator of Object (Iterator<Object>) is used then it passes one Object per test case similar to 1D array
             * 2) if Iterator of 1D array of Object (Iterator<Object[]>) is used then it passes each array to each test case and values in array are used for multiple parameters per test case, similar to 2D array
     */


    /// here parallel attribute allows the parallel execution of the test cases
    /// to control the thread count we must use data-provider-thread-count="2" : attribute at suite level
    @DataProvider(name = "2D_data_parallel",parallel = true)
    Object[][] loginDataParallel() {
        return new Object[][]{
                {"abc@gamil.com","test@123"},
                {"xyz@gamil.com","test@13"},
                {"john@gmail.com","test@12"},
                {"dummy1221@gmail.com","test@123"},
                {"johncanedy@gmail.com","test"}};

    }
/// in order to control the thread count for this we can use the XML file and specify there
/// otherwise we can use (not working) :
    /**
     public class DataProviderThreadTransformer implements IAnnotationTransformer {
        @Override
        public void transform(ITestAnnotation annotation, Class testClass,
        Constructor testConstructor, Method testMethod) {
            if ("loginDataParallel".equals(testMethod.getName())) {
                annotation.setInvocationCount(5); // Number of data rows
                annotation.setThreadPoolSize(3); // Max threads for this DataProvider
            }
        }
    }
     <suite name="DynamicThreadSuite">
         <listeners>
            <listener class-name="com.example.DataProviderThreadTransformer"/>
         </listeners>
         <test name="DynamicThreadTest">
            <classes>
                <class name="com.example.LoginTests"/>
            </classes>
         </test>
     </suite>
     * */
    /// it did not work all the test cases started at the same time




    /// such method is used to know which text case is executing this data provider based on the actual name of the test method
    /// it can return different data based on which test method is calling it
    @DataProvider(name="parameterized method method")
    Object[][] parameterizedDataProvider(Method method){

        String methodName = method.getName();

        Class<?>[] parameterTypes = method.getParameterTypes();

        System.out.println("Method Name: " + methodName);
        System.out.println("Parameter Count: " + parameterTypes.length);

        // Different data based on method name
        switch (methodName) {
            case "testUserLogin":
                return new Object[][]{
                        {"regularUser", "password123", "user"},
                        {"adminUser", "admin456", "admin"}
                };
            case "testEmailValidation":
                return new Object[][]{
                        {"valid@email.com", true},
                        {"invalid-email", false},
                        {"@missing-local.com", false}
                };
            default:
                return new Object[][]{{"default", "value"}};
        }
    }

    /// such method is used to know which test case of XML file is executing this method
    /// also we can know which suite is under execution
    /// also we can know which groups are included and which are not
    /// it points to current test case under execution using which we can gather information about classes, methods, test cases, groups, suites and the XML file
    @DataProvider(name="parameterized method context")
    Object[][] parameterizedDataProvider_1(ITestContext iTestContext){
        String suiteName = iTestContext.getSuite().getName();

        String testName = iTestContext.getName();

        System.out.println("Suite Name: " + suiteName);
        System.out.println("Test Name: " + testName);

        // Return different data based on test context
        if (suiteName.equals("RegressionSuite")) {
            return new Object[][] {
                    {"regression_user1", "pass1"},
                    {"regression_user2", "pass2"},
                    {"regression_user3", "pass3"}
            };
        } else if (suiteName.equals("SmokeSuite")) {
            return new Object[][] {
                    {"smoke_user", "smoke_pass"}
            };
        }
        return new Object[][] {{"default_user", "default_pass"}};
    }

    /// such method is used for more advanced dynamic decision of which data to return
    @DataProvider(name="parameterized method method and context")
    Object[][] parameterizedDataProvider_2(Method method,ITestContext iTestContext){

        String methodName = method.getName();
        String suiteName = iTestContext.getSuite().getName();

        System.out.println("Processing: " + methodName + " in " + suiteName);

        // Complex logic based on both method and context
        if (suiteName.equals("RegressionSuite")) {
            if (methodName.contains("Login")) {
                return new Object[][] {
                        {"regression_admin", "admin123", "full_access"},
                        {"regression_user", "user123", "limited_access"},
                        {"regression_guest", "guest123", "read_only"}
                };
            } else if (methodName.contains("API")) {
                return new Object[][] {
                        {"api_key_1", "endpoint_1"},
                        {"api_key_2", "endpoint_2"}
                };
            }
        }

        return new Object[][] {{"basic", "data"}};
    }


    /**
     * methods to be used :
     * method.getName();
     * method.getParameterTypes();
     * iTestContext.getSuite().getName();
     * iTestContext.getName();
     * context.getIncludedGroups();
     * context.getExcludedGroups();
     * context.getCurrentXmlTest();
     * context.getStartDate());
     * context.getEndDate());
     * */
    /***
     * classes to be used :
     * XmlTest
     * ISuite
     */
    /**
     * Key Points About ITestContext Scope
         * Test-Level Scope: ITestContext represents the current <test> element being executed, not the entire XML file.
         * Multiple Contexts: If your XML has multiple <test> elements, each will have its own ITestContext when executed.
         * Sequential Execution: Tests in the XML file are executed sequentially, and each gets its own context.
         * Context Isolation: Each test context is independent and contains information specific to that test.
         * Suite Access: While the context is test-scoped, you can access suite-level information through context.getSuite().
         * Parameter Inheritance: Test-level parameters override suite-level parameters with the same name.
     */


}
