package org.example._22;

import org.testng.annotations.Test;

import java.util.Map;
import java.util.Scanner;
@Test
public class TestNGIntegrationSetup {
    /**
     * Test Ng : Test new generation
     * it is used for unit testing
     * it is built for java so not available for other languages
     * java based unit testing tool
     * testing for code level : method outputs
     * it can be used for:
             * 1) writing the test cases
             * 2) organising the test cases
             * 3) executing the test cases
             * 4) generating the report
             * 5) parallel test
     * advantages :
             * 1) Test cases and test suites
             * 2) Grouping of test cases
             * 3) Prioritize
             * 4) Parameterization
             * 5) Parallel Testing
             * 6) Reports
     */

    /**
     * Selenium web driver is limited to interacting with the web elements and validating
     * it is not in the form of test cases
     * e.g. want to execute test cases based on priority, order, group of cases, reports, parallel, multiple browser
     * to achieve other features we need to integrate other tools as well
     */

    /**
     * TestNG configuration :
     * 1) as a plugin then adding it to project
     * 2) add dependency in pom
     * we added the dependency in pom, in setting-> plugin we made sure testng is in installed (mandatory step),
     * in project structure-> libraries we choose from maven and added org:testng:testng:7.11.0 checked all boxes and added
     */

    /**
     * Here we will run code without main method in the class
     * instead we use Test annotations, e.g. : @Test
     */

    /**
     * when a class is made in this file with name FirstTestCase, but the public class is different the
     * run option is not available but when the same class is made in different file, using public keyword
     * the run option becomes visible
     */

    /**
     * running a test case will not execute like a java program it has a different output way
     * e.g. :
      SLF4J(W): No SLF4J providers were found.
      SLF4J(W): Defaulting to no-operation (NOP) logger implementation
      SLF4J(W): See https://www.slf4j.org/codes.html#noProviders for further details.
      login method
      logout method
      opening application

      ===============================================
      Default Suite
      Total tests run: 3, Passes: 3, Failures: 0, Skips: 0
      ===============================================


      Process finished with exit code 0
     */

    /**
     * Javadoc comments are meant to document specific code elements like classes, methods, fields, or
     * constructors. When IntelliJ encounters a Javadoc comment that doesn't precede any of these
     * elements, it considers it "dangling" because it serves no documentation purpose.
     * */

    /**
     * We can execute the test file by running the java class containing the test annotations
     * but if wih to run group of test together we can use XML file
     * testng xml file : various features of testng is executed using xml file

     * Group of test is called test suite
     * */
    /**
     * for report generation create testing.xml file from the context menu which automatically runs the testing file as specified
     * upon running it automatically generates the test-output folder in root directory
     * in this test-output folder the test report is generated automatically when testing.xml file is executed
     * along with the test report there are various files generated in the test-output folder
     * two reports are main : emailable-report.html and index.html

     * In our case due to some issue create testing.xml option is not in context menu so we created it manually
     * specified the class to run and upon running no automatic test-output folder was generated, also no report was created
     * for creating the testing report we did the following :
     * for report generation we can create configuration
     * in edit configuration create new configuration of TestNG where we select the class/module/package name to run test in
     * for the output directory set the test-output folder or anything else as specified
     * for report generation edit the listener add : org.testng.reporters.EmailableReporter2
     * press apply then ok to confirm
     * upon running the report is generated the output folder as specified
     * the name of report file is always same (emailable-report.html) so use different directory paths to segregate
     * in our case only emailable-report.html is generated
     * */

    /**
     * Structure of testing.xml file :
     * following are the common statements for all files :
         * <?xml version="1.0" encoding="UTF-8"?>
         * <!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
     * suite tag is the root parent tag : name attribute (we can change)
     * test tag :
         * thread-count attribute is used for parallel testing
         * name tag
     * classes tag : contains the number of class tags showing how many classes to test
     * class tag : name attribute is used to specify the class to run in the test

     * we added the testng xml plugin from marketplace to generate the testing.xml file automatically from content menu
     * we added more report listeners to get more output files
     * */
    /**
     * xml file is called suite here
     * normally the following is the flow :
     * Test Suite --> test cases --> test steps
     * xml file --> test classes --> test methods
     * */
    /**
     * Execute xml file through testng to generate the reports automatically
     * group test running is also possible as we have seen

     * when auto xml generation is possible why make it manually :
            * only basic tags are added automatically to add more tags in the file we need to add them manually
     * tags are common for all the testing.xml file
      * */
    /**
     * what we did till now using xml file :
     * 1) executed multiple tests as 1 suite
     * 2) we can generate testing reports
     * */

    public void say(){
        System.out.println("say test");
    }
    public  void mainn() {
        double largeDouble = 1.0e-308;
        System.out.println(largeDouble);
        float a=1.1111111111f;
        double b=1.123456789101112;
        double c=1.1234f;
//        System.out.println(c);
//        find_pi();
        System.out.println(Math.PI);
    }
    static void find_pi(){
        double deno=1;
        double sign=1;
        double pi=0;
        Scanner s= new Scanner(System.in);
        System.out.print("enter n : ");
        long n;
        n=s.nextLong();
        System.out.println("calculating pi for n : "+n);
        for (long i=0;i<n;i++){
                pi+=sign*(1/deno);
                sign*=-1;
                deno+=2;
        }
        System.out.println(4*pi);
    }
}
