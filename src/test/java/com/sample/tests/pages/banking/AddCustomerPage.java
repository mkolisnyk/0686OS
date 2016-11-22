package com.sample.tests.pages.banking;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

public class AddCustomerPage extends BankManagerCommonPage {

    public AddCustomerPage(WebDriver driverValue) {
        super(driverValue);
        // TODO Auto-generated constructor stub
    }

    @FindBy(locator = "//input[@type='text']")
    public Edit editFirstName;
    @FindBy(locator = "xpath=(//input[@type='text'])[2]")
    public Edit editLastName;
    @FindBy(locator = "xpath=(//input[@type='text'])[3]")
    public Edit editPostCode;
    
    @FindBy(locator = "//button[text() = 'Add Customer']")
    public Control buttonSubmit;
    
    @Override
    public Page navigate() throws Exception {
        return this.buttonAddCustomer.clickAndWaitFor(this.getClass());
    }
}
