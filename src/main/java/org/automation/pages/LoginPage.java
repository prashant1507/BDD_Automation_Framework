package org.automation.pages;

import org.openqa.selenium.By;

public final class LoginPage extends BasePage {

	private final By pageLogoImg = By.xpath("//div[@id='divLogo']/img");
	private final By usernameTextBox = By.id("txtUsername");
	private final By passwordTextBox = By.id("txtPassword");
	private final By loginBtn = By.xpath("//input[@id='btnLogin']");
	private final By forgotPasswordLink = By.xpath("//div[@id='forgotPasswordLink']/a");
	private final By invalidCredentialsErrorMsg = By.xpath("//span[@id='spanMessage']");

	public boolean isPageLogoImgDisplayed() {
		return isDisplayed(pageLogoImg);
	}
	
	public boolean isForgotPasswordLinkDisplayed() {
		return isDisplayed(forgotPasswordLink);
	}

	public void enterUsername(String username) {
		sendKeys(usernameTextBox, username);
	}

	public void enterPassword(String password) {
		sendKeys(passwordTextBox, password);
	}

	public void clickLoginBtn() {
		click(loginBtn);
	}
	
	public String getInvalidCredentialsErrorMsg() {
		return getText(invalidCredentialsErrorMsg);
	}
}
