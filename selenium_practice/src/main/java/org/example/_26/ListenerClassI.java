package org.example._26;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClassI implements ITestListener {
/**
 * we are implementing the listener methods here
 * all overridden method should be public
 * these are not test methods but test ng calls them based on test execution and status
 * */

    public void onStart(ITestContext context) {
        /// before all tests
        System.out.println("on start");
    }
    public void onTestStart(ITestResult result) {
        /// before each test starts
        System.out.println("on test start");
    }
    public void onTestSuccess(ITestResult result) {
        /// when test is passes
        System.out.println("on test success");
    }
    public void onTestFailure(ITestResult result) {
        /// when test is failed
        System.out.println("on test failure");
    }
    public void onTestSkipped(ITestResult result) {
        /// when test is skipped
        System.out.println("on test skip");
    }
    public void onFinish(ITestContext context) {
        /// after all tests
        System.out.println("on finish");
    }
}
/***
 interface ITestListener extends ITestNGListener {
 default void onTestStart(ITestResult result) {
 }
 default void onTestSuccess(ITestResult result) {
 }
 default void onTestFailure(ITestResult result) {
 }
 default void onTestSkipped(ITestResult result) {
 }
 default void onTestFailedButWithinSuccessPercentage(ITestResult result) {
 }
 default void onTestFailedWithTimeout(ITestResult result) {
 onTestFailure(result);
 }
 default void onStart(ITestContext context) {
 }
 default void onFinish(ITestContext context) {
 }
 }
 */
