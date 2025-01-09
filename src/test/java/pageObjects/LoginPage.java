package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	//constructor
	public LoginPage(WebDriver driver)
	{ 
		super(driver);
	}
	
	//Locators:
	@FindBy(xpath="//input[@id='input-email']")
	WebElement Email_id;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement LoginBtn;
	
	// Action Methods:
	
	public void setEmail(String email)
	{
		Email_id.sendKeys(email);
	}
	
	public void setPassword(String psword)
	{
		password.sendKeys(psword);
	}
	
	public void ClickLogin()
	{
		LoginBtn.click();
	}
	
	

}
