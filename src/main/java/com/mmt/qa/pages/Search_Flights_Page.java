package com.mmt.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mmt.qa.base.MMT_Base;
import com.mmt.qa.util.MMT_Util;

public class Search_Flights_Page extends MMT_Base {

	// Page Factory - OR:
	@FindBy(xpath = "//li[@data-cy='oneWayTrip']")
	WebElement linkOneWayTrip;

	@FindBy(id = "fromCity")
	WebElement fromCity;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement fromCitySkeys;

	@FindBy(id = "toCity")
	WebElement toCity;

	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement toCitySkeys;

	@FindBy(xpath = "//span[@class = 'lbl_input latoBold appendBottom10'][text()='DEPARTURE']")
	WebElement departureDateSelection;

	@FindBy(xpath = "//span[@class = 'DayPicker-NavButton DayPicker-NavButton--next']")
	WebElement datePickerNextMnth;

	@FindBy(xpath = "//span[@class = 'lbl_input latoBold appendBottom10'][text()='Travellers & CLASS']")
	WebElement travellersClassSelection;

	@FindBy(xpath = "//div[@class='travellers']/div/ul[@class='guestCounter font12 darkText']/li")
	List<WebElement> adultCount;

	@FindBy(xpath = "//div[@class='makeFlex column']/ul[@class='guestCounter font12 darkText']/li")
	List<WebElement> childCount;

	@FindBy(xpath = "//ul[@class='guestCounter classSelect font12 darkText']/li")
	List<WebElement> travelClass;

	@FindBy(xpath = "//button[@data-cy = 'travellerApplyBtn']")
	WebElement applyTraveller;

	@FindBy(xpath = "//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")
	WebElement searchFlight;

	// Initializing the Page Objects:
	public Search_Flights_Page() {
		PageFactory.initElements(driver, this);
	}

	public Search_Flights_Page searchFlight(String fromcity, String tocity, String noOfFutureDays, String adults,
			String child, String travelclass) throws InterruptedException {

		MMT_Util.explicitWaits_clickOnElement(driver, fromCity, MMT_Util.EXPLICIT_WAIT_TIMEOUT);

		MMT_Util.explicitWaits_elementSendKeys(driver, fromCitySkeys, MMT_Util.EXPLICIT_WAIT_TIMEOUT, fromcity);

		driver.findElement(By.xpath("//p[contains(text(),'" + fromcity + "')]")).click();

		MMT_Util.explicitWaits_clickOnElement(driver, toCity, MMT_Util.EXPLICIT_WAIT_TIMEOUT);

		MMT_Util.explicitWaits_elementSendKeys(driver, toCitySkeys, MMT_Util.EXPLICIT_WAIT_TIMEOUT, tocity);

		driver.findElement(By.xpath("//p[contains(text(),'" + tocity + "')]")).click();

		MMT_Util.explicitWaits_clickOnElement(driver, departureDateSelection, MMT_Util.EXPLICIT_WAIT_TIMEOUT);

		selectDepartureMonth(noOfFutureDays);

		String depdate = MMT_Util.getDepartureDay(noOfFutureDays);

		driver.findElement(By.xpath("//div[@class='dateInnerCell']/p[text()='" + depdate + "']")).click();

		MMT_Util.explicitWaits_clickOnElement(driver, travellersClassSelection, MMT_Util.EXPLICIT_WAIT_TIMEOUT);

		selectAdults(adults);

		selectChild(child);

		selectClass(travelclass);

		MMT_Util.explicitWaits_clickOnElement(driver, applyTraveller, MMT_Util.EXPLICIT_WAIT_TIMEOUT);

		MMT_Util.explicitWaits_clickOnElement(driver, searchFlight, MMT_Util.EXPLICIT_WAIT_TIMEOUT);

		Thread.sleep(10000);

		return new Search_Flights_Page();

	}

	public void selectAdults(String adultcount) {

		// Get the size of the WebElement in page
		System.out.println("Total number of adults that can be selected is :" + adultCount.size());

		// Iterate through the list of all links in the page
		for (int i = 0; i < adultCount.size(); i++) {
			if (adultCount.get(i).getText().equals(adultcount)) {
				MMT_Util.explicitWaits_clickOnElement(driver, adultCount.get(i), MMT_Util.EXPLICIT_WAIT_TIMEOUT);
				System.out.println(adultcount + " adults selected");
			}
		}
	}

	public void selectChild(String childcount) {

		// Get the size of the WebElement in page
		System.out.println("Total number of child that can be selected is :" + childCount.size());

		// Iterate through the list of all links in the page
		for (int i = 0; i < childCount.size(); i++) {
			if (childCount.get(i).getText().equals(childcount)) {
				MMT_Util.explicitWaits_clickOnElement(driver, childCount.get(i), MMT_Util.EXPLICIT_WAIT_TIMEOUT);
				System.out.println(childcount + " child selected");
			}
		}
	}

	public void selectClass(String travelclass) {

		// Get the size of the WebElement in page
		System.out.println("Travel classes that can be selected are :" + travelClass.size());

		// Iterate through the list of all links in the page
		for (int i = 0; i < travelClass.size(); i++) {
			if (travelClass.get(i).getText().equals(travelclass)) {
				MMT_Util.explicitWaits_clickOnElement(driver, travelClass.get(i), MMT_Util.EXPLICIT_WAIT_TIMEOUT);
				System.out.println(travelclass + " travel class selected");
			}
		}
	}

	public void selectDepartureMonth(String noOfFutureDays) {

		int monthDiff = MMT_Util.getMonthDifference(noOfFutureDays);

		if (monthDiff > 0)
			for (int i = 0; i < monthDiff; i++) {

				MMT_Util.explicitWaits_clickOnElement(driver, datePickerNextMnth, MMT_Util.EXPLICIT_WAIT_TIMEOUT);

			}
	}

}
