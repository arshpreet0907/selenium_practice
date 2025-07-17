package org.example._22;

import org.testng.annotations.Test;
@Test
public class FirstTestCase {
    /**
     * Steps to perform
     * 1) open app
     * 2) login
     * 3) logout
     * for every step we create a method
     * based on complexity of the steps we make separate methods
     */
    @Test(priority = 1)
    void open_app(){
        System.out.println("opening application");
    }
    /**
     * Here the order of execution is in alphabetical order not the way we wish them to be
     * to resolve this we need to add priority to the methods
     * to add priority we simply add @Test(priority = 1) : here lower number is of higher priority
     * using this we can control the order of execution
     * order of methods in the class from top to down doesn't matter

     * required data type for the priority is int (both +ve and -ve), floating number will not work
     * priority number can be anything, need not tobe consecutive, which ever is lower will execute first

     * if priority is not specified then the default priority is 0 (zero)
     * if same priority is given to same then again alphabetical order is considered

     * if @Test annotation is removed that method is not considered a test and will not execute as a part of test file
     * if @Test is provided to class and not to any method then test file executes and every public nonstatic method gets executed as a test case
     * return type doesn't matter but parameter should be properly handled
     * there should be at least one of the test annotations
     * */
    @Test(priority = 2)
    void login(){

        System.out.println("login method");
    }
    /**
     * 1) TestNG execute test methods based on alphabetical order.
     * 2) @Test(priority=num) controls the order of execution.
     * 3) Once you provide priority to the test methods, then order of methods is not considered.
     * 4) priorities can be random numbers( no need to have consecutive numbers)
     * 5) If you don't provide priority then default value is Zero (0).
     * 6) If the priorities are same then again execute methods in alphabetical order.
     * 7) Negative values are allowed in priority.
     * 8) TestNG execute test methods only if they are having @Test annotation.
     * */
    @Test(priority = 3)
    void logout(){
        System.out.println("logout method");
    }


}
