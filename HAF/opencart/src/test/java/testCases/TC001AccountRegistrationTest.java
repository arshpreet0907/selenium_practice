package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

/// here class name is testcase id + test case name + "Test"
/// here we are considering each class as a test case
public class TC001AccountRegistrationTest extends BaseClass {

    /// the methods we created here which can be commonly used by other classes as well we will create another class and put it there
    /// e.g. setup, tearDown, randomString, randomNumber, randomAlphaNumeric
    /// this new class is BaseClass, and just extend this base class where ever we need to use these methods


    /**
     * here we are getting the driver configured from base class, but we should not also get the url from base class as it can vary for different test classes
     * to solve this we can do the following :
     * 1) make another before class method in the child class which will execute after the before class method of BaseClass, so configure the driver in base class and provide the url in child class
     * 2) make an abstract method getURL in BaseClass to return the url and use this to provide the url to driver in BaseClass, in child class override this method for particular url
     * */

    /// 1) make another before class
//    @BeforeClass
//    void setURL(){
//        driver.get("https://tutorialsninja.com/demo/");
//    }
    /// 2) override the getURL method
//    @Override
//    public String getURL(){
//        //        return "https://tutorialsninja.com/demo/";
//        return properties.getProperty("tutorialNinjaURL");
//    }
//

    @Test(groups = {"Regression","Master"})
    void verifyAccountRegistration(){
        /// adding logs : we can add logs for each event

        try {
            logger.info("****Starting TC001AccountRegistrationTest*****");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on my account link");
            hp.clickRegister();
            logger.info("Clicked on register link");

            AccountRegistrationPage rp = new AccountRegistrationPage(driver);
            /// if we hard code the data in here then if we run again it will fail, email already registered
            /// so we need to produce the data either randomly or get it from data provider
            /// two types of data : static data and dynamic data
            /// create random data using user defined method
            /// using such methods for other data

            logger.info("adding customer details");
            rp.setFirstLast(randomString().toUpperCase());
            rp.setLastName(randomString().toUpperCase());
            rp.setEmail(randomString() + "@gmail.com");
            rp.setTelephone(randomNumber());

            String password = randomAlphaNumeric();
            rp.setPassword(password);
            rp.setConfirmPassword(password);

            rp.setNewsLetterSubscription(true);
            rp.setReadPolicy();
            rp.clickContinue();

            logger.info("validating expected message");
            String confirmation_msg = rp.getConfirmationMessage();

            if (confirmation_msg.equals("Your Account Has Been Created!")){
                logger.debug("Debug logs without error...");
                Assert.assertTrue(true);
            }
            else {
                logger.error("exception occur : Test Failed"); /// here it is advised to provide the constant string
                logger.debug("Debug logs with error...");
                Assert.fail("message not same");
            }
            logger.info("****Finished TC001AccountRegistrationTest*****");
        }catch (Exception e){
            Assert.fail();
        }
        }

}
