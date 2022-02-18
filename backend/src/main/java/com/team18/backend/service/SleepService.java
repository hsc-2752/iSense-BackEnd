package com.team18.backend.service;

import com.team18.backend.mapper.HealthMapper;
import com.team18.backend.pojo.EnvironmentData;
import com.team18.backend.pojo.SleepData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 计算深浅睡眠以及睡眠质量
 */
@Service
public class SleepService {
    private String light;
    private String noise;
    private String airQuality;
    private String temperature;

//TODO 修改这些标准值
    static final double STANDARD_LIGHT = 10;
    static final double STANDARD_NOISE = 30;
    static final double  STANDARD_AQ = 30;
    static final double  STANDARD_TEMP1 = 15;
    static final double  STANDARD_TEMP2 = 25;

    @Autowired
    private SleepData sleepData;

    @Autowired
    private HealthMapper healthMapper;


    public SleepService(SleepData sleepData) {
        this.sleepData = sleepData;
    }

    /**
     * 计算深浅睡眠时间，得到当前时间,调用mapper存入数据库
     */
    @ExceptionHandler
    public Map<String,Double> calculateTwoKindsSleepTime() throws ParseException {

        double paraPer = Math.random()*0.2+0.55;
        double deepPer = 1 - paraPer;
        double total = calTotalTime();

        double deepTime = deepPer*total;
        double paraTime = paraPer*total;
        Map<String,Double> sleepMap = new HashMap<>(2);
        sleepMap.put("deepSleep",deepTime);
        sleepMap.put("paraSleep",paraTime);

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        try{
            healthMapper.storeSleep(deepTime,paraTime, dateFormat.format(date));
        }catch (DataAccessException e) {
            healthMapper.updateSleep(deepTime,paraTime,dateFormat.format(date));
        }
        return sleepMap;
    }



    /**
     * 根据睡眠期间环境中温度，噪声，光照，空气质量和用户输入的是否有醒来
     * 来对睡眠时环境各项因素指标做评估
     */
    @Autowired
    private EVDataService evDataService;

    public String evaluate() {

        for (EnvironmentData e:evDataService.getSleepEVDataList(sleepData.getStartTime(),sleepData.getEndTime())
        ) {
            if (e.getBrightness() > STANDARD_LIGHT){
                light = "1";
            }
            if (e.getHCHO() > STANDARD_AQ) {
                airQuality = "1";
            }
            if (e.getTemp() > STANDARD_TEMP1) {
                temperature = "1";
            }else if (e.getTemp() < STANDARD_TEMP2) {
                temperature = "2";
            }
            if (e.getVoice() > STANDARD_NOISE) {
                noise = "1";
            }
        }
        return light+airQuality+temperature+noise;
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
