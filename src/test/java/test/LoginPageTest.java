package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.ConfigReader;
import page.LoginPage;
import page.TestBase;

public class LoginPageTest extends TestBase {
	LoginPage loginPageObj;
	Logger logger = LogManager.getLogger(LoginPageTest.class);
	SoftAssert softAssert;

	@BeforeMethod
	public void setUp() {
		initDriver();
		driver.get(ConfigReader.getProperty("base.url"));
		loginPageObj = PageFactory.initElements(driver, LoginPage.class);
		softAssert= new SoftAssert();
	}

	@Test
	public void loginTest() {
		loginPageObj.enterUserName(ConfigReader.getProperty("username"));			
		loginPageObj.enterPassword(ConfigReader.getProperty("password"));
		loginPageObj.clickSignInButton();
		String expectedTitle = "Codefios2";
		String actualTitle = loginPageObj.getPageTitle();
		takeScreenshot(driver);
		logger.info("This is beforeAssert!");
//		Assert.assertEquals(actualTitle, expectedTitle);
		softAssert.assertEquals(actualTitle, expectedTitle);		
		logger.info("This is afterAssert!");		
		softAssert.assertAll();
	}

	@Test
	public void pageTitleTest()  {
		loginPageObj.enterUserName(ConfigReader.getProperty("username"));
		loginPageObj.enterPassword(ConfigReader.getProperty("password"));
		loginPageObj.clickSignInButton();
		takeScreenshot(driver);
		String expectedTitle = "Codefios";
		String actualTitle = loginPageObj.getPageTitle();
		logger.info("This is beforeAssert!");
		softAssert.assertEquals(actualTitle, expectedTitle);
		logger.info("This is afterAssert!");
		softAssert.assertAll();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();		
	}

}
