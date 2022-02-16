package com.team18.backend.service;

import com.team18.backend.mapper.HealthMapper;
import com.team18.backend.pojo.SleepData;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    @Autowired
    HealthMapper healthMapper;
    /**
     * 计算深浅睡眠时间，得到当前时间,调用mapper存入数据库
     */
    public void calculateDeepTime() throws ParseException {

        double paraPer = Math.random()*0.2+0.55;
        double deepPer = 1 - paraPer;
        double total = calTotalTime();

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        healthMapper.storeSleep(deepPer*total,paraPer*total,dateFormat.format(date) );

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
