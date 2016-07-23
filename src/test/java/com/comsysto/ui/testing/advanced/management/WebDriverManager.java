package com.comsysto.ui.testing.advanced.management;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver webDriver) {
      WebDriverManager.webDriver.set(webDriver);

        webDriver.manage().timeouts().implicitlyWait(getTimeoutInSeconds(), TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(getTimeoutInSeconds(), TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(getTimeoutInSeconds(), TimeUnit.SECONDS);
    }

    private static long getTimeoutInSeconds() {
        return 5;
    }

    public static void quitWebDriver() {
        WebDriver driver = webDriver.get();
        if (driver != null) {
            driver.quit();
        }
    }
}
