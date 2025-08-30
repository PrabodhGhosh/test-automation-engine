package com.automation.framework.helper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WaitHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitHelper.class);
    private static final int DEFAULT_TIMEOUT_SECONDS = 15;
    private static final int DEFAULT_POLLING_MILLISECONDS = 500;

    /**
     * Waits for an element to be present and visible using a FluentWait.
     * @param driver The WebDriver instance.
     * @param locator The By locator of the element.
     * @return The WebElement once it is found.
     */
    public static WebElement fluentWait(WebDriver driver, By locator) {
        LOGGER.info("Starting Fluent Wait for element with locator: {}", locator);
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                    .pollingEvery(Duration.ofMillis(DEFAULT_POLLING_MILLISECONDS))
                    .ignoring(NoSuchElementException.class);

            return wait.until(d -> {
                LOGGER.debug("Polling for element with locator: {}", locator);
                return d.findElement(locator);
            });
        } catch (TimeoutException e) {
            LOGGER.error("Timed out waiting for element with locator: {}", locator);
            throw new TimeoutException("Timed out waiting for element: " + locator, e);
        }
    }
}