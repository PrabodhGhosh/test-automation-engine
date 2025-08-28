package com.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourCartPage extends BasePage{
    private final By checkoutButton = By.id("checkout");

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckout() {
        clickElement(checkoutButton);
    }


}
