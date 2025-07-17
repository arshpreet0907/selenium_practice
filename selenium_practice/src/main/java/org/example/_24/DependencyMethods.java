package org.example._24;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyMethods {
    @Test(priority = 1)
    void open_app(){
        Assert.assertTrue(true);
    }
    @Test(priority = 2,dependsOnMethods={"open_app"}) /// if open app fails it will be skipped
    void login(){
        Assert.assertTrue(false);
    }
    @Test(priority = 3,dependsOnMethods={"open_app","login"}) /// we can pass multiple method names as well
    void search(){
        Assert.assertTrue(true);
    }

    @Test(priority = 4,dependsOnMethods={"login"})
    void adv_search(){
        Assert.assertTrue(true);
    }

    @Test(priority = 5,dependsOnMethods={"login"})
    void logout(){
        Assert.assertTrue(true);
    }


}
