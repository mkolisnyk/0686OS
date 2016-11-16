package com.sample.tests.pages.banking;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

public class BankManagerCommonPage extends Page {

    public BankManagerCommonPage(WebDriver driverValue) {
        super(driverValue);
        // TODO Auto-generated constructor stub
    }

    @FindBy(locator = "//button[contains(text(),'Add Customer')]")
    public Control buttonAddCustomer;
    @FindBy(locator = "//button[contains(text(),'Open Account')]")
    public Control buttonOpenAccount;
    @FindBy(locator = "//button[contains(text(),'Customers')]")
    public Control buttonCustomers;
}
