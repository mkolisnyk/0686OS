package com.sample.framework.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectList extends Control {

    public SelectList(WebDriver driverValue, By locatorValue) {
        super(driverValue, locatorValue);
    }

    public Select getSelect() {
        return new Select(super.element());
    }
    public void selectByText(String value) {
        this.exists();
        this.getSelect().selectByVisibleText(value);
    }
}
