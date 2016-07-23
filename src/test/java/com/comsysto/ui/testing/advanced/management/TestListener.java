package com.comsysto.ui.testing.advanced.management;

import org.testng.*;

import java.util.Iterator;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onStart(ITestContext context) {

        super.onStart(context);

        // set the retry analyser for all methods of this context
        for (ITestNGMethod method : context.getAllTestMethods()) {
            method.setRetryAnalyzer(new RetryAnalyser());
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        super.onFinish(context);

        // go through all failed tests
        Iterator<ITestResult> listOfFailedTests = context.getFailedTests().getAllResults().iterator();
        while (listOfFailedTests.hasNext()) {

            final ITestResult failedTest = listOfFailedTests.next();
            ITestNGMethod method = failedTest.getMethod();

            // remove tests that failed several times and those which succeeded later on
            if (context.getFailedTests().getResults(method).size() > 1
                    || context.getPassedTests().getResults(method).size() > 0) {
                listOfFailedTests.remove();
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        super.onTestFailure(result);

        if (result.getMethod().getRetryAnalyzer() != null) {
            RetryAnalyser retryAnalyzer = (RetryAnalyser)result.getMethod().getRetryAnalyzer();

            if(retryAnalyzer.isRetryAvailable()) {
                result.setStatus(ITestResult.SKIP);
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
            Reporter.setCurrentTestResult(result);
        }
    }
}