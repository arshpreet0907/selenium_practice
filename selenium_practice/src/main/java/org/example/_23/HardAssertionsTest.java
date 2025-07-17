package org.example._23;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertionsTest {
//    @Test
    void test(){

//        Assert.assertEquals(123,"abc");
//        Assert.assertEquals(123,123);

//        Assert.assertNotEquals("123",123); /// if values are not equal test will pass

//        Assert.assertTrue(true); /// pass
//        Assert.assertTrue(false); /// fails

//        Assert.assertFalse(true); /// fails
//        Assert.assertFalse(false); /// pass

//        Assert.fail(); /// fails
    }
    @Test
    void test1(){
        System.out.println(1);
        System.out.println(2);

        Assert.assertEquals(1,2); /// it will fail

        /// now these statements will not execute
        System.out.println(3);
        System.out.println(4);
    }
}
