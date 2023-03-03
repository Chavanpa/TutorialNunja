package com.tutorialninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;





public class HomePage {

	WebDriver driver;
	
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginoption;
	
	@FindBy(linkText="Register")
	private WebElement RegisterOption;
	
	@FindBy(name="search")
	private WebElement Searchfield;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement Searchbutton;
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickMyAccountDropMenu() {
		myAccountDropMenu.click();
	}
	public void clickLoginOption() {
		loginoption.click();
	}
	public void clickOnRegidter() {
		RegisterOption.click();
	}
	public void enterProductIntoSearchBoxField(String Product) {
		Searchfield.sendKeys(Product);
	}
	public SearchPage clicksearchbtn() {
		Searchbutton.click();
		return new SearchPage(driver);
	}
 public RegisterPage navigateToRegisterPage() {
		
		myAccountDropMenu.click();
		RegisterOption.click();
		return new RegisterPage(driver);
	}
 public LoginPage navigateToLoginPage() {
		
		myAccountDropMenu.click();
		loginoption.click();
		return new LoginPage(driver);
	}

}
