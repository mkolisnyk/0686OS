package com.sample.tests.junit.kdt.steps;

import org.junit.Assert;

import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

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
    @Then("^(?:I should see |)the \"(.*)\" field is available$")
    public Control verifyElementExists(String fieldName) throws Exception {
        Control control = Page.getCurrent().onPage(fieldName);
        Assert.assertNotNull("Unable to find the '" + fieldName + "' element on current page.", control);
        return control;
    }
    @When("^(?:I |)enter \"(.*)\" text into the \"(.*)\" field$")
    public void enterText(String text, String fieldName) throws Exception {
        Edit control = (Edit) verifyElementExists(fieldName);
        control.setText(text);
    }
    @Then("^(?:I should see |)the \"(.*)\" field contains the \"(.*)\" text$")
    public void verifyFieldText(String fieldName, String text) throws Exception {
        Control control = (Control) verifyElementExists(fieldName);
        String actualText = control.getText();
        Assert.assertTrue(
            String.format("The '%s' field has unexpected text. Expected: '%s', Actual: '%s'",
                fieldName,
                text,
                actualText
            ),
            text.equals(actualText) || actualText.contains(text));
    }

}
