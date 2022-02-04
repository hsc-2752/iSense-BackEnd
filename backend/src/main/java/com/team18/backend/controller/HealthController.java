package com.team18.backend.controller;

import com.team18.backend.mapper.HealthMapper;
import com.team18.backend.pojo.HealthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<HealthData> bdMapper(){
        return healthMapper.findAll();
    }


    /**
     * Returns the data from the front end to the Service layer for assignment
     */
    //TODO improve method body and return value
    @RequestMapping(value = "/getHealthData/assignBody",method = RequestMethod.POST)
    public void assignBody(@RequestParam("weight") double weight,
                         @RequestParam("height") double height){

    }
    /**
     * Get the BMI results from the Service layer and return to the front end
     */
    //TODO improve method body and return value based on service class
    @RequestMapping(value = "/getHealthData/BMI",method = RequestMethod.GET)
    public double bmiMapper(){
        double temp = 1.0;
        return temp;
    }

    /**
     * 获取用户是否睡眠中途被噪音和光照吵醒
     */
    //TODO 待商议
    @RequestMapping(value = "/getHealthData/assignFlag",method = RequestMethod.POST)
    public void assignFlag(@RequestParam("isAwakenByNoisy") boolean isAwakenByNoisy){
    }

    /**
     * Obtain sleep time from service layer, and return it to the front end
     * @return sleep time 返回类型暂时是字符串，service完成改成睡眠数据对象
     */
    //TODO change return type once service layer complete
    @RequestMapping(value = "/getHealthData/Sleep",method = RequestMethod.GET)
    @ResponseBody
    public String sleepTime(){
        return "deep sleep:5;paradox sleep:2";
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
     * 获取前端发送的时间(bmi)
     */
    @RequestMapping(value =  "/getTime/bmi",method = RequestMethod.POST)
    public String getBMI(@RequestParam("bmiTime")String bmiTime){
        return healthMapper.findBMI(bmiTime);
    }




}
