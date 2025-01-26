package page;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.ConfigReader;

public class TestBase {

	public static WebDriver driver;

	public static void initDriver() {

		String browser = ConfigReader.getProperty("browser");

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", ConfigReader.getProperty("webdriver.chrome.driver"));
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", ConfigReader.getProperty("webdriver.gecko.driver"));
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Unable to find browser!");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(ConfigReader.getProperty("base.url"));
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			String browser = ConfigReader.getProperty("browser");

			switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", ConfigReader.getProperty("webdriver.chrome.driver"));
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", ConfigReader.getProperty("webdriver.gecko.driver"));
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Unable to find browser!");
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public void takeScreenshot(WebDriver driver)  {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String currentDirectory = System.getProperty("user.dir");
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyy_HHmmss");
		Date date = new Date();
		String label = formatter.format(date);
		try {
			FileUtils.copyFile(sourceFile, new File(currentDirectory + "/screenshot/" + label + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to save Screenshot. Error:" + e.getMessage());
		}
	}

}
