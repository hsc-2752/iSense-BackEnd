package com.team18.backend.pojo;

import org.springframework.stereotype.Component;

/**
 * Sleep data component can be autowried
 */
@Component
public class CalculatedSleepData {
    private double deepSleep;
    private double paraSleep;
    private String dateIndex;

    public double getDeepSleep() {
        return deepSleep;
    }

    public void setDeepSleep(double deepSleep) {
        this.deepSleep = deepSleep;
    }

    public double getParaSleep() {
        return paraSleep;
    }

    public void setParaSleep(double paraSleep) {
        this.paraSleep = paraSleep;
    }

    public String getDateIndex() {
        return dateIndex;
    }

    public void setDateIndex(String dateIndex) {
        this.dateIndex = dateIndex;
    }
}
