package com.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage{
    private final By inventoryItem = By.className("inventory_item");
    private final By sauceLabsBackpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public boolean isPageLoaded() {
        try {
            return waitForElementVisibility(inventoryItem).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void addBackpackToCart() {
        clickElement(sauceLabsBackpackAddToCartButton);
    }

    public String getShoppingCartCount() {
        return waitForElementVisibility(shoppingCartBadge).getText();
    }

    public void clickShoppingCart() {
        clickElement(shoppingCartLink);
    }


}
