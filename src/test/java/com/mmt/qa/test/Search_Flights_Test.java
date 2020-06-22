package com.mmt.qa.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.mmt.qa.base.MMT_Base;
import com.mmt.qa.pages.Search_Flights_Page;
import com.mmt.qa.pages.Select_Flight_AirAsia_Page;
import com.mmt.qa.util.MMT_Util;

public class Search_Flights_Test extends MMT_Base {

	Search_Flights_Page searchFlightPage;
	Select_Flight_AirAsia_Page searchAirAsiaFlight;

	// Data Sheet name to be provided when dataprovider is defined within the test class 
	//String searchFlightDataSheet = "SearchFlight";

	public Search_Flights_Test() {
		super();
	}

	/*
	 * @BeforeClass public void setUp() { // initialization(); demoLoginPage = new
	 * Demo_Login_Page(); billingAddressPage = new Demo_BillingAddress_Page(); log.
	 * info("**Initialization invoke for Demo_BillingAddressTest is successful**");
	 * }
	 */

	/*
	 * @DataProvider(name = "search flight data excel") public Object[][]
	 * getFlightTestData() { Object data[][] =
	 * ExcelDataReader.getTestData(searchFlightDataSheet); return data; }
	 */

	//
	@Test(description = "search flight test", priority = 1, dataProviderClass = MMT_Util.class, dataProvider = "util generic data provider", enabled = true, timeOut = 50000)
	public void validateSearchFlightTest(String fromcity, String tocity, String noOfFutureDays, String adults,
			String child, String travelclass) throws IOException, InterruptedException {
		log.info("**Start Search_Flights_Pages -> validateSearchFlightTest**");
		searchFlightPage = new Search_Flights_Page();
		searchFlightPage.searchFlight(fromcity, tocity, noOfFutureDays, adults, child, travelclass);
		log.info("**End Search_Flights_Pages -> validateSearchFlightTest**");

	}

	@Test(description = "select flight test", priority = 2, enabled = false, dependsOnMethods = { "validateSearchFlightTest" })
	public void validateSelectFlightPriceTest() throws IOException, InterruptedException {
		log.info("**Start Select_Flight_AirAsia_Page -> validateSelectFlightPriceTest**");
		searchAirAsiaFlight = new Select_Flight_AirAsia_Page();
		searchAirAsiaFlight.captureFlightPrice();
		log.info("**End Select_Flight_AirAsia_Page -> validateSelectFlightPriceTest**");

	}

}
