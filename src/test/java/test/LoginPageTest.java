package test;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.ConfigReader;
import page.LoginPage;
import page.TestBase;

public class LoginPageTest extends TestBase {
	LoginPage loginPageObj;

	@BeforeMethod
	public void setUp() {
		initDriver();
		driver.get(ConfigReader.getProperty("base.url"));
		loginPageObj = PageFactory.initElements(driver, LoginPage.class);
	}

	@Test
	public void loginTest() {
		loginPageObj.enterUserName(ConfigReader.getProperty("username"));		
		System.out.println("print something!");
		loginPageObj.enterPassword(ConfigReader.getProperty("password"));
		loginPageObj.clickSignInButton();
		takeScreenshot(driver);
	}

	@Test
	public void pageTitleTest()  {
		loginPageObj.enterUserName(ConfigReader.getProperty("username"));
		loginPageObj.enterPassword(ConfigReader.getProperty("password"));
		loginPageObj.clickSignInButton();
		String expectedTitle = "Codefios";
		String actualTitle = loginPageObj.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		takeScreenshot(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
