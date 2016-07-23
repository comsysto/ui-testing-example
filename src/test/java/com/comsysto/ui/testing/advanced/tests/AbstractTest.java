package com.comsysto.ui.testing.advanced.tests;

import com.comsysto.ui.testing.advanced.management.FailureCapture;
import com.comsysto.ui.testing.advanced.management.TestListener;
import com.comsysto.ui.testing.advanced.management.WebDriverManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners({TestListener.class})
public class AbstractTest {

    protected String baseUrl = "http://localhost:8080";
    protected WebDriver webDriver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        ChromeDriverManager.getInstance().setup();
        webDriver = new ChromeDriver();
        WebDriverManager.setWebDriver(webDriver);
    }

    @AfterMethod(alwaysRun = true)
    public void shutdown(ITestResult result) {
        if (!result.isSuccess()) {

            System.out.println("Test failed on url: " + webDriver.getCurrentUrl());

            final FailureCapture failureCapture = new FailureCapture(result, webDriver);

            failureCapture.createScreenshot();
            failureCapture.createHtmlOutput();
        }
        WebDriverManager.quitWebDriver();
    }

}
