package org.example._23.BATest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class C3 {
    @Test
    void abc_1(){
        System.out.println("this is abc_1 test from C3");
    }
    @BeforeTest
    void a_bt(){
        System.out.println("this is before test 1");
    }
    @BeforeTest
    void a_abt(){
        System.out.println("this is before test 1.1");
    }
    @AfterTest
    void c3at(){
        System.out.println("this is after test c3");
    }
}
