package com.sample.tests.junit;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.ui.controls.SelectList;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

@RunWith(Parameterized.class)
public class BookingSearchTest {
	private String destination;
	private boolean isLeisure;
	private int numberOfAdults;

	private SearchPage searchPage;
	private SearchResultsPage searchResultsPage;
	
	public BookingSearchTest(String destination, boolean isLeisure,
			int numberOfAdults) {
		super();
		this.destination = destination;
		this.isLeisure = isLeisure;
		this.numberOfAdults = numberOfAdults;
	}

	@Before
	public void setUp() throws Exception {
		Configuration.load();
		Configuration.print();
		
		System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
		System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
		
		DesiredCapabilities cap = new DesiredCapabilities();
		Driver.add(Configuration.get("browser"), cap);
		searchPage = PageFactory.init(SearchPage.class);
		searchPage.navigate();
	}
	@After
	public void tearDown() {
	    Driver.current().quit();
	}
	@Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(
            new Object[][] {
            		{"London", true, 2 },
            		{"Manchester", false, 1 },
            });
    }
	
	@Test
	public void testValidSearch() throws Exception {
	    searchPage.editDestination.setText(destination);
	    searchPage.checkInToday();
		searchPage.selectTravelFor(isLeisure);
		searchPage.radioHotels.click();
		searchPage.selectAdultsNumber.selectByText("" + numberOfAdults);
		searchPage.buttonSubmit.click();
		searchResultsPage = PageFactory.init(SearchResultsPage.class);
		searchResultsPage.editDestination.click();
		searchResultsPage.isTextPresent(destination);
		searchResultsPage.captureScreenShot("./build/image01.png");
	}

}
