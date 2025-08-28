package com.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
            return waitForElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
