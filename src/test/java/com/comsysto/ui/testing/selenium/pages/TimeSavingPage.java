package com.comsysto.ui.testing.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TimeSavingPage {

    private final WebDriver webDriver;
    private final String baseUrl;


    public TimeSavingPage(WebDriver webDriver, String baseUrl) {

        this.webDriver = webDriver;
        this.baseUrl = baseUrl;
    }

   public TimeSavingPage open() {
        webDriver.get(baseUrl);
        return this;
    }

    public TimeSavingPage openWithSlowmotion(int seconds) {
        webDriver.get(baseUrl + "?slowmotion=" + seconds);
        return this;
    }

    public TimeSavingPage enterTimePerUser(int timePerUser) {
        final WebElement element = webDriver.findElement(By.xpath("//input[@data-description='time-per-user']"));
        element.clear();
        element.sendKeys(String.valueOf(timePerUser));
        return this;
    }

    public TimeSavingPage enterAmountUsers(int amountUsers) {
        final WebElement element = webDriver.findElement(By.xpath("//input[@data-description='amount-users']"));
        element.clear();
        element.sendKeys(String.valueOf(amountUsers));
        return this;
    }

    public TimeSavingPage clickCalculate() {
        webDriver.findElement(By.xpath("//input[@data-description='calculate']")).click();
        return this;
    }

    public TimeSavingPage assertResult(int secondsPerDay, int secondsPerYear) {
        final String result = webDriver.findElement(By.xpath("//*[@data-description='results']")).getText();
        Assert.assertEquals(result, "Wow!\nYou save the world " + secondsPerDay + " seconds each day!\n" +
                "That's " + secondsPerYear + " seconds a year!");
        return this;
    }
}