package com.comsysto.ui.testing.simple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleResultsPage {

    private static final String RESULT_LINKS_CSS_SELECTOR = "h3.r a";
    private WebDriver webDriver;

    public GoogleResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getFirstLinkHref() {
        List<WebElement> resultLinks = webDriver.findElements(By.cssSelector(RESULT_LINKS_CSS_SELECTOR));
        return resultLinks.get(0).getAttribute("href");
    }
}
