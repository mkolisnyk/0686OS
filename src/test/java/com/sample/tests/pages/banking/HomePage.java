package com.sample.tests.pages.banking;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

public class HomePage extends Page {

    public HomePage(WebDriver driverValue) {
        super(driverValue);
        // TODO Auto-generated constructor stub
    }

    @FindBy(locator = "//button[text() = 'Bank Manager Login']")
    public Control buttonBankManagerLogin;
}
