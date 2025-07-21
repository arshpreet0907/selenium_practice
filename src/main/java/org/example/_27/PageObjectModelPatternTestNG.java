package org.example._27;

public class PageObjectModelPatternTestNG {
    /**
     * Page Object Model is not specific to TestNG
     * it is a design pattern not a framework,
     * place test cases, web locators in separate classes
     * defines how we maintain the locators
     * test methods -> validations + actions
     * Disadvantages without page object model :
             * 1) Duplication of elements/locators : multiple test cases can refer to same elements, then they define locators separately, no reusability
             * 2) Update : if the attribute or x path gets changed we need to change it everywhere
     * page object model :
             * 1) Divide locators and test methods in two different classes
             * 2) Create Page Object Classes : dedicated classes for different web pages containing all the required web elements also define the action methods on the web elements in each class
             * 3) In test methods get these elements from page object class and the action methods to perform actions on elements, test methods only contain the validations
     * Advantages :
             * 1) Increased reusability of locators and actions on elements
             * 2) Any change only need to be updated in individual web page class only, single change will reflect everywhere
     * */
    /**
     * We can create Page Object Class in following ways :
     * 1) without using PageFactory
     * 2) using PageFactory
     * it is a predefined class in selenium

     * Flow -> xml - test class - page object class - application
     * Steps :
     * 1) create login page object class
     * 2) create the test cases (create and setup webdriver driver here along with url and pass it to the page class)
     * 3) create the xml file and execute it
     * */


}
