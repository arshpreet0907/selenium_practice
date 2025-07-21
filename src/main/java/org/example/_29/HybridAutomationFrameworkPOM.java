package org.example._29;

public class HybridAutomationFrameworkPOM {
    /**
     * Here we will design the framework
     * Hybrid Automation Framework
     * Java, Selenium, TextNG, Maven and Page Object Model :
       [
        [Page Objects] -> [Test Cases] -> [TestNG.xml] -> [pom.xml]
                             ^|                 |           |
       [[Utilities] [Test Data] [Resources]] [[Reports]   [Logs]]
     ] ->
     [Git]  ->  [[GitHub]  ->  [Jenkins]]
     */
    /**
     * Development :
             * Page Objects : Base Page, Home Page, Reg Page, Login Page, Search Page, etc.
             * Test Cases : Base Class, Login Test, Login DDT, Account Reg Test, Search Product Test, Add to cart, etc.
             * Utilities : XLUtility, ExtentReportUtility, DataProviders
             * Test Data : Excel Files
             * Resources : Properties, Log4j2
     * Execution :
             * TestNG.xml : Mastersuite.xml, Grouping.xml, multiplebrower.xml
             * pom.xml : Dependencies, plugins
             * Reports : TestNG Reports, Extent Reports
             * Logs : Info, Debug, Warn, etc.
     * CI :
             * Git
             * GitHub
             * Jenkins
     */

    /**
     * For This we need a new Maven Project
     * From now on refer to HAF project
     * */



}
