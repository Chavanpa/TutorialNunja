package com.tutorialninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	
	WebDriver driver;
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement clickMyaccount;
	
	@FindBy(id="input-email")
	private WebElement emailIdField;
	
	@FindBy(id="input-password")
	private WebElement passwordfield;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginbtn;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	public void enterEmailaddress(String email)
	{
		emailIdField.sendKeys(email);
	}
	public void enterpassword(String pass) {
		passwordfield.sendKeys(pass);
	}
	public AccountPage clickloginbutton() {
		loginbtn.click();
		return new AccountPage(driver);
		
	}
	public AccountPage login(String emailText,String passwordText) {
		
		emailIdField.sendKeys(emailText);
		passwordfield.sendKeys(passwordText);
		loginbtn.click();
		return new AccountPage(driver);
		
	}
public String retrieveEmailPasswordNotMatchingWarningMessageText() {
		
		String warningText = emailPasswordNotMatchingWarning.getText();
		return warningText;
		
	}
	
	

}
