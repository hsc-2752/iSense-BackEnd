package com.team18.backend.pojo;

import java.util.Date;

public class SleepData {



    private Boolean isAwaken;
    private Date startTime;
    private Date endTime;


    public SleepData(Date startTime, Date endTime, Boolean isAwaken) {

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Boolean getAwaken() {
        return isAwaken;
    }

    public void setAwaken(Boolean awaken) {
        isAwaken = awaken;
    }
//
//    public void setLight(Boolean light) {
//        this.light = light;
//    }
//
//    public void setNoise(Boolean noise) {
//        this.noise = noise;
//    }
//
//    public void setAirQuality(Boolean airQuality) {
//        this.airQuality = airQuality;
//    }
//
//    public void setTemperature(Boolean temperature) {
//        this.temperature = temperature;
//    }
//
//    public Boolean getLight() {
//        return light;
//    }
//
//    public Boolean getNoise() {
//        return noise;
//    }
//
//    public Boolean getAirQuality() {
//        return airQuality;
//    }
//
//    public Boolean getTemperature() {
//        return temperature;
//    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
