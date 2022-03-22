package com.team18.backend.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HuData {
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
