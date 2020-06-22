package com.mmt.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mmt.qa.base.MMT_Base;
import com.mmt.qa.util.MMT_Util;

public class Select_Flight_AirAsia_Page extends MMT_Base {

	/*
	 * @FindBy(xpath = "//span[@class='fli_filter__heading'][text()='Stops']")
	 * WebElement stopsLink;
	 */

	@FindBy(xpath = "//span[@class = 'airways-name ' and text()='AirAsia']/ancestor::div[@class='pull-left airline-info']/following-sibling::div[@class='pull-left  make_relative price']//span")
	List<WebElement> searchFlight;
	
	@FindBy(xpath = "//span[@class = 'airways-name ' and text()='AirAsia']/ancestor::div[@class='pull-left airline-info']/following-sibling::div[@class='pull-left']//div[@class ='dept-time']")
	List<WebElement> selectFlightTime;

	// Initializing the Page Objects:
	public Select_Flight_AirAsia_Page() {
		PageFactory.initElements(driver, this);
	}
	
	public void captureFlightPrice() {

		//List<String> getPrice = MMT_Util.capture_Price(searchFlight);

		//String lowestPrice = MMT_Util.format_Price(getPrice.get(0).toString());
		
		List<String> getTimings = MMT_Util.capture_Time(selectFlightTime);
		
		String lastFlight = MMT_Util.format_Timings(getTimings.get(getTimings.size()-1).toString());

		
		/*
		 * driver.findElement(By.xpath(
		 * "//span[@class = 'airways-name ' and text()='AirAsia']/ancestor::div[@class='pull-left airline-info']/following-sibling::div[@class='pull-left  make_relative price']//span[contains(text(),'"
		 * + lowestPrice +
		 * "')]/parent::p/parent::div/following-sibling::div/button[contains(@id,'bookbutton')]"
		 * )) .click();
		 */
		
		//driver.findElement(By.xpath("//span[@class='actual-price'][contains(text(),'" + lowestPrice + "')]/parent::p/parent::div/following-sibling::div/button[contains(text(),'Book Now')]")).click();
		 
		
		driver.findElement(By.xpath("//span[@class = 'airways-name ' and text()='AirAsia']/ancestor::div[@class='pull-left airline-info']/following-sibling::div[@class='pull-left']//div[text()='"+ lastFlight +"']/parent::div/ancestor::div[@class='pull-left']//following-sibling::div[@class='pull-left make_relative']/button[contains(@id,'bookbutton')]")).click();
		
		driver.findElement(By.xpath("(//span[@class = 'airways-name ' and text()='AirAsia']/ancestor::div[@class='pull-left airline-info']/following-sibling::div[@class='pull-left']//div[text()='"+ lastFlight +"']/parent::div/ancestor::div[@class='pull-left']//following-sibling::div[@class='pull-left make_relative']/button[contains(@id,'bookbutton')]/ancestor::div[@class='card-upperpart-wrapper']/following-sibling::div[@class='viewFaresOuter collapse in']//button)[1]")).click();
		}

}
