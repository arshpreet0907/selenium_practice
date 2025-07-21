package org.example._23.BATest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class C2 {
    @Test
    void xzy(){
        System.out.println("this is xyz test from C2");
    }
    @AfterTest
    void at_1(){
        System.out.println("this is after test");
    }
}
