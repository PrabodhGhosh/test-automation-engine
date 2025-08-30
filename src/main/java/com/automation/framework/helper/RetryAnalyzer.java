package com.automation.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryAnalyzer.class);
    private static final int MAX_RETRY_COUNT = 2; // Number of times to retry
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            LOGGER.info("Retrying test '{}' for the {} time.", result.getName(), retryCount);
            return true;
        }
        return false;
    }

}