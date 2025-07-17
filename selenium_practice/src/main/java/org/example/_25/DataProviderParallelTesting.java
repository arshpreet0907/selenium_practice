package org.example._25;
/// what if we need to control thread count using data providers (data-provider-thread-count="2" and in data provider method make parallel=true),
/// also making separate test tags for each thread is too much when number of tests is higher (take data from data provider and pass xml parameter to data provider and perform split to get data)
public class DataProviderParallelTesting {
    /**
     * Parameterization
     * 1) @DataProvider : data driven testing
     * 2) using XMl file : for parallel testing
     * Both are separate
     * */
    static class DataProvidersDemo{
        /**
         * till now, we used loops to read data from Excel files
         * now we will do the same without loops
         * data provider method create data and pass it to some other method using @DataProvider annotation
         * here we have a test method which needs some testing data but doesn't wish to use any looping statements
         * repeat same test for different data
         * so we create a method that will prepare data and pass it test method
         * number of test execution is decided by the data
         * Advantage of data provider :
                 * 1) avoid using looping statements
         * for small data we can hard code the data in our method
         * but for large data volume we will have Excel files, this method will read this and keep a 2D array of data
         * in real scenario we use xml files and Excel sheets
         * here we will hard code the data to pass to test method
         * we need to use @DataProvider annotation to create such method
         * */
        /**
         * Here we have a before class setup method, after class teardown method and test testLogin method
         * first setup then perform tests and then teardown
         */
    }
    static class ParallelTestingXMLDemo{
        /**
         * This is done using XML file
         * we are making another class ParamTest to perform this
         * we are going to a web page and verifying logo is displayed, title and url
         * when hard coded we are doing for only Chrome browser at a time

         * we are going to use XML file to pass different browser names
         * before classes tag add another tag : parameter, along with 2 attributes name and value
         * for test to receive the parameter we need to add the @Parameter annotation to the test method
         * @Parameter({"browser"})
         * and add the argument in the method signature to receive the input from xml file
         <suite name="All Test Suite">
             <test name="ParamTest">
                 <parameter name="browser" value="chrome"/>
                 <classes>
                    <class name="org.example._25.ParamTest"/>
                 </classes>
             </test>
         </suite>
         * in Test Class we add a switch in set up method to change the browser based on the input from XML file
         * other test methods can also receive
         * now file cannot run on itself it requires xml file
         * */
        /**
         * if we do not wish to change parameter each time and want to execute each time
         * 1) sequential execution :create different tests for each parameter entry
         * 2) parallel execution : add an attribute to the suite : parallel="tests"
         *                          we can also specify this parallel at test level, if we have multiple classes at test level, then add the attribute in test tag :  parallel="classes"
         *                          similarly for methods specify methods in parallel attribute at suite or test level
         * thread count attribute : at suite level how many threads are created to run multiple tests in parallel
         *                          similarly at the test level, usually 2-5 is fine, by default it is 1. higher number of threads might reduce performance
         * if we have a lot of files making separate test cases is difficult then:
             * 1) make the test take data from data provider and make the data provider take data from xml file using parameterized data provider : String browsers = context.getCurrentXmlTest().getParameter("browserList"); then use split to get different browsers
             * 2) make the test take data from data provider and allow parallel from data provider and then specify the data provider thread count in xml at suite level
         * */
    }
}
