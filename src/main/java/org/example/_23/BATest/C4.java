package org.example._23.BATest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class C4 {
    @Test
    void xzy_1(){
        System.out.println("this is xyz_1 test from C4");
    }
    @AfterTest
    void at(){
        System.out.println("this is after test_1");
    }
}
