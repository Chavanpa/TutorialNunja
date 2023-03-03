package com.tutorialninja.testclasses;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;
import com.tutorialninja.pages.AccountSuccessPage;
import com.tutorialninja.pages.HomePage;
import com.tutorialninja.pages.RegisterPage;
import com.tutorialninja.utils.Utilities;



public class RegisterTest extends Base {
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}
	
	@BeforeMethod()
	public void setup() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}
	@AfterMethod()
	public void teardoun() {
		driver.quit();
	}
	@Test(priority=1)
	
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"),prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
	
	}
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"),prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
	
	}
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"),prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regaring duplicate email address is not displayed");
	
	}
	@Test(priority=4)
	public void verifyRegisteringAccountWithDiffPaswword() {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"),dataProp.getProperty("invalidPassword"));
		Assert.assertFalse(registerPage.retrivePasswordNotMatch().contains(dataProp.getProperty("invalidPassword")), "Warning message Password & ConfirmPassword match is not displayed");
	}
	
	
	
	
	
	
	
	

}
