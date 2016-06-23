package com.comsysto.ui.testing.simple;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class AbstractTest {

    protected WebDriver webDriver;

    @BeforeClass
    public void beforeClass () {
        ChromeDriverManager.getInstance().setup();
        webDriver = new ChromeDriver();
    }

    @AfterClass
    public void shutdown() {
        webDriver.close();
        webDriver.quit();
    }
}
