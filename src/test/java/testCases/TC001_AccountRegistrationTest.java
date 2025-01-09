package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
		
	@Test(groups={"Regression","Master"})
	public void VerifyAccountRegistration()
	{
		
		logger.info("Started executing TC001_AccountRegistrationTest");
		HomePage hp = new HomePage(driver);
		hp.clkMyAccount();
		logger.info("clicked on MyAccount");
		
		hp.clkRegister();
		logger.info("clicked on Register");
		
		AccountRegisterationPage rp = new AccountRegisterationPage(driver);
		logger.info("Entering Customer Information");
		rp.setFirstname(randomString().toUpperCase());
		rp.setLastname(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		rp.setTelephone(randomNumber());
		
		String password= randomAlphaNumeric();
		System.out.println("The generated password is:"+password);
		
		rp.setPassword(password);
		rp.setConfirmPwd(password);
		rp.setPrivacypolicy();
		rp.clickContinue();
		
		logger.info("Validating Verifcation Message");
		String confMsg= rp.verifyConfirmationMsg();
		if(confMsg.equalsIgnoreCase("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);	
		}
		else
		{
			logger.error("Test Failed");
			Assert.fail();
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(msg, "Your Account Has Been Created!");
		logger.info("Finished executing TC001_AccountRegistrationTest");		
	}

}
