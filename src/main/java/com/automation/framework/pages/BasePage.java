package com.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    private final int TIMEOUT_SECONDS=15;

    public BasePage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    protected WebElement waitForElementVisibility(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    protected WebElement waitForElementClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }


    protected boolean waitForText(By locator, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    protected void clickElement(By locator) {
        waitForElementClickable(locator).click(); // Use the new wait
    }

    protected void enterText(By locator, String text) {
        WebElement element = waitForElementVisibility(locator); // Use the new wait
        element.clear();
        element.sendKeys(text);
    }

    protected String getElementText(By locator) {
        return waitForElementVisibility(locator).getText();
    }

}
