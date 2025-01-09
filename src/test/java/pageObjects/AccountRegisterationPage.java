package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage {
	
	public AccountRegisterationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators:
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstnameInpBox;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastnameInpBox;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement emailInpBox;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telephoneInpBox;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement passwordInpBox;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement crfmpwdInpBox;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicyBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueBtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement successMsg;
	
	
	//Action Methods:
	public void setFirstname(String firstname)
	{
		firstnameInpBox.sendKeys(firstname);	
	}
	
	public void setLastname(String lastname)
	{
		lastnameInpBox.sendKeys(lastname);	
	}
	
	public void setEmail(String email)
	{
		emailInpBox.sendKeys(email);	
	}
	
	public void setTelephone(String telephone)
	{
		telephoneInpBox.sendKeys(telephone);	
	}
	
	public void setPassword(String pwd)
	{
		passwordInpBox.sendKeys(pwd);	
	}
	
	public void setConfirmPwd(String pwd)
	{
		crfmpwdInpBox.sendKeys(pwd);	
	}
	
	public void setPrivacypolicy()
	{
		chkPolicyBox.click();
	}
	
	public void clickContinue()
	{
		continueBtn.click();
	}
	
	public String verifyConfirmationMsg()
	{
		try {
		return successMsg.getText();
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
}
