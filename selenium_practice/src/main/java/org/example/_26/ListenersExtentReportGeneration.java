package org.example._26;

public class ListenersExtentReportGeneration {
    static class ListenersDemo{
        /**
         * 3 status of test cases: pass, fail, skip
         * based on these results we wish to perform some actions
         * these actions are called post actions : e.g. update report, take screenshot, etc.
         * for this we use Listeners

         * we make a Listener class : no test method is here implement methods (default methods) of ITestListener interface

         * steps :
         * 1) make the test case class
         * 2) make the listener class
         * 3) make the xml file add the test and listener classes here

         * there are various types of listeners available, but we are going to look at the following
         * ways to implement listener class :
         * 1) implement ITestListener interface : can implement according to our requirement
         * 2) extend TestListenerAdapter class : can get the already implemented methods, need to override


         * running xml file without adding listener class nothing related to listener class happens
         * ways to add listener class :
         * 1) xml file :
             * to add this we need to add another xml tag listeners at suite level and inside it add listener tag, inside it, we add our listener class
             <suite  name="Suite">
                 <listeners>
                    <listener class-name="org.example._26.ListenerClassI"/>
                 </listeners>
                 <test  name="ListenTest" >
                     <classes>
                        <class name="org.example._26.ListenerT"/>
                     </classes>
                 </test>
             </suite>
             * for all classes we have same listeners, there are  utility files
         * 2) add Listeners annotation at the test class level, add the listener class there
                @Listeners({org.example._26.ListenerClassI.class}) /// here it requires the array of class type
                public class ListenerT {}
         * we prefer using the xml file : for multiple test cases we usually have same listener class
                 * so using the listener annotation we need to add them to each class
                 * using xml it can be written once
         * the same listener class it used for multiple test classes is because the post actions are same for all test case
         * post actions : report generation (log everything in one report), update the result in report, etc.
         * */

    }
    static class ExtentReportGeneration{
        /**
         * Generate custom Extent report (not a part of test ng) using listener
         * it is a utility file
         * it is a third party report, we need to add third party dependency in pom.xml
         * */
        /**
         * classes to be used (many more available) :
         * 1) ExtentSparkReporter : UI of report (look and feel)
         * 2) ExtentReports : populate common info on the report (tester name, browser name, operating system name, project name, module name, environment details)
         * 3) ExtentTest : creating the test entries and update the status of the test methods, and update the test entries in report

         * the way to create and update the extent report can be referred in the java implementation in ExtentReportWithListener class
         * make the object for ExtentSparkReporter class : add the title, name, theme, etc. for the report using .config().setDocumentTitle()/setReportName()/setTheme(), etc.
         * make the object for ExtentReports class : add the above reporter object to it(attachReporter()), add the system information using .setSystemInfo()
         * make the object for ExtentTest using extent_reports.createTest(result.getName()), then add test logs using .log(Status,"msg") for pass, fail, skip separately
         * close the extent_reports using .flush()
         * */

    }
}
