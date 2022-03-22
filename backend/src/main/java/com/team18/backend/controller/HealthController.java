package com.team18.backend.controller;

import com.team18.backend.pojo.CalculatedSleepData;
import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.HuData;
import com.team18.backend.pojo.SleepData;
import com.team18.backend.service.HealthDataService;
import com.team18.backend.service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class HealthController {


    /**
     * 注入service层
     */
    private HealthDataService healthDataService;
    private SleepData sleepData;
    private SleepService sleepService;


    @Autowired
    HealthController(HealthDataService healthDataService
    ,SleepData sleepData
    ,SleepService sleepService){
        this.healthDataService = healthDataService;
        this.sleepData = sleepData;
        this.sleepService = sleepService;
    }

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
     * 用于画图
     */
    @RequestMapping(value = "/getTime/BMI",method = RequestMethod.GET)
    public List<HuData> bmiMapper(){
        return healthDataService.getAllBMI();
    }

    /**
     * 用于画图的数据获取，前端返回一个横坐标个数，
     * 根据横坐标个数决定获取几个十五分钟的平均值。
     * (血氧)
     */
    @RequestMapping(value =  "/getTime/bos",method = RequestMethod.POST)
    public List<Double> getBloodOxygen(@RequestParam("count")int count) {
        return healthDataService.getManyAvgBOS(count);
    }

    /**
     * (心率),以及获取个数
     */
    @RequestMapping(value =  "/getTime/hr",method = RequestMethod.POST)
    public List<Double> getHeartRate(@RequestParam("count")int count){
        return healthDataService.getManyAvgHR(count);
    }

    /**
     * 用于画图的数据获取，前端返回一个横坐标个数，
     * 根据横坐标个数决定获取几个十五分钟的平均值。
     * (睡眠时间)
     */
    @RequestMapping(value = "/getTime/sleep", method = RequestMethod.POST)
    public List<CalculatedSleepData> getSleep(@RequestParam("count")int count){
        return healthDataService.getManySleep(count);
    }


   // @Autowired

  //  @Autowired
    /**
     * 获取睡眠时间,将深浅睡眠计算出来并存入数据库
     */
    @RequestMapping(value = "/getTime/sleepTime",method = RequestMethod.POST)
    public Map<String,Double> getSleepTime(@RequestParam("startTime")String startTime,
                                           @RequestParam("endTime")String endTime) throws ParseException {
        sleepData.setEndTime(endTime);
        sleepData.setStartTime(startTime);
       return sleepService.calculateTwoKindsSleepTime();

    }

}
