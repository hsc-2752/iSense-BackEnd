package com.team18.backend.pojo;


import org.springframework.stereotype.Component;

@Component
public class SleepData {

    private Boolean isAwaken;
    private String startTime;
    private String endTime;


//    public SleepData(String startTime, String endTime, Boolean isAwaken) {
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }

    public Boolean getAwaken() {
        return isAwaken;
    }

    public void setAwaken(Boolean awaken) {
        isAwaken = awaken;
    }


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
