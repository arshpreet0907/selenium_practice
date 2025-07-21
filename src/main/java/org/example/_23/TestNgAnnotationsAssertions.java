package org.example._23;

import org.testng.annotations.Test;

import java.util.Scanner;

@Test
public class TestNgAnnotationsAssertions {
   static class AnnotationsDemo {
       /**
        * Annotations: decide the order of execution of methods
        * 1) @Test : any method is a test method when specified with the @Test annotation
        * 2) @BeforeMethod : executed before every test method, serves as entry point
        * 3) @AfterMethod : executed after every test method, serves as exit point
        * 4) @BeforeClass : executed only once before all the test methods of a class
        * 5) @AfterClass : executed only once after all the test methods of a class
        * 6) @BeforeTest : executed once right before test
        * 7) @AfterTest : executed once right after test
        * 8) @BeforeSuite : executed once right before test
        * 9) @AfterSuite : executed once right after suite
        */
       /**
        * A) Before After Method :
        * e.g. login is a prerequisite then perform validations and then logout
        * Steps :
        * Login -> @BeforeMethod
        * Search -> @Test
        * Logout -> @AfterMethod
        * Login -> @BeforeMethod
        * Adv Search -> @Test
        * Logout -> @AfterMethod
        * Some steps are repeated and some are prerequisites, entry exit points.
        * login is repeated before each test, according to the specific case we need to specify
        * logout is repeated after each test
        * no validations on login and logout
        * here only search and advance search are the only test : in tree structure only these are
        * visible in results, but all of these will be performed, these are used as entry exit points
        * It is not mandatory to use both we can use as per need either one,both or none
        * */
       /**
        * B) Before After Class :
        * e.g. login is a prerequisite then perform validations and then logout
        * Steps :
        * Login -> @BeforeClass
        * Search -> @Test
        * Adv Search -> @Test
        * Logout -> @AfterClass
        * search and advance search are done together, login and logout executed only once
        * executed only once before and after all the test methods
        * they do not participate in results but execute completely
        * It is not mandatory to use both we can use as per need either one,both or none
        * */
       /**
        * Test and Suite Definition in XML File :
        <suite name="mySuite">
           <test name="myTest">
              <classes>
                 <class name="org.example._22FirstTestCase"/>
                 <class name="org.example._22OrangeHRMTest "/>
              </classes>
           </test>
        </suite>
        * according to xml file a test is a collection of classes
        * here we have only 1 test, there could be multiple :
        <suite name="mySuite">
           <test name="myTest">
              <classes>
                 <class name="org.example._22FirstTestCase"/>
              </classes>
           </test>

           <test name="myTest1">
              <classes>
                 <class name="org.example._22OrangeHRMTest "/>
              </classes>
           </test>
        </suite>
        * */
       /**
        * C) Before After Test :
        * before test is executed before execution of every test and after is also accordingly as per xml level
        * we made two classes C1 C2 with before test in C1 and after test in C2
        * we can place both before and after test in either C1 or C2, or we can divide them as well
        * at run time annotations decide the execution order
        * we run these test using the xml file in the following scenarios:
        * 1) both classes in same test : methods execution order : before -> C1 -> C2 -> after ; C1 is written earlier in xml
        *    here only 1 test
        * 2) both classes are places in separate tests : still the execution order is same as 1st scenario
        *    if a class has a before test method and placed inside a test in xml file, it will execute always before the test starts
        *    similarly for after test as well
        *    if multiple classes have the before or after test methods in same test,
        *    then alphabetical order of class name is followed for execution order
        */
       /**
        * D) Before After Suite :
        * these are executed at suite level, thus only executed once before suite and once after suite
        * if same file has multiple such methods then alphabetical order is followed
        * but if different classes have before suite then alphabetical order of class name is followed for execution order
        * */
   }

   @Test
   static class AssertionsDemo{
       /**
        * There is a convention in test ng that the method names should always start with "test"
        * Assertions help us to validation the output of code
        * it can be done with conditional statements as well but there is an issue with them :
        * even if the values are not same and test failed is printed test ng doesn't see it as test failed as shows test passed
        * test method is passes in that case
        * only the result of test method is updated in report
        * refer to AssertionD class for test case
        * */
       /**
        * to solve this problem we use Assert class and it's methods to perform testing
        * Assert.assertEquals(); : it takes two parameters if same then test case passes else it fails
        * We should always use Assertions only
        * */
       /**
        * Kinds of Assertions :
            * 1) Hard Assertions :
                    * whenever we use Assert class static method
                    * there is a problem : whenever the test fails the statements below this failed assertion statement are not executed
                    * to avoid this assertion should always be the last statement in method
                    * most of the time we use this type of approach
            * 2) Soft Assertions :
                    * it also has the same methods but solves the hard assertion problem
                    * for this we need to make object of class SoftAssert and use the same methods as the Assert class
                    * difference is that Assert class has static methods while SoftAssert class need object to execute
                    * normally if soft_assert_obj.assertEquals(1,2); is called the test passes and all statements executes
                    * to make the tests fail on such conditions we need a mandatory method : soft_assert_obj.assertAll(); as the last statement of method
                    * because if it is called before the other statements they will not execute
                    * this method enables the hard assertion behavior
                    * soft assertion object initialisation should happen in the method itself only
        * */


   }
}
