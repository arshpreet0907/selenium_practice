package org.example._24;

import org.testng.annotations.Test;

public class GroupingPaymentTest {
    @Test(priority = 1,groups = {"sanity","regression","functional"})
    void paymentInRupees(){
        System.out.println("payment By rupees");
    }
    @Test(priority = 2,groups = {"sanity","regression","functional"})
    void paymentInDollars(){
        System.out.println("payment By Dollars");
    }
}
