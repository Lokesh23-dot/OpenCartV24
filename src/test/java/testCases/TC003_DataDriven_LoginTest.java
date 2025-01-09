package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.DataProviders;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC003_DataDriven_LoginTest extends BaseClass {

	//dataProviderClass=DataProviders.class: we are using this because dataProvider method is in another class. If the data provider method is inside this class we dont need to use this.
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")
	public void VerifyLogin( String email, String psword, String exp_res)
	{
		//HomePage:
		HomePage hp= new HomePage(driver);
		hp.clkMyAccount();
		hp.clkLogin();
		
		//LoginPage:
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(psword);
		lp.ClickLogin();
		
		//MyAccountPage:
		MyAccountPage map= new MyAccountPage(driver);
		boolean targetPage= map.IsMyAccountExist();
		if(exp_res.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				map.clkLogOut();
				Assert.assertTrue(true);
			}
		
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp_res.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				map.clkLogOut();
				Assert.assertTrue(false);
			}
			
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		
	}
}
