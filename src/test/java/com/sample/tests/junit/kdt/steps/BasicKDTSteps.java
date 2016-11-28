package com.sample.tests.junit.kdt.steps;

import org.junit.Assert;

import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BasicKDTSteps {

    public BasicKDTSteps() {
        // TODO Auto-generated constructor stub
    }

    @Given("^the banking application has been started$")
    public void startBankingApplication() {
        Driver.current().get("http://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }
    @Given("^I am on the \"(.*)\" (?:page|screen)$")
    @When("^(?:I |)go to the \"(.*)\" (?:page|screen)$")
    public void navigateToPage(String name) throws Exception {
        Page target = Page.screen(name);
        Assert.assertNotNull("Unable to find the '" + name + "' page.", target);
        target.navigate();
        verifyCurrentPage(name);
    }
    @When("^(?:I |)(?:click|tap) on the \"(.*)\" (?:button|element|control)$")
    public void clickOnTheButton(String name) throws Exception {
        Control control = Page.getCurrent().onPage(name);
        Assert.assertNotNull("Unable to find the '" + name + "' element on current page.", control);
        control.click();
    }
    @Then("^I should see the \"(.*)\" (?:page|screen)$")
    public void verifyCurrentPage(String name) throws Exception {
        Page target = Page.screen(name);
        Assert.assertTrue("The '" + name + "' screen is not current", target.isCurrent());
        Page.setCurrent(target);
    }
}
