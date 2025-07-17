package org.example._23;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertionsD {
//    @Test
    void testTitle(){
        String exp_title="Opencart";

        String act_title="Openshop";

        /// normally we do it like
        if (exp_title.equals(act_title))
            System.out.println("test passes");
        else
            System.out.println("test failed");
    }
    @Test
    void testTitleAssert(){
        String exp_title="Opencart";
        String act_title="Opencart";

        Assert.assertEquals(exp_title,act_title);
        /// if both same then test method passes otherwise the test case fails and throws AssertionError
    }
    @Test
    void testTitleAssertIfElse(){
        String exp_title="Opencart";
        String act_title="Openshop";

        if (exp_title.equals(act_title)) {
            System.out.println("test passes");
            Assert.assertTrue(true);
        }
        else {
            System.out.println("test failed");
            Assert.assertTrue(false);

        }

        /// if both same then test method passes otherwise the test case fails and throws AssertionError
    }

}
