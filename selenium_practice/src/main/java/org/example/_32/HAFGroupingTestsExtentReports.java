package org.example._32;
public class HAFGroupingTestsExtentReports{
    /**
         * 7) Grouping tests :
                * steps :
                        * 1) Add all test cases into specific group (sanity, regression, master etc.).
                        * 2) Also add BaseClass methods setup() & teardown() to all groups.
                        * 3) Create separate TestNG xml file(grouping.xml) to run groups and include groups which we want to execute.
                * all methods to be executed should be grouped including test,before and after,etc.
                * LoginTest : {Sanity, Master}, AccountRegistrationTest : {Regression, Master}, DataDrivenLoginTest : {DataDriven}
                * setup(Before Class) :  {Main}
                * tearDown(After Class) :  {Main}
                * here define tag can be used to make an inclusion group of classes (Union) and execute without giving another name to the Union of groups
                * to run methods that belong to two specified groups (Intersection) is not explicitly possible (Expressions might work but not in this case), can be done using combination of include and exclude tags

         * 8) Add Extent Reports to Project
                 * Steps :
                         * 1) Create ExtentReportUtility utility class under utilities package.
                         * 2) Add captureScreen() method in BaseClass
                         * 3) Add ExtentReportUtility (Listener class) entry in testng.xml file.
                         * 4) Make sure WebDriver is static in BaseClass, we refer same driver instance in ExtentReportUtility
                 * Also we will add the screenshot feature on failure in base class we add a method to take test method name as input take screenshot and return the path of the screenshot
                 * In ExtentReportUtility we add the methods : onStart, onTestSuccess, on TestFailure, onTestSkipped, onFinish. adding the status, info, groups of each test case along with other functionalities
                 * adding the listener xml files
                 * we need to make the BaseClass web driver static because one instance is created when execution starts from setup,
                 * but when new BaseClass() is called a driver is used in captureScreenshot method, so if web driver is nonstatic a new driver can be made
                 * and conflict can be there, to avoid it make the web driver static so it is shared across all the instances of BaseClass under one execution
                 * intentionally fail any test to check if screenshot gets attached to the report

         * 9) Run Failed Test Cases
                 * test-output->testng-failed.xml
                 * how :
                         * if in round 1 out of 100 test cases 90 got passes 10 failed, in round 2 we want ot execute only these 10 failed test cases
                         * capture the failed test cases
                         * when our xml file executes the test cases the failed test cases are stored automatically in a separate xml file in the test-output folder named testng-failed.xml
                         * it will cover the failed test cases of previous test round
                         * the entry will not be deleted till another fail happens till then it will not be removed even if the test got passed
                 * in our case test-output folder is not automatically generated so this xml file is also not generated
         */
      

        /**
         Error in XML file:
         The content of element type "test" must match "(method-selectors?,parameter*,groups?,packages?,classes?)".

         The error indicates that the XML elements inside <test> are not in the correct order according to the TestNG DTD.
         The required order is:

         method-selectors? (optional)
         parameter* (zero or more)
         groups? (optional)
         packages? (optional)
         classes? (optional)

         * */
}