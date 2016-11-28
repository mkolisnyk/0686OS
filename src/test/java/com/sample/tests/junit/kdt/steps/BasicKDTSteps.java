package com.sample.tests.junit.kdt.steps;

import com.sample.framework.Driver;

import cucumber.api.java.en.Given;

public class BasicKDTSteps {

    public BasicKDTSteps() {
        // TODO Auto-generated constructor stub
    }

    @Given("^the banking application has been started$")
    public void startBankingApplication() {
        Driver.current().get("http://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }
}
