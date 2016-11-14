package com.sample.tests.junit;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.tests.pages.SearchPage;

public class SearchPageUITest {

    public SearchPageUITest() {
        // TODO Auto-generated constructor stub
    }
    private SearchPage searchPage;
    
    @Before
    public void setUp() throws Exception {
        Configuration.load();
        Configuration.print();
        
        System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
        System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
        
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        cap.setCapability("platformVersion", "5.0");
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", "/Users/mykolak/base.apk");
        cap.setCapability("deviceName", "Any");
        cap.setCapability("commandTimeout", "60");
        if (Configuration.platform().isWeb()) {
            Driver.add(Configuration.get("browser"), cap);
        } else {
            Driver.add(Configuration.get("driver_url"), Configuration.get("browser"), cap);
        }
        searchPage = PageFactory.init(SearchPage.class);
        searchPage.navigate();
    }
    @After
    public void tearDown() {
        Driver.current().quit();
    }

    @Test
    public void testVerifyUIOnSearchPage() {
        Assert.assertTrue(searchPage.editDestination.exists());
        Assert.assertTrue(searchPage.checkoutDayExpand.exists());
        Assert.assertTrue(searchPage.radioBusiness.exists());
        Assert.assertTrue(searchPage.radioLeisure.exists());
        //Assert.assertTrue(searchPage.radioHotels.exists());
        Assert.assertTrue(searchPage.selectAdultsNumber.exists());
        Assert.assertTrue(searchPage.buttonSubmit.exists());
    }
}
