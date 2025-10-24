package com.automation.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class DriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class.getName());
    // --- Helper to create browser capabilities (used by both local and remote) ---
    private static Capabilities getBrowserCapabilities(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                // Common options for both local and remote execution
                if(headless)
                {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                }
                if (headless) {
                    chromeOptions.addArguments("--disable-gpu");
                }
                return chromeOptions;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                return firefoxOptions;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("-headless");
                }
                return edgeOptions;

            default:
                LOGGER.warn("Unsupported browser: {}. Defaulting to Chrome capabilities.", browser);
                return getBrowserCapabilities("chrome", headless); // Default to Chrome capabilities
        }
    }

    // --- Main method updated to accept execution parameters ---
    public static WebDriver getDriver(String browser, boolean headless, String executionMode, String gridUrl) {
        WebDriver driver = null;
        Capabilities capabilities = getBrowserCapabilities(browser, headless);

        LOGGER.info("Starting driver in {} mode for browser: {}", executionMode.toUpperCase(), browser);

        if ("remote".equalsIgnoreCase(executionMode)) {
            // --- RemoteWebDriver Setup ---
            try {
                driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
                LOGGER.info("Successfully connected to Selenium Grid at {}", gridUrl);
            } catch (Exception e) {
                LOGGER.error("Failed to start RemoteWebDriver on {}. Error: {}", gridUrl, e.getMessage());
                // Optional: Fallback to local if remote fails, or throw a runtime exception.
                throw new RuntimeException("Could not connect to Selenium Grid: " + gridUrl, e);
            }

        } else {
            // --- Local Driver Setup ---

            // Check browser against the capabilities object type for local initialization
            if (capabilities instanceof ChromeOptions) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver((ChromeOptions) capabilities);
            } else if (capabilities instanceof FirefoxOptions) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver((FirefoxOptions) capabilities);
            } else if (capabilities instanceof EdgeOptions) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver((EdgeOptions) capabilities);
            } else {
                LOGGER.error("Local driver initialization failed for browser: {}", browser);
                // Fallback to basic local Chrome if all else fails
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }
        return driver;
    }
}
