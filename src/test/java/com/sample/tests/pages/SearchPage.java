package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Configuration;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.ui.controls.SelectList;

public class SearchPage extends Page {

    public Edit editDestination;
    public Control checkoutDayExpand;
    public Control checkoutDayToday;
    public Control radioLeisure;
    public Control radioBusiness;
    public Control radioHotels;
    public SelectList  selectAdultsNumber;
    public Control buttonSubmit;
    
    public SearchPage(WebDriver driverValue) {
        super(driverValue);
        editDestination = new Edit(this, By.id("ss"));
        checkoutDayExpand = new Control(this,
                By.cssSelector("i.sb-date-field__chevron.bicon-downchevron"));
        checkoutDayToday = new Control(this,
                By.xpath("//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]"));
        radioLeisure = new Control(this, By.xpath("(//input[@name='sb_travel_purpose'])[2]"));
        radioBusiness = new Control(this, By.xpath("(//input[@name='sb_travel_purpose'])[1]"));
        radioHotels = new Control(this, By.xpath("(//input[@name='nflt'])[2]"));
        selectAdultsNumber = new SelectList(this, By.id("group_adults"));
        buttonSubmit = new Control(this, By.xpath("//button[@type='submit']"));
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
