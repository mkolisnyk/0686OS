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
		driver.findElement(By.id("ss")).click();
		driver.findElement(By.id("ss")).clear();
		driver.findElement(By.id("ss")).sendKeys(this.destination);
		driver.findElement(
				By.cssSelector("i.sb-date-field__chevron.bicon-downchevron"))
				.click();
		
		driver.findElement(
				By.xpath("//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]"))
				.click();
		if (this.isLeisure) {
			driver.findElement(By.xpath("(//input[@name='sb_travel_purpose'])[2]"))
					.click();
		} else {
			driver.findElement(By.xpath("(//input[@name='sb_travel_purpose'])[1]"))
					.click();
		}
		driver.findElement(By.xpath("(//input[@name='nflt'])[2]")).click();
		new Select(driver.findElement(By.id("group_adults")))
				.selectByVisibleText("" + this.numberOfAdults);
		driver.findElement(
				By.cssSelector("#group_adults > option[value=\"" + this.numberOfAdults + "\"]")).click();
		driver.findElement(By.xpath("//button[@type='submit']"))
				.click();
		driver.findElement(By.id("ss")).click();
	}

}
