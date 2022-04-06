package com.team18.backend.pojo;

/**
 * Primary health data component can be autowried
 */
public class HeartData {
    private double HeartRate;
    private double BOS;


    public double getHeartRate() {
        return HeartRate;
    }

    public void setHeartRate(double heartRate) {
        HeartRate = heartRate;
    }

    public double getBOS() {
        return BOS;
    }

    public void setBOS(double BOS) {
        this.BOS = BOS;
    }
}
