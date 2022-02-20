package com.team18.backend.pojo;


import org.springframework.stereotype.Component;

@Component
public class SleepData {

    private String startTime;
    private String endTime;


    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
