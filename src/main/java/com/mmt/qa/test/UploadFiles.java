package com.mmt.qa.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mmt.qa.util.MMT_Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadFiles {

	public static void main(String[] args) {

		WebDriver driver;

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(MMT_Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(MMT_Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

		// Get primary URL
		driver.get("http://testautomationpractice.blogspot.com/");

		// WebElement uploadLink =
		WebElement uploadLink = driver.findElement(By.xpath("//input[@id='RESULT_FileUpload-10']"));
		uploadLink.click();

		/*
		 * @SuppressWarnings("deprecation") WebDriverWait wait = new
		 * WebDriverWait(driver, 10); WebElement uploadLink = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//input[@id='RESULT_FileUpload-10']")));
		 */

		// enter the file path onto the file-selection input field
		uploadLink.sendKeys("C:\\Users\\Shreo-Niloy\\Desktop\\Eclipse Neon\\UploadFile\\sample.pdf");

	}
}