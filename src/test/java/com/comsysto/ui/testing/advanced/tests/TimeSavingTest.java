package com.comsysto.ui.testing.advanced.tests;

import com.comsysto.ui.testing.advanced.management.TestListener;
import com.comsysto.ui.testing.advanced.pages.TimeSavingPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class TimeSavingTest extends AbstractTest {

    private TimeSavingPage timeSavingPage;
    private static int slowmotion = 10;

    @BeforeMethod
    public void setup() {
        super.setup();
        timeSavingPage = new TimeSavingPage(webDriver, baseUrl);
    }

    @Test
    public void render() {
        timeSavingPage.open()
                .enterTimePerUser(5)
                .enterAmountUsers(100)
                .clickCalculate()
                .assertResult(500, 182500);
    }

    @Test
    public void render_slowmotion() {
        int slowmotionThisTime = slowmotion;
        slowmotion = 2;

        timeSavingPage.openWithSlowmotion(slowmotionThisTime);
        timeSavingPage
                .enterTimePerUser(5)
                .enterAmountUsers(100)
                .clickCalculate()
                .assertResult(500, 182500);


    }

}
