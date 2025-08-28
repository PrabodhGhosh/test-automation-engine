package com.automation.tests;

import com.automation.framework.base.BaseTest;
import com.automation.framework.helper.DataProviderUtil;
import com.automation.framework.pages.InventoryPage;
import com.automation.framework.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
    @Test(dataProvider = "csvDataProvider", dataProviderClass = DataProviderUtil.class,
            description = "Verify login with various credentials from CSV file")
    public void testLogin(String username, String password, String expectedUrlPart, String description) {
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
