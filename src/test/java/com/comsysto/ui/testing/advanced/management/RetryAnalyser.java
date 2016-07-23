package com.comsysto.ui.testing.advanced.management;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 *  If added to a test, it will retry the test as often as the according test configuration property says.
 */
public class RetryAnalyser implements IRetryAnalyzer {

    private int retryCount = 0;
    private int maxRetryCount;

    public RetryAnalyser() {

        maxRetryCount = 1;
    }

    public boolean retry(ITestResult result) {

        if (isRetryAvailable()) {
            retryCount++;
            return true;
        }
        return false;
    }

    public boolean isRetryAvailable() {
        return retryCount < maxRetryCount;
    }
}