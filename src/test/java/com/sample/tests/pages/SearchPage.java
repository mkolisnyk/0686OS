package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.Configuration;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.ui.controls.SelectList;

public class SearchPage extends Page {

    @FindBy(locator = "ss")
    public Edit editDestination;
    
    @FindBy(locator = "css=i.sb-date-field__chevron.bicon-downchevron")
    public Control checkoutDayExpand;
    
    @FindBy(locator = "//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]")
    public Control checkoutDayToday;
    
    @FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[2]")
    public Control radioLeisure;
    
    @FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[1]")
    public Control radioBusiness;
    
    @FindBy(locator = "xpath=(//input[@name='nflt'])[2]")
    public Control radioHotels;
    
    @FindBy(locator = "group_adults")
    public SelectList  selectAdultsNumber;

    @FindBy(locator = "//button[@type='submit']")
    public Control buttonSubmit;
    
    public SearchPage(WebDriver driverValue) {
        super(driverValue);
    }

    @Override
    public Page navigate() {
        String baseUrl = Configuration.get("url");
        this.getDriver().get(baseUrl);
        return this;
    }
    public void checkInToday() {
        checkoutDayExpand.click();
        checkoutDayToday.click();
    }
    public void selectTravelFor(boolean isLeisure) {
        if (isLeisure) {
            radioLeisure.click();
        } else {
            radioBusiness.click();
        }
    }
}
