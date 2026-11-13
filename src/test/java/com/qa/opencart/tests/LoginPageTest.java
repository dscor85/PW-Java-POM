package com.qa.opencart.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage = homePage.navigateToLoginPage();
		String actLoginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("page act title: " + actLoginPageTitle);
		Assert.assertEquals(actLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);

	}

	@Test(priority = 2)
	public void forgotPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLink());

	}

	@Test(priority = 3)
	public void appLoginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
	}
	
	
	
	//Playwright Assertions
	@Test(priority = 4)
	public void locatorTestA() {
		assertThat(page.getByPlaceholder("Search")).isAttached();;

	}

	@Test(priority = 5)
	public void locatorTestB() {
		assertThat(page.getByAltText("naveenopencart")).isVisible();

	}

	@Test(priority = 6)
	public void locatorTestC() {
		assertThat(page).hasTitle("Account Login");

	}

	@Test(priority = 7)
	public void locatorTestD() {

		assertThat(page.locator("//li//span[contains(text(),'123')]")).hasText("123456789");

	}

}
