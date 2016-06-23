package com.comsysto.ui.testing.simple;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleBeautifulTest extends AbstractTest {

    @Test
    public void checkGoogle () throws InterruptedException {

        GoogleResultsPage googleResultsPage = new GoogleSearchPage(webDriver)
                .open()
                .searchFor("comsysto");

        Assert.assertEquals(googleResultsPage.getFirstLinkHref(), "https://comsysto.com/");
    }
}
