package org.example._24;


public class DependencyMethodsGroupingTests {
  
    static class DependencyMethodsDemo{
        /**
         * Dependency Methods :
         * for the following steps :
                 * open app
                 * login
                 * search
                 * adv search
                 * logout
         * testng will execute all the tests
         * but if open app fails then it is confirmed that others will fail too
         * it doesn't properly judge the tests
         * it means the other steps are dependent on others
         * login is dependent on open app
         * and rest depend on login
         * */
        /**
         * if one method fails all dependent methods should not execute
         * first identify the dependent methods and use the following attribute
         * dependsOnMethods={""} : we need to provide the array of strings here
         * the methods on which a method is dependent should be in the same class
         * */
    }
    static class GroupingTestsDemo{
        /**
         * Creation of Groups and executing the tests according to the groups
         * Grouping : categorizing the tests in different categories (Test Methods not classes)
         * Categories :
                 * Sanity (Basic Functionality) :if fails cannot move further
                 * Regression : need to execute in every cycle of testing
                 * Functional : all the tests
         * this task starts before automation and is possible only through XML file
         * first identify which methods come under which group
         * e.g. : login methods are sanity, signup methods are regression and payment methods are sanity and regression i.e. functional
         * Group name can be anything we provide it as String Array in the @Test annotation attribute
         * */
        /**
         * in XML file before the classes tag we need to add groups tag inside it add a run tag inside it add include tag specifying the name attribute
         <?xml version="1.0" encoding="UTF-8"?>
         <!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
         <suite name="Grouping Suite">
             <test name="Grouping Test">
                 <groups>
                     <run>
                        <include name="sanity"/>
                     </run>
                </groups>
                 <classes>
                     <class name="org.example._24.GroupingLoginTests"/>
                     <class name="org.example._24.GroupingSignupTest"/>
                     <class name="org.example._24.GroupingPaymentTest"/>
                 </classes>
             </test>
         </suite>
         * In this case 5 methods are running : every method with sanity group
         * to run only sanity and not regression use exclude tag : 3 methods
                * <exclude name="regression"/>
         * to run methods with both sanity and regression : we need to give separate category for that group
         * apart from the two groups add another group name to the methods
         * */
        /**
         * Complex Logic (AND + OR + NOT)
         * Combine includes/excludes and meta-groups :
         <define name="core">
         <include name="sanity"/>
         <include name="regression"/>
         </define>

         <run>
         <include name="core"/>         <!-- (sanity OR regression) -->
         <exclude name="broken"/>       <!-- NOT broken -->
         </run>
         * */
        /**
         * Use dependsOnGroups for sequencing (not direct filtering):
         java
         @Test(groups = "sanity")
         public void test1() { ... }

         @Test(groups = "regression", dependsOnGroups = {"sanity"})
         public void test2() { ... }
         * */
    }
}
