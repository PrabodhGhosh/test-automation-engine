package com.automation.framework.base;

import com.automation.framework.config.ConfigManager;
import com.automation.framework.driver.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class.getName());
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod

    public void setUp() {
        LOGGER.info("Setting up WebDriver for the test...");
        boolean headless = Boolean.parseBoolean(ConfigManager.getProperty("headless"));

        try {
            driver.set(DriverFactory.getDriver("chrome", headless));
        } catch (Exception e) {
            LOGGER.error("The browser is not supported");
            throw new RuntimeException("Could not initialize WebDriver.", e);
        }

        getDriver().manage().window().maximize();
        getDriver().get(ConfigManager.getProperty("base_url"));
        LOGGER.info("Navigated to URL: {}", ConfigManager.getProperty("base_url"));
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            LOGGER.info("Closing the browser.");
            getDriver().quit();
            driver.remove();
        }


    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
