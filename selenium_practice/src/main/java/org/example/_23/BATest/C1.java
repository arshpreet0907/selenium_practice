package org.example._23.BATest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class C1 {
    @Test
    void abc(){
        System.out.println("this is abc test from C1");
    }
    @BeforeTest
    void z_bt(){
        System.out.println("this is before test");
    }
}
