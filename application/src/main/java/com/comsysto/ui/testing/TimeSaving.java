package com.comsysto.ui.testing;

import java.io.Serializable;

public class TimeSaving implements Serializable {

    private Integer timePerUser;
    private Integer amountUsers;
    private Integer resultInSecondsPerDay;
    private Integer resultInSecondsPerYear;

    public TimeSaving(Integer timePerUser, Integer amountUsers) {
        this.timePerUser = timePerUser;
        this.amountUsers = amountUsers;
        calculateAndSetTimeSaving();
    }

    public void calculateAndSetTimeSaving () {
        resultInSecondsPerDay = this.timePerUser * this.amountUsers;
        resultInSecondsPerYear = resultInSecondsPerDay * 365;
    }

}
