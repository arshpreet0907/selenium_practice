package org.example._23;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionsTest {
    @Test
    void test(){
        System.out.println(1);
        System.out.println(2);

        SoftAssert softAssert= new SoftAssert();

        /// here we need to make the object of soft assert class and use the same methods as Assert class
//        softAssert.assertEquals(1,2);
        softAssert.assertEquals(1,1);


        System.out.println(3);
        System.out.println(4);

        softAssert.assertAll(); /// this method is mandatory
        /// this method enables the hard assertion behavior and the statements written below it will not execute
        /// without this method the soft assertion will always pass
        /// soft assertion object initialisation should happen in the method itself only
        /// Ensures thread safety (critical for parallel execution).
        /// Prevents assertion state leakage between test methods.
        /// Guarantees a fresh SoftAssert instance per test.
        System.out.println(5);

        /**
         private ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

         @BeforeMethod
         public void setup() {
            softAssert.set(new SoftAssert()); // Thread-safe initialization
         }

         @Test
         public void test1() {
            softAssert.get().assertEquals(actual, expected);
         }

         @AfterMethod
         public void tearDown() {
            softAssert.get().assertAll(); // Verify all assertions
            softAssert.remove(); // Cleanup
         }
         * When multiple assertions are reused across helper methods.
         * Requires careful thread management (e.g., with ThreadLocal).
         */
    }
}
