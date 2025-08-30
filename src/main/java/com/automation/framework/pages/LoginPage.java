package com.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void login(String username, String password) {
        enterText(usernameInput, username);
        enterText(passwordInput, password);
        clickElement(loginButton);
    }

    public boolean isErrorMessageDisplayed() {
        try {
            // Wait for up to 5 seconds for the error message to be visible
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        } catch (Exception e) {
            // Catch any exception and return false, as the element was not found in time
            return false;
        }
    }

}
