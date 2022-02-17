package com.team18.backend.controller;

import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.HuData;
import com.team18.backend.pojo.SleepData;
import com.team18.backend.service.HealthDataService;
import com.team18.backend.service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class HealthController {


    /**
     * 注入service层
     */
    @Autowired
    private HealthDataService healthDataService;



    /**
     * Obtain all HR and BOS data from database, return it to
     */
    @RequestMapping(value = "/getHealthData",method = RequestMethod.GET)
    public HeartData bdMapper(){
        return healthDataService.getNewestData();
    }

    /**
     * 计算获得bmi
     * @param weight 前端传来的体重
     * @param height 前端传来的身高
     * @return 获得的bmi
     */
    @RequestMapping(value = "/getHealthData/assignBody",method = RequestMethod.POST)
    public double assignBody(@RequestParam("weight") double weight,
                         @RequestParam("height") double height){
     return healthDataService.getAndStoreBMI(weight,height);
    }
    /**
     * 获取所有bmi(数据类型为List，存放了bmi以及对应时间)
     */
    @RequestMapping(value = "/getHealthData/BMI",method = RequestMethod.GET)
    public List<HuData> bmiMapper(){
        return healthDataService.getAllBMI();
    }


    /**
     * 用于画图的数据获取，前端返回一个横坐标个数，
     * 根据横坐标个数决定获取几个十五分钟的平均值。
     * (血氧)
     */
    @RequestMapping(value =  "/getTime/bos",method = RequestMethod.POST)
    public List<Double> getBloodOxygen(@RequestParam("count")int count){
        return healthDataService.getManyAvgBOS(count);
    }

    /**
     * 获取前端发送的时间(心率),以及获取个数
     */
    @RequestMapping(value =  "/getTime/hr",method = RequestMethod.POST)
    public List<Double> getHeartRate(@RequestParam("count")int count){
        return healthDataService.getManyAvgHR(count);
    }

    /**
     * 获取睡眠时间
     */

    @Autowired
    private SleepData sleepData;
    @Autowired
    private SleepService sleepService;

    @RequestMapping(value = "/getTime/sleepTime",method = RequestMethod.POST)
    public void getSleepTime(@RequestParam("startTime")String startTime,
                             @RequestParam("endTime")String endTime,
                             @RequestParam("isAwaken")boolean isAwaken) throws ParseException {
        sleepData.setEndTime(endTime);
        sleepData.setStartTime(startTime);
        sleepData.setAwaken(isAwaken);

        sleepService.calculateDeepTime();
    }


}
