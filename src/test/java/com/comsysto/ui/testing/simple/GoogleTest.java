package com.comsysto.ui.testing.simple;

import io.github.bonigarcia.wdm.MarionetteDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest {

    @Test
    public void checkGoogle () {
        WebDriver webDriver = new MarionetteDriver();
        webDriver.get("http://www.google.com");
        WebElement searchField = webDriver.findElement(By.name("q"));
        searchField.sendKeys("comsysto");
        searchField.submit();
        Assert.assertEquals(webDriver.getTitle(), "comsysto");
    }
}
