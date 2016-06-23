package com.comsysto.ui.testing.simple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {

    private static final String START_URL = "http://www.google.com";
    private static final String SEARCH_FIELD_NAME = "q";

    private WebDriver webDriver;

    public GoogleSearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public GoogleSearchPage open() {
        webDriver.get(START_URL);
        return this;
    }

    public GoogleResultsPage searchFor(String searchInput) {
        WebElement searchField = webDriver.findElement(By.name(SEARCH_FIELD_NAME));
        searchField.sendKeys(searchInput);
        searchField.submit();
        new WebDriverWait(webDriver, 10, 1000).until(ExpectedConditions.titleContains(searchInput));
        return new GoogleResultsPage(webDriver);
    }
}
