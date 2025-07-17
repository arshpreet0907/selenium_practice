package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003DataDrivenLoginTest extends BaseClass {

    /**
     * Here we will use the data provider to get data for multiple tests
     * we will get both valid and invalid data
     * our test case will pass and fail based on following :
             * valid data :
                     * login success : pass -> logout
                     * login failed : fail
             * invalid data :
                     * login success : fail -> logout
                     * login failed : pass
     * here parallel test case run will not work since we are operating on the same driver without opening new browser
     * exception : java.lang.AssertionError: stale element reference: stale element not found
     * */
//int i=1; /// own, to count the number of test method execution
    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = {"DataDriven"})
    void dataDrivenLoginTest(String username, String password, String result){
        logger.info("***** Starting TC003DataDrivenLoginTest *****");
        try {
            /// clicking on login button
//            System.out.println(i++);  /// own, number of test executions
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            /// entering login details
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(username);
            lp.setPassword(password);
            lp.clickLogin();

            MyAccountPage map = new MyAccountPage(driver);
            boolean loginSuccess = map.isMyAccountExists();

            if (loginSuccess) {
                map.clickLogout();
                Assert.assertEquals(result,"Valid");
            }
            else
                Assert.assertEquals(result,"Invalid");
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
        logger.info("***** Finished TC003DataDrivenLoginTest *****");
    }
    /**
     * username	            password	res
     * dummy1221@gmail.com	test@123	Valid
     * ertmn@gmail.com	    Laxmii  	Invalid
     * popqwert@gmail.com	Lakshmi	    Invalid
     * abc123@gmail.com	    test@123	Valid
     * amlptre@gmail.com	xyz123	    Invalid
     *                      test@123    Invalid
     * */
}
