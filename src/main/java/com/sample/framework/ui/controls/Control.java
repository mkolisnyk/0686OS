package com.sample.framework.ui.controls;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sample.framework.Configuration;

public class Control {
    protected static final long TIMEOUT = Configuration.timeout();
    private WebDriver driver;
    private By locator;
    public Control(WebDriver driverValue, By locatorValue) {
        this.driver = driverValue;
        this.locator = locatorValue;
    }
    public WebDriver getDriver() {
        return driver;
    }
    public WebElement element() {
        return driver.findElement(locator);
    }
    public boolean exists(long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
    public boolean exists() {
        return exists(TIMEOUT);
    }
    public void click() {
        Assert.assertTrue(
            "Unable to find element: " + this.locator.toString(),
            exists());
        this.element().click();
    }
}
