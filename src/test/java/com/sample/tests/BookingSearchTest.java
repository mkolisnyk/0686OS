package com.sample.tests;
import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;

public class BookingSearchTest {
	private WebDriver driver;
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
	@Test
	public void testValidSearch() {
		// 1) Record test
		// 2) Add drivers
		// 3) Initialize webdriver
		// 4) Update long locators (date selection and search button)
		// 5) Move pre- and postconditions to setUp and teardown methods
		
		driver.findElement(By.id("ss")).click();
		driver.findElement(By.id("ss")).clear();
		driver.findElement(By.id("ss")).sendKeys("london");
		driver.findElement(
				By.cssSelector("i.sb-date-field__chevron.bicon-downchevron"))
				.click();
		
		driver.findElement(
				By.xpath("//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]"))
				.click();
		driver.findElement(By.xpath("(//input[@name='sb_travel_purpose'])[2]"))
				.click();
		driver.findElement(By.xpath("(//input[@name='sb_travel_purpose'])[2]"))
				.click();
		driver.findElement(By.xpath("(//input[@name='nflt'])[2]")).click();
		driver.findElement(By.xpath("(//input[@name='nflt'])[2]")).click();
		new Select(driver.findElement(By.id("group_adults")))
				.selectByVisibleText("1");
		new Select(driver.findElement(By.id("group_adults")))
				.selectByVisibleText("1");
		driver.findElement(
				By.cssSelector("#group_adults > option[value=\"1\"]")).click();
		driver.findElement(By.xpath("//button[@type='submit']"))
				.click();
		driver.findElement(By.id("ss")).click();
	}

}
