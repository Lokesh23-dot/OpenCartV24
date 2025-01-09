package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	//constructor
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators:
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement MyaccountHeading;
	
	@FindBy(xpath="//a[@class='list-group-item' and normalize-space()='Logout']")
	WebElement logoutBtn;
	
	//Action Methods:
	public boolean IsMyAccountExist()
	{
		try {
		return MyaccountHeading.isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public void clkLogOut()
	{
		logoutBtn.click();
	}	
	
	
	
}
