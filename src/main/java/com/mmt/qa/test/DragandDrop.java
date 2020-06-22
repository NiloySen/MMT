package com.mmt.qa.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.mmt.qa.util.MMT_Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragandDrop {

	public static void main(String[] args) {

		WebDriver driver;

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(MMT_Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(MMT_Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

		// Get primary URL
		driver.get("http://demo.guru99.com/test/drag_drop.html");

		WebElement From = driver.findElement(By.xpath("//a[contains(text(),'BANK')]"));
		WebElement To = driver.findElement(By.xpath("//*[@id=\"bank\"]/li"));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);

		// Dragged and dropped.
		act.dragAndDrop(From, To).build().perform();

	}
}
