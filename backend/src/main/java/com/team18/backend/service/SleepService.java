package com.team18.backend.service;

import com.team18.backend.pojo.SleepData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Random;

/**
 * 计算深浅睡眠以及睡眠质量
 */
public class SleepService {
    private Boolean light;
    private Boolean noise;
    private Boolean airQuality;
    private Boolean temperature;


    @Autowired
    private SleepData sleepData;
    /**
     * 计算深浅睡眠时间，得到当前时间,调用mapper存入数据库
     */
    public void calculateDeepTime() {

        double paraPer = Math.random()*0.2+0.55;
        double deepPer = 1 - paraPer;
        double total = calTotalTime();

    }

    /**
     * 计算总睡眠时间
     */
    private double calTotalTime() {
        Date startTime = sleepData.getStartTime();
        Date endTime = sleepData.getEndTime();
        return (double) ((endTime.getTime() - startTime.getTime()) / (60 * 60 * 1000)) % 24;
    }



}
