package com.team18.backend.service;

import com.team18.backend.mapper.HealthMapper;
import com.team18.backend.pojo.SleepData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 计算深浅睡眠以及睡眠质量
 */

public class SleepService {
    private Boolean light;
    private Boolean noise;
    private Boolean airQuality;
    private Boolean temperature;



    private SleepData sleepData;

    public SleepService(SleepData sleepData) {
        this.sleepData = sleepData;
    }
    /**
     * 计算深浅睡眠时间，得到当前时间,调用mapper存入数据库
     */
    public Map<String,Double> calculateDeepTime() throws ParseException {

        double paraPer = Math.random()*0.2+0.55;
        double deepPer = 1 - paraPer;
        double total = calTotalTime();
        Map<String,Double> sleep = new HashMap<>();
        sleep.put("paraSleep",paraPer*total);
        sleep.put("deepSleep",deepPer*total);
   return sleep;
    }

    /**
     * 计算总睡眠时间
     */
    private long calTotalTime() throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Date startTime = simpleFormat.parse(sleepData.getStartTime());
        Date endTime = simpleFormat.parse(sleepData.getEndTime());
        return ((endTime.getTime() - startTime.getTime()) / (60 * 60 * 1000)) % 24;
    }



}
