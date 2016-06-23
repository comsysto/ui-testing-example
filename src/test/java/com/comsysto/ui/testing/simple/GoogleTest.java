package com.comsysto.ui.testing.simple;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest {

    @Test
    public void checkGoogle () throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.google.com");
        WebElement searchField = webDriver.findElement(By.name("q"));
        searchField.sendKeys("comsysto");
        searchField.submit();
        new WebDriverWait(webDriver, 10, 1000).until(ExpectedConditions.titleContains("comsysto"));
        List<WebElement> titles = webDriver.findElements(By.cssSelector("h3.r a"));
        Assert.assertEquals(titles.get(0).getAttribute("href"), "https://comsysto.com/");
        webDriver.close();
        webDriver.quit();
    }
}
