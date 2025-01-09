package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators:
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement MyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement Register;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement Login;
	
	//Action Methods:
	public void clkMyAccount()
	{
		MyAccount.click();
	}
	
	public void clkRegister()
	{
		Register.click();
	}
	
	public void clkLogin()
	{
		Login.click();
	}

}
