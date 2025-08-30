package com.automation.tests;

import com.automation.framework.base.BaseTest;
import com.automation.framework.helper.DataProviderUtil;
import com.automation.framework.helper.RetryAnalyzer;
import com.automation.framework.pages.InventoryPage;
import com.automation.framework.pages.LoginPage;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.framework.helper.JsonDataProvider;

public class LoginTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
    @Test(dataProvider = "jsonDataProvider", dataProviderClass = JsonDataProvider.class,
            description = "Verify login with various credentials from JSON file",
            retryAnalyzer = RetryAnalyzer.class)
    public void testLogin(JsonObject testData) {
        String username = testData.get("username").getAsString();
        String password = testData.get("password").getAsString();
        String expectedUrlPart = testData.get("expectedUrlPart").getAsString();
        String description = testData.get("description").getAsString();

        LOGGER.info("Starting login test with scenario: {}", description);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        // Assertions based on the expected outcome from the data
        if (expectedUrlPart.equals("inventory")) {
            Assert.assertTrue(getDriver().getCurrentUrl().contains(expectedUrlPart), "Valid login failed for user: " + username);
            LOGGER.info("Login successful for user: {}", username);
            // Verify a page element to be sure it loaded
            InventoryPage inventoryPage = new InventoryPage(getDriver());
            Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory page did not load after successful login.");
        } else {
            // Assert error message is displayed for invalid login
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed for invalid credentials.");
            LOGGER.info("Error message verified for user: {}", username);
        }
    }


}
