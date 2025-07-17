package org.example._28;

public class HybridAutomationFramework {
    /**
     * We will understand the Automation Framework
     * what is framework, its types, steps to follow while designing a framework,
     * phases, how we choose test cases for automation, what criteria to follow
     * */
    /**
     * What is Automation Framework ?
     * in a framework we organize automation project files/folders in structured manner
     * files : test cases, page object classes, properties files, utility files, test data files, report files, xml files and screenshots.
     * we don't put everything in one folder, we divide them for better maintenance and reusability, similar files in same folder
     * */
    /**
     * Why we need framework (Objectives) :
     * 1) Re-usability : avoid duplication, test cases should be independent
     * 2) Maintainability : if wish to add/modify test cases, other people should also be able to do so for long time
     * 3) Readability : everyone should be able to understand the files and test cases, do not complicate thing unnecessarily
     * */
    /**
     * Types :
     * 1) Built-in Framework (libraries to be installed and used) : already available e.g. testng, junit, cucumber, robot, etc.
     * 2) Custom (user-defined) : along with built-in framework we add more on top of it, to add more functionality based on our own requirements
                                  modular framework, data driven, keywork driven, hybrid driven (combinations, flexibility to have third party tool), etc.
                                  many things remain common in most projects
     * */
    /**
     * Phases/Stages :
     * 1) Analyzing the Application Under Test (AUT) :
             * 1) how many pages (number of page object classes)
             * 2) what type of elements (built-in or customized) and how many
             * 3) what to automate and what we cannot automate

     * 2) Choose test cases for Automation :
             * 1) not all can be automated,
             * 2) some will be done manually tested,
             * 3) initial builds of manual testing then automation
     * 100% automation :
             * 1) what percentage can be automated : is 100% automated,
             * 2) need to clarify how many can be automated and what cannot be,
             * 3) if we automate all the automatable test cases it is still called 100% automation
     * based on priority we need to choose the test cases
             * 1) P1 priority : sanity (most)
             * 2) P2 priority : data driven test cases/re-tests
             * 3) P3 priority : Regression test cases
             * 4) P4 priority : any other test cases
     * First 3 things we must automate, if rest can be automated then do it

     * 3) Design and Development of Framework :
             * 1) folder structure which everyone should follow
             * 2) creating the different files
             * 3) designed by seniors

     * 4) Execution :
             * 1) executing the just written test case and execution of entire test cases based on requirement is different
             * 2) execution takes place in levels
             * 3) 2 types -> local (within same device or ide) and remote execution (remote repositories)
             * 4) through selenium grid we can achieve this, and jenkins as well

     * 5) Maintenance :
             * 1) local and remote repositories then push to global repository
             * 2) then CI part through jenkins`
     * */
    static class DemoWebApp{
        /**
         * We will automate the ecommerce web application here
         * Domain specific experience is very important and specific to the domain like banking, health, ecommerce, etc.
         * Overall features of specific domain are usually same : similar service and actions
         * */
        /**
         * Ecommerce :
         * frontend operations : accessible to user/client
         * register, login, search, choose product, add product into cart, put products into wishlist, order the product, do the payment, after ordering track the product status, after delivery comment and review the product, etc.
         * backend operations : only administration people have the access
         * maintain the products information, all registered customers information, ordered product information, orders delivered, orders on the way, orders not still delivered, store information, inventory, reports, frequently bought products, not much sold products, shipping information, etc.
         * Different applications for frontend and backend but integrated together having common database
         * Automation here : frontend part
         * */
        /**
         * What we are going to automate here :
         * OpenCart : https://tutorialsninja.com/demo/
         * 1) register test : frontend creating new customer -> check if admin panel has same data -> check if same data present in database or not
         *                    this way we do end-to-end testing, all three must be properly synced
         * we will create a number of test case scenarios and for each scenario we will make a number test cases, out of which some cannot be automated as well
         * we will create the framework using page object model
         * */
    }
}
