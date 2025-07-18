package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002LoginTest extends BaseClass {

    /**
     * This is a test for valid login test
     * */
    @Test(groups = {"Sanity","Master"}) /// group is added in step 7
    void loginTest(){

        logger.info("***** Starting TC002LoginTest *****");
        try {
            /// clicking on login button
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            /// entering login details
            LoginPage lp = new LoginPage(driver);
            /// we should not hardcode data in our test case, here we will take data from properties file
            lp.setEmail(properties.getProperty("email"));
            lp.setPassword(properties.getProperty("password"));
//            lp.setPassword("hello123");  /// intentionally failing test to check if screenshot gets attached to the report
            lp.clickLogin();

            MyAccountPage map = new MyAccountPage(driver);
            boolean loginSuccess = map.isMyAccountExists();

            Assert.assertTrue(loginSuccess, "Login Failed");
            map.clickLogout();
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
        logger.info("***** Finished TC002LoginTest *****");

        }
}
