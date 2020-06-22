package com.mmt.qa.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.mmt.qa.base.MMT_Base;

public class MMT_Util extends MMT_Base {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT_TIMEOUT = 20;
	public static long EXPLICIT_WAIT_TIMEOUT = 2;

	// Take Entire page Screenshot method
	public static void takePageScreenshot(String pageName) throws IOException {

		// Take screenshot and store as a file format
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		// now copy the screenshot to desired location using copyFile //method
		FileUtils.copyFile(srcFile, new File(currentDir + "\\screenshots_Teststepscreenshots\\" + pageName + "_"
				+ System.currentTimeMillis() + ".png"));
	}

	// Take Page Section Screenshot method
	public static String captureScreenSection(WebElement element, String sectionName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		// highlightElement(element,driver);
		File source = element.getScreenshotAs(OutputType.FILE);
		String target = System.getProperty("user.dir") + "/screenshots_ScreenSection/" + sectionName + "_" + dateName
				+ ".png";
		File finalDestination = new File(target);
		FileUtils.copyFile(source, finalDestination);
		System.out.println("Screenshot taken");
		return target;
	}

	// Highlight Page Section method
	public static void highlightElement(WebElement highlightElement, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border.='2px soild red'", driver);
	}

	// Take Failure Screenshot method
	public static String getFailedScreenshot(String screenshotName) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/screenshots_FailedTests/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destination;
	}

	// Explicit Wait element visibility method
	@SuppressWarnings("deprecation")
	public static void explicitWaits_visibilityOfelement(WebDriver driver, WebElement element, long timeout) {
		try {
			if (element != null) {
				new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
			} else {
				System.out.println("Web Element is null");
			}
		} catch (ElementNotVisibleException e) {
			System.out.println("Element is not visible on the page");
		} catch (NoSuchElementException e) {
			System.out.println("No such Element is present on the page");
		}
	}

	// Explicit Wait element click method
	@SuppressWarnings("deprecation")
	public static void explicitWaits_SelectElement(WebDriver driver, WebElement element, long timeout,
			String valueToSelect) {
		try {
			if (element != null) {
				explicitWaits_visibilityOfelement(driver, element, timeout);
				new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				Select selectCountry = new Select(element);
				selectCountry.selectByVisibleText(valueToSelect);
			} else {
				System.out.println("Web Element is null");
			}
		} catch (ElementClickInterceptedException e) {
			System.out.println("Element is not clickable on the page");
		}
	}

	// Explicit Wait element click method
	@SuppressWarnings("deprecation")
	public static void explicitWaits_clickOnElement(WebDriver driver, WebElement element, long timeout) {
		try {
			if (element != null) {
				explicitWaits_visibilityOfelement(driver, element, timeout);
				new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
				element.click();
			} else {
				System.out.println("Web Element is null");
			}
		} catch (ElementClickInterceptedException e) {
			System.out.println("Element is not clickable on the page");
		}
	}

	// Explicit Wait element send-keys method
	@SuppressWarnings("deprecation")
	public static void explicitWaits_elementSendKeys(WebDriver driver, WebElement element, long timeout,
			String keysToSend) {
		try {
			if (element != null && keysToSend != null) {
				explicitWaits_clickOnElement(driver, element, timeout);
				element.clear();
				element.sendKeys(keysToSend);
			} else {
				System.out.println("Web Element or keysToSend null");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element is not clickable or unable to send data to the text field");
		}
	}

	// Element is displayed
	public static Boolean elementIsDisplayed(WebElement element) {

		Boolean display = null;

		try {
			if (element != null) {
				display = element.isDisplayed();
			} else {
				System.out.println("Web Element is null");
			}
		} catch (Exception e) {
			System.out.println("Element is not displayed");
		}
		return display;
	}

	// Soft Assert for String text
	@SuppressWarnings("deprecation")
	public static void verify_SoftAssert(String actualText, String expectedText) {
		try {
			if (actualText != null && expectedText != null) {
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertEquals(actualText, expectedText, "Assert is successful");
			} else {
				System.out.println("Expected text or Actual text is null");
			}
		} catch (Exception e) {
			System.out.println("Assert failed, expected and actual mismatch");
		}
	}

	public static String format_Price(String lowestPrice) {

		int lowestPriceToBeFormatted = Integer.parseInt(lowestPrice);
		String lowestPriceAsString = String.format("%,d", lowestPriceToBeFormatted);
		return lowestPriceAsString;
	}

	public static String format_Timings(String earliestTimings) {

		// int earliestTimingsToBeFormatted = Integer.parseInt(earliestTimings);
		StringBuilder timing = new StringBuilder(earliestTimings);
		String selectedTime = timing.insert(2, ':').toString();
		return selectedTime;
	}

	public static List<String> capture_Price(List<WebElement> prices) {

		// Get the size of the prices WebElement in page
		System.out.println("Total number of flights that can be selected is :" + prices.size());

		List<String> getPrice = new ArrayList<String>();

		// Iterate through the list of all listed flight prices in the page
		for (int i = 0; i < prices.size(); i++) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			getPrice.add(prices.get(i).getText().substring(2).replace(",", ""));
			// Remove the currency symbol and replace the comma from the price
			System.out.println(prices.get(i).getText().substring(2).replace(",", ""));
		}

		System.out.println(getPrice);
		Collections.sort(getPrice);

		return getPrice;
	}

	public static List<String> capture_Time(List<WebElement> timings) {

		// Get the size of the prices WebElement in page
		System.out.println("Total number of flights that can be selected is :" + timings.size());

		List<String> getTimings = new ArrayList<String>();

		// Iterate through the list of all listed flight prices in the page
		for (int i = 0; i < timings.size(); i++) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			getTimings.add(timings.get(i).getText().replace(":", ""));
			// Remove the currency symbol and replace the comma from the price
			System.out.println(timings.get(i).getText().replace(":", ""));
		}

		System.out.println(getTimings);
		Collections.sort(getTimings);

		return getTimings;
	}

	public static int getMonthDifference(String noOfFutureDays) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM");
		Date currentDate = new Date();
		System.out.println(dateFormat.format(currentDate));
		String currentMonthPart = dateFormat.format(currentDate).substring(3);
		System.out.println(currentMonthPart);

		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);

		// manipulate date
		c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(noOfFutureDays));

		// convert calendar to date
		Date futureDate = c.getTime();
		System.out.println(dateFormat.format(futureDate));
		String futureMonthPart = dateFormat.format(futureDate).substring(3);
		System.out.println(futureMonthPart);

		int monthDiff = Integer.parseInt(futureMonthPart) - Integer.parseInt(currentMonthPart);

		return monthDiff;
	}

	public static String getDepartureDay(String noOfFutureDays) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM");
		Date currentDate = new Date();
		System.out.println(dateFormat.format(currentDate));

		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);

		// manipulate date
		c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(noOfFutureDays));

		// convert calendar to date
		Date futureDate = c.getTime();
		System.out.println(dateFormat.format(futureDate));
		String futureDatePart = dateFormat.format(futureDate).substring(0, 2);

		return futureDatePart;
	}

	@DataProvider(name = "util generic data provider")
	public static Object[][] getTestData(Method testMethodName) {

		// sheetName in the excel should be the test method name in the test class
		String dataSheetName = null;

		try {
			dataSheetName = testMethodName.getName();
		} catch (IllegalArgumentException e) {
			System.out.println("Test method name was not properly passed or method name conversion was not successful");
		}

		Object data[][] = ExcelDataReader.getTestData(dataSheetName);
		return data;
	}
}
