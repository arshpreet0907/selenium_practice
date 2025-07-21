package org.example._23;
import org.testng.annotations.*;
public class AllAnnotations {
    @AfterSuite
    void as(){
        System.out.println("after suite");
    }
    @BeforeSuite
    void bs(){
        System.out.println("before suite");
    }
    @BeforeTest
    void bt(){
        System.out.println("before test");
    }
    @AfterTest
    void at(){
        System.out.println("after test");
    }
    @BeforeClass
    void bc() {
        System.out.println("before class");
    }
    @AfterClass
    void ac(){
        System.out.println("after class");
    }
    @BeforeMethod
    void bm(){
        System.out.println("before method");
    }
    @AfterMethod
    void am(){
        System.out.println("after method");
    }

    @Test
    void tm1(){
        System.out.println("this is test method 1");
    }
    @Test
    void tm2(){
        System.out.println("this is test method 2");
    }

}
