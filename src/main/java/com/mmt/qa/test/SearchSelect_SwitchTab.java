package com.mmt.qa.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mmt.qa.util.MMT_Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchSelect_SwitchTab {

	public static void main(String[] args) throws IOException, InterruptedException {

		BufferedReader obj = new BufferedReader(new InputStreamReader((System.in)));
		System.out.println("Enter Search String:");
		String searchText = obj.readLine();

		WebDriver driver;

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(MMT_Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(MMT_Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

		// Get primary URL
		driver.get("http://testautomationpractice.blogspot.com/");

		// Enter search text
		WebElement elementSearchText = driver.findElement(By.xpath("//input[@class='wikipedia-search-input']"));
		elementSearchText.sendKeys(searchText);

		Thread.sleep(3000);

		// Search
		WebElement searchBtn = driver.findElement(By.xpath("//input[@class='wikipedia-search-button']"));
		searchBtn.click();

		Thread.sleep(3000);

		// Select link from search result
		List<WebElement> selectElement = driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']"));
		for (int i = 0; i < selectElement.size(); i++) {
			if (selectElement.get(i).getText().equals(searchText)) {
				driver.findElement(By.xpath("//div[@id='wikipedia-search-result-link']/a[text()='" + searchText + "']"))
						.click();
			}
		}

		Thread.sleep(3000);

		//WebDriver wikiDriver = driver.switchTo().newWindow(WindowType.TAB);
		driver.switchTo().newWindow(WindowType.TAB);

		Thread.sleep(3000);

		Set<String> wikiWindowHandles = driver.getWindowHandles();
		Iterator<String> itr = wikiWindowHandles.iterator();
		System.out.println(wikiWindowHandles.size());
		String parentWindow = itr.next();
		String childWindow = itr.next();
		
		if(parentWindow!=childWindow) {
			driver.switchTo().window(childWindow);
		}

		//driver.switchTo().defaultContent();

		WebElement heading = driver
				.findElement(By.xpath("//h1[@id='firstHeading']/i[text()='" + searchText + "']"));
		Boolean searchFlag = heading.getText().contains(searchText);
		System.out.println(searchFlag);

	}
}
