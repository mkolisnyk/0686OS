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
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.ui.controls.SelectList;

@RunWith(Parameterized.class)
public class BookingSearchTest {
	private WebDriver driver;
	private String destination;
	private boolean isLeisure;
	private int numberOfAdults;

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
		String baseUrl = Configuration.get("url");
		System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
		System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
		
		DesiredCapabilities cap = new DesiredCapabilities();
		Driver.add(Configuration.get("browser"), cap);
		driver = Driver.current();
		driver.get(baseUrl);
	}
	@After
	public void tearDown() {
		driver.quit();
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
	public void testValidSearch() {
	    Edit editDestination = new Edit(driver, By.id("ss"));
	    Control checkoutDayExpand = new Control(driver,
	            By.cssSelector("i.sb-date-field__chevron.bicon-downchevron"));
		Control checkoutDayToday = new Control(driver,
		        By.xpath("//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]"));
	    Control radioLeisure = new Control(driver, By.xpath("(//input[@name='sb_travel_purpose'])[2]"));
	    Control radioBusiness = new Control(driver, By.xpath("(//input[@name='sb_travel_purpose'])[1]"));
	    Control radioHotels = new Control(driver, By.xpath("(//input[@name='nflt'])[2]"));
	    SelectList  selectAdultsNumber = new SelectList(driver, By.id("group_adults"));
	    Control buttonSubmit = new Control(driver, By.xpath("//button[@type='submit']"));
        
	    editDestination.setText(destination);
	    checkoutDayExpand.click();
	    checkoutDayToday.click();

		if (this.isLeisure) {
		    radioLeisure.click();
		} else {
		    radioBusiness.click();
		}
		radioHotels.click();
		selectAdultsNumber.selectByText("" + numberOfAdults);
		buttonSubmit.click();
		editDestination.click();
	}

}
