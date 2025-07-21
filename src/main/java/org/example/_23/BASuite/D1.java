package org.example._23.BASuite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class D1 {
    @Test
    void pqr(){

        System.out.println("this is coming from D1");
    }
    @AfterSuite
    void as(){
        System.out.println("D1 after suite");
    }
    @BeforeSuite
    void z(){
        System.out.println("D1 before suite");
    }
    @BeforeSuite
    void zz(){
        System.out.println("D1 before suite 1");
    }
}
