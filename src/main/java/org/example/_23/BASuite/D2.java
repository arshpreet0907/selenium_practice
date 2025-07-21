package org.example._23.BASuite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class D2 {
    @Test
    void lmn(){
        System.out.println("this is coming from D2");
    }
    @AfterSuite
    void as(){
        System.out.println("D2 after suite");
    }
    @BeforeSuite
    void bs(){
        System.out.println("D2 before suite");
    }
}
