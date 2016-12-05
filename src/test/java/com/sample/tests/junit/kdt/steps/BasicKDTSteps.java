package com.sample.tests.junit.kdt.steps;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import com.sample.framework.Context;
import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.ui.controls.TableView;

import cucumber.api.DataTable;
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
    public void enterValue(String text, String fieldName) throws Exception {
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
    @When("^(?:I |)accept the alert message$")
    public void acceptAlert() {
        Driver.current().switchTo().alert().accept();
    }
    @Then("^(?:I should see |)the \"(.*)\" text is shown$")
    public void verifyTextPresent(String text) throws Exception {
        Assert.assertTrue("Unable to find text: '" + text + "'", Page.getCurrent().isTextPresent(text));
    }
    @Then("^(?:I should see |)the following fields are shown:$")
    public void verifyMultipleFieldsAvailability(List<String> elements) throws Exception {
        for (String element : elements) {
            verifyElementExists(element);
        }
    }
    @Then("^(?:I should see |)the following labels are shown:$")
    public void verifyMultipleLabelsAvailability(List<String> elements) throws Exception {
        for (String element : elements) {
            verifyTextPresent(element);
        }
    }
    @When("^(?:I |)populate current page with the following data:$")
    public void populatePageWithData(DataTable data) throws Throwable {
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            enterValue(row.get("Value"), row.get("Field"));
        }
    }
    @Then("^(?:I should see |)the page contains the following data:$")
    public void pageContainsData(DataTable data) throws Throwable {
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            verifyFieldText(row.get("Field"), row.get("Value"));
        }
    }
    @Then("^(?:I should see |)the \"(.*)\" (?:list|table) is (|not )empty$")
    public void verifyListEmptyState(String list, String emptyState) throws Throwable {
        boolean empty = emptyState.trim().equals("");
        TableView control = (TableView) verifyElementExists(list);
        if (empty) {
            Assert.assertTrue("The '"+ list + "' element is not empty", control.isEmpty());
        } else {
            Assert.assertTrue("The '"+ list + "' element is empty", control.isNotEmpty());
        }
    }
    @Then("^(?:I should see |)the (first|last) (?:row|item) of the \"(.*)\" (?:list|table) contains the following data:$")
    public void verifyListRowData(String firstLast, String list, DataTable data) throws Throwable {
        int index = 0;
        TableView control = (TableView) verifyElementExists(list);
        if (firstLast.equals("last")) {
            index = control.getItemsCount() - 1;
        }
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            for (Entry<String, String> entry : row.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Assert.assertEquals(String.format("The %s row element '%s' has unexpected value", firstLast, key),
                        value, control.getSubItem(key, index).getText());
            }
        }
    }
    @When("^(?:I |)(?:click|tap) on the (first|last) \"(.*)\" element of the \"(.*)\" (?:list|table)$")
    public void clickOnSubItem(String firstLast, String item, String list) throws Exception {
        int index = 0;
        TableView control = (TableView) verifyElementExists(list);
        if (firstLast.equals("last")) {
            index = control.getItemsCount() - 1;
        }
        control.getSubItem(item, index).click();
    }
    @When("^(?:I |)note the \"(.*)\" (?:table|list) (?:row|item) count as \"(.*)\"")
    public void noteRowCountAs(String list, String varName) throws Exception {
        TableView control = (TableView) verifyElementExists(list);
        Context.put(varName, control.getItemsCount());
    }
    @Then("^(?:I should see |)the \"(.*)\" (?:table|list) has \"(.*)\" (?:items|rows)$")
    public void verifyTableRowCount(String list, String countValue) throws Exception {
        TableView control = (TableView) verifyElementExists(list);
        int actualCount = control.getItemsCount();
        String expectedCountValue = countValue;
        for (String key : Context.variables()) {
            expectedCountValue = expectedCountValue.replaceAll(key, Context.get(key).toString());
        }
        int expectedCount = Integer.parseInt(expectedCountValue);
        Assert.assertEquals("Unexpected row count for the '" + list + "' table", expectedCount, actualCount);
    }
}
