package com.automation.framework.helper;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeleniumHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumHelper.class.getName());
    public static String takeScreenshot(WebDriver driver) {
        String filePath = null;
        try {
            // Generate a unique file name
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String fileName = "screenshot_" + timestamp + ".png";
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Define the destination path
            File destinationPath = new File("test-output/screenshots/" + fileName);
            FileHandler.copy(screenshotFile, destinationPath);

            filePath = destinationPath.getAbsolutePath();
            LOGGER.info("Screenshot saved successfully at: {}", filePath);

        } catch (IOException e) {
            LOGGER.error("ERROR: Failed to capture screenshot. {}", e.getMessage());
        } catch (ClassCastException e) {
            LOGGER.error("ERROR: Driver does not support taking screenshots. {}", e.getMessage());
        }
        return filePath;
    }
}
