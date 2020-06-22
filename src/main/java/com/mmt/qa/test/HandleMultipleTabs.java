package com.mmt.qa.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mmt.qa.util.MMT_Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleMultipleTabs {
	public static void main(String[] args) {

		WebDriver driver;

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(MMT_Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(MMT_Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

		// Get primary URL
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		// Fetch the primary tab session
		String loginTab = driver.getWindowHandle();
		System.out.println("The primary tab is :" + loginTab);

		/*
		 * // Click the link for Forgot Password
		 * 
		 * @SuppressWarnings("deprecation") WebDriverWait wait = new
		 * WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.elementToBeClickable(By.
		 * xpath("//a[text()='Forgot your password?']"))).click(); //
		 * driver.findElement(By.xpath("//a[text()='Forgot your password?']"));
		 */
		WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
		// Fetch the secondary tab session
		String forgotPaswordTab = newTab.getWindowHandle();
		System.out.println("The secondary tab is :" + forgotPaswordTab);

		// Get the secondary URL
		newTab.get("https://opensource-demo.orangehrmlive.com/index.php/auth/requestPasswordResetCode");

		// enter the reset password details
		newTab.findElement(By.id("securityAuthentication_userName")).sendKeys("TestUser");
		newTab.findElement(By.id("btnSearchValues")).click();
		// close the secondary tab
		newTab.close();

		// switch back to the primary window
		driver.switchTo().window(loginTab);
		// enter the login details
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
	}
}
