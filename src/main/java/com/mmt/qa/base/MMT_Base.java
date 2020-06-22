package com.mmt.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.mmt.qa.util.MMT_Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MMT_Base {

	public static WebDriver driver;
	public static Properties prop;

	// What is log? : capturing info/activities at the time of program execution.
	// types of logs:
	// 1. info
	// 2. warn
	// 3. debug
	// 4. fatal

	// how to generate the logs? : use Apache log4j API (log4j jar)
	// How it works? : it reads log 4j configuration from log4j.properties file
	// where to create: create inside resources folder

	public static Logger log = Logger.getLogger("**<--Log4j app logs-->**");

	//Base class constructor to read properties file
	public MMT_Base() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/mmt/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// "C:\\Users\\Shreo-Niloy\\eclipse-workspace\\FreeCRMCogmento" -> user.dir
	// "\\src\\main\\resources\\executables\\chromedriver.exe"

	// To read browser name from TestNG.xml
	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeTest
	public static void initialization(String browserName) {
		log.info("**Initializing Browser**");

		// String browserName = prop.getProperty("browser"); --> To read browser name
		// from config.properties

		if (browserName.equalsIgnoreCase("chrome")) {
			/*
			 * System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
			 * + prop.getProperty("chromepath"));
			 */
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("headlesschrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
		}

		else if (browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\executables\\geckodriver");
			driver = new FirefoxDriver();
		}

		log.info("**Browser launched**");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(MMT_Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(MMT_Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

		log.info("**Get the browser URL**");
		driver.get(prop.getProperty("loggedurl"));

	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
