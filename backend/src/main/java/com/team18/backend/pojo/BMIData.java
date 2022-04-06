package com.team18.backend.pojo;

import org.springframework.stereotype.Component;

/**
 * BMI data component, can be autowrited
 */
@Component
public class BMIData {
    private String timeIndex;
    private double bmi;

    public String getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(String timeIndex) {
        this.timeIndex = timeIndex;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}
