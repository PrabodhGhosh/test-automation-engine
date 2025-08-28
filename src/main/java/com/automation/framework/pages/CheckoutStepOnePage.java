package com.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);

    }

    public void enterInformationAndContinue(String firstName, String lastName, String postalCode) {
        enterText(firstNameInput, firstName);
        enterText(lastNameInput, lastName);
        enterText(postalCodeInput, postalCode);
        clickElement(continueButton);
    }

}
