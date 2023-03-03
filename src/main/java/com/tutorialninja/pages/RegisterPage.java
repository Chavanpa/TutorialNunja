package com.tutorialninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstnameField;
	
	@FindBy(id="input-lastname")
	private WebElement LastNameField;
	
	@FindBy(id="input-email")
	private WebElement EmailidField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement PasswordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPassField;
	
	@FindBy(name="newsletter")
	private WebElement clicknewsLetter;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	@FindBy(xpath="//div[@class='text-danger']")
	private WebElement ConfirmpaassNotMatch;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
		public void enterfirstName(String Fname) {
			firstnameField.sendKeys(Fname);
		}
		
		public void enterLastName(String Lname) {
			LastNameField.sendKeys(Lname);
		}
		public void enterEmailaddress(String Email) {
			EmailidField.sendKeys(Email);
		}
		public void EnterTelephone(String Telephone) {
			telephoneField.sendKeys(Telephone);
		}
		public void enterPassword(String pass) {
			PasswordField.sendKeys(pass);
		}
		public void enterConfirmpass(String ConfirmPass) {
			confirmPassField.sendKeys(ConfirmPass);
		}
		public void selectYesNewsletterOption() {
			
			clicknewsLetter.click();
			
		}
		public void selectPrivacyPolicy() {
			
			privacyPolicyField.click();
			
		}
		public AccountSuccessPage clickOnContinueButton() {
			
			continueButton.click();
			return new AccountSuccessPage(driver);
			
		}
		
		public AccountSuccessPage registerWithMandatoryFields(String Fname,String Lname,String Email,String telephone,String Pass,String ConfirmPass) {
			firstnameField.sendKeys(Fname);
			LastNameField.sendKeys(Lname);
			EmailidField.sendKeys(Email);
			telephoneField.sendKeys(telephone);
			PasswordField.sendKeys(Pass);
			confirmPassField.sendKeys(ConfirmPass);
			privacyPolicyField.click();

			continueButton.click();
			return new AccountSuccessPage(driver);
		}
		public String retrievePasswordWarning() {
			
			String passwordWarningText = passwordWarning.getText();
			return passwordWarningText;
		}
		
		public String retrivePasswordNotMatch() {
			String ConfirmPassNotMatchin=ConfirmpaassNotMatch.getText();
			return ConfirmPassNotMatchin;
		}
		public String retrieveTelephoneWarning() {
			
			String telephoneWarningText = telephoneWarning.getText();
			return telephoneWarningText;
		}
		
		public String retrieveEmailWarning() {
			
			String emailWarningText = emailWarning.getText();
			return emailWarningText;
		}
		public String retrieveDuplicateEmailAddressWarning() {
			
			String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
			return duplicateEmailWarningText;
		}
		public String retrieveLastNameWarning() {
			
			String lastNameWarningText = lastNameWarning.getText();
			return lastNameWarningText;
		}
		
		public String retrieveFirstNameWarning() {
			
			String firstNameWarningText = firstNameWarning.getText();
			return firstNameWarningText;
		}
		
		public String retrievePrivacyPolicyWarning() {
			
			String privacyPolicyWarningText = privacyPolicyWarning.getText();
			return privacyPolicyWarningText;
			
		}
		
		public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String ConfirmPass) {
			
			firstnameField.sendKeys(firstNameText);
			LastNameField.sendKeys(lastNameText);
			EmailidField.sendKeys(emailText);
			telephoneField.sendKeys(telephoneText);
			PasswordField.sendKeys(passwordText);
			confirmPassField.sendKeys(ConfirmPass);
			clicknewsLetter.click();
			privacyPolicyField.click();
			continueButton.click();
			return new AccountSuccessPage(driver);
			
		}
		public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning,String expectedLastNameWarning,String expectedEmailWarning,String expectedTelephoneWarning,String expectedPasswordWarning) {
			
			boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
			boolean firstNameWarningStatus = firstNameWarning.getText().equals(expectedFirstNameWarning);
			boolean lastNameWarningStatus = lastNameWarning.getText().equals(expectedLastNameWarning);
			boolean emailWarningStatus = emailWarning.getText().equals(expectedEmailWarning);
			boolean telephoneWarningStatus = telephoneWarning.getText().equals(expectedTelephoneWarning);
			boolean passwordWarningStatus = passwordWarning.getText().equals(expectedPasswordWarning);
			return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
		}
	
		
		
		
		
	}


