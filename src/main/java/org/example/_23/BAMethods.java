package org.example._23;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BAMethods {
    @BeforeMethod
    void login(){
        System.out.println("login");
    }
    @AfterMethod
    void logout(){
        System.out.println("logout");
    }
    @Test(priority = 1)
    void search(){
        System.out.println("search");
    }
    @Test(priority = 2)
    void advance_search(){
        System.out.println("advance search");
    }
}
