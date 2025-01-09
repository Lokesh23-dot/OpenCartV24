package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	
	@Test(groups={"Sanity","Master"})
	public void VerifyLogin()
	{
		//HomePage:
		HomePage hp= new HomePage(driver);
		hp.clkMyAccount();
		hp.clkLogin();
		
		//LoginPage:
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(prop.getProperty("email_id"));
		lp.setPassword(prop.getProperty("password"));
		lp.ClickLogin();
		
		//MyAccountPage:
		MyAccountPage map= new MyAccountPage(driver);
		boolean targetPage= map.IsMyAccountExist();
		Assert.assertTrue(targetPage);
	}
}
