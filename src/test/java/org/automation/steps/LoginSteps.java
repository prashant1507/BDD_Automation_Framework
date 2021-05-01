package org.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.pages.HomePage;
import org.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.SkipException;

public final class LoginSteps {
	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();

	@When("^Login Page is displayed$")
	public void verifyLoginPage() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed(), "Forgot Password link is not displayed.");
	}

	@And("^User provides username (.*)$")
	public void userProvidesUsernameAsAdmin(String username) {
		loginPage.enterUsername(username);
	}

	@And("^User provides password (.*)$")
	public void userProvidesPassword(String pass) {
		loginPage.enterPassword(pass);
	}

	@And("^User clicks on login button$")
	public void userClicksOnLoginButton() {
		loginPage.clickLoginBtn();
	}

	@Then("^Test case is skipped$")
	public void testCaseIsSkipped() {
		throw new SkipException("hi, I am skipped");
	}

	@Then("^User is at homepage$")
	public void userIsAtHomepage() {
		Assert.assertTrue(homePage.getWelcomeText("Welcomes"), "Failed to login.");
	}
}
