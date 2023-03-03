package com.tutorialninja.testclasses;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;
import com.tutorialninja.pages.AccountPage;
import com.tutorialninja.pages.HomePage;
import com.tutorialninja.pages.LoginPage;
import com.tutorialninja.utils.Utilities;


public class LoginTest extends Base {
	
	public WebDriver driver;
	LoginPage loginpage;
	AccountPage accountPage;
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginpage=homePage.navigateToLoginPage();
	
	}
	@AfterMethod
	public void teardoun()
	{
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String Password) {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		accountPage=loginpage.login(email, Password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account Information option is not displayed");
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] suplyTestData(){
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		loginpage.login(prop.getProperty("InvalidEmail"),prop.getProperty("InvalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected Warning message is not displayed");
	}
	@Test(priority=3)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		loginpage.login(prop.getProperty("validEmail"),prop.getProperty("InvalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected Warning message is not displayed");
	}
	@Test(priority=4)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		loginpage.login(prop.getProperty("InvalidEmail"),prop.getProperty("validPassword"));
		//Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected Warning message is not displayed");
	}
	@Test(priority=5)
	public void verifyLoginWithLaveEmptyCredentials() {
		Utilities.waitTillAllElementAppear(driver,Utilities.PAGE_LOAD_TIME);
		loginpage.login("","");
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected Warning message is not displayed");
	}

}
