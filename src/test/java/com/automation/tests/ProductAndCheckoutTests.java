package com.automation.tests;

import com.automation.framework.base.BaseTest;
import com.automation.framework.pages.CheckoutStepOnePage;
import com.automation.framework.pages.InventoryPage;
import com.automation.framework.pages.LoginPage;
import com.automation.framework.pages.YourCartPage;
import com.automation.framework.pages.CheckoutCompletePage;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductAndCheckoutTests extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class.getName());
    @BeforeMethod
    public void loginBeforeTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Pre-condition failed: Inventory page did not load.");
    }

    @Test(description = "Verify adding a single item to the cart")
    public void testAddSingleItemToCart() {
        LOGGER.info("Starting testAddSingleItemToCart...");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addBackpackToCart();
        String cartCount = inventoryPage.getShoppingCartCount();
        Assert.assertEquals(cartCount, "1", "Cart count did not update to 1 after adding an item.");
        LOGGER.info("Item added to cart and count verified. Test passed.");
    }

    @Test(description = "Verify an end-to-end checkout process")
    public void testEndToEndCheckout() {
        LOGGER.info("Starting testEndToEndCheckout...");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addBackpackToCart();
        inventoryPage.clickShoppingCart();

        YourCartPage cartPage = new YourCartPage(getDriver());
        cartPage.clickCheckout();

        CheckoutStepOnePage checkoutOne = new CheckoutStepOnePage(getDriver());
        checkoutOne.enterInformationAndContinue("Tester", "Test", "12345");

        getDriver().findElement(By.id("finish")).click();

        CheckoutCompletePage checkoutComplete = new CheckoutCompletePage(getDriver());
        String completeMessage = checkoutComplete.getCompleteMessage();

        Assert.assertEquals(completeMessage, "Thank you for your order!", "Checkout complete message is incorrect.");
        LOGGER.info("End-to-end checkout successful. Test passed.");
    }


}
