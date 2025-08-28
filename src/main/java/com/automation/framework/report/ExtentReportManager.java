package com.automation.framework.report;

import com.automation.framework.base.BaseTest;
import com.automation.framework.helper.SeleniumHelper;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtentReportManager.class.getName());
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Starting Test Suite: {}", context.getName());
        // Create reports directory
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test-Report-" + timestamp + ".html";
        String reportPath = System.getProperty("user.dir") + "/test-output/" + reportName;
        Path screenshotDir = Paths.get(System.getProperty("user.dir"), "test-output", "screenshots");
        if (!Files.exists(screenshotDir)) {
            try {
                Files.createDirectories(screenshotDir);
            } catch (IOException e) {
                LOGGER.error("Failed to create screenshots directory: {}", e.getMessage());
            }
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("My Automation Report");
        sparkReporter.config().setReportName("Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Tester Name");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }
    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Starting Test: {}", result.getMethod().getMethodName());
        ExtentTest test = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.set(test);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test Passed: {}", result.getMethod().getMethodName());
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("Test Failed: {}", result.getMethod().getMethodName());

        // Get the driver instance from BaseTest
        WebDriver driver = BaseTest.getDriver();

        if (driver != null) {
            // Call the screenshot helper
            String screenshotPath = SeleniumHelper.takeScreenshot(driver);
            if (screenshotPath != null) {
                String relativePath = "./screenshots/" + new File(screenshotPath).getName();
                extentTest.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            }
        } else {
            extentTest.get().fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn("Test Skipped: {}", result.getMethod().getMethodName());
        extentTest.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Finishing Test Suite: {}", context.getName());
        extent.flush();
    }

    // Helper method to get the current test from the listener
    public static ExtentTest getTest() {
        return extentTest.get();
    }
}
