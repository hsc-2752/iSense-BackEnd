package com.team18.backend.controller;

import com.team18.backend.mapper.HealthMapper;
import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.HuData;
import com.team18.backend.pojo.SleepData;
import com.team18.backend.service.HuDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class HealthController {

    @Autowired
    HealthMapper healthMapper;

    /**
     * Obtain all HR and BOS data from database, return it to
     */
    @RequestMapping(value = "/getHealthData",method = RequestMethod.GET)
    @ResponseBody
    public List<HeartData> bdMapper(){
        return healthMapper.findAll();
    }


    /**
     * Returns the data from the front end to the Service layer for assignment
     */
    //TODO improve method body and return value
    @Autowired
    private HuDataService huDataService;

    @RequestMapping(value = "/getHealthData/assignBody",method = RequestMethod.POST)
    public double assignBody(@RequestParam("weight") double weight,
                         @RequestParam("height") double height){
      double bmi =  huDataService.BMICalculator(height,weight);
      Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = format.format(date);
      healthMapper.storeBMI(bmi,time);
      return bmi;
    }
    /**
     * Get the BMI results from the Service layer and return to the front end
     */
    //TODO improve method body and return value based on service class
    @RequestMapping(value = "/getHealthData/BMI",method = RequestMethod.GET)
    public List<HuData> bmiMapper(){
        return healthMapper.getBMI();
    }



    /**
     * Obtain sleep time from service layer, and return it to the front end
     * @return sleep time 返回类型暂时是字符串，service完成改成睡眠数据对象
     */
    //TODO change return type once service layer complete
    @RequestMapping(value = "/getHealthData/Sleep",method = RequestMethod.POST)
    public void sleepTime(@RequestParam("startTime")Date startTime,
                            @RequestParam("endTime")Date endTime){

    }

    /**
     * 获取前端发送的时间(血氧)
     */
    @RequestMapping(value =  "/getTime/bos",method = RequestMethod.POST)
    public String getBloodOxygen(@RequestParam("bosTime")String bosTime){
        return healthMapper.findBOS(bosTime);
    }
    /**
     * 获取前端发送的时间(心率)
     */
    @RequestMapping(value =  "/getTime/hr",method = RequestMethod.POST)
    public String getHeartRate(@RequestParam("hrTime")String hrTime){
        return healthMapper.findHR(hrTime);
    }

    /**
     * 获取睡眠时间
     */
    @RequestMapping(value = "/getTime/sleepTime",method = RequestMethod.POST)
    public void getSleepTime(@RequestParam("startTime")Date startTime,
                             @RequestParam("endTime")Date endTime,
                             @RequestParam("isAwaken")boolean isAwaken){
        SleepData sleepData = new SleepData(startTime, endTime, isAwaken);

    }


}
