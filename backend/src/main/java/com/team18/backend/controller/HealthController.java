package com.team18.backend.controller;

import com.team18.backend.pojo.CalculatedSleepData;
import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.BMIData;
import com.team18.backend.pojo.SleepData;
import com.team18.backend.service.HealthDataService;
import com.team18.backend.service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * This controller controls controls all connection about health data.
 */
@RestController
public class HealthController {


    /**
     * autowried to service
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
     * calculate BMI
     * @param weight weight from client
     * @param height height from client
     * @return BMI value
     */
    @RequestMapping(value = "/getHealthData/assignBody",method = RequestMethod.POST)
    public double assignBody(@RequestParam("weight") double weight,
                         @RequestParam("height") double height){
     return healthDataService.getAndStoreBMI(weight,height);
    }
    /**
     * Retrieve all BMIs (data type: List, stored BMI and corresponding time)
     * For drawing chart
     */
    @RequestMapping(value = "/getTime/BMI",method = RequestMethod.GET)
    public List<BMIData> bmiMapper(){
        return healthDataService.getAllBMI();
    }

    /**
     * For drawing data acquisition, the front end returns a number of abscissa,
     * Take a number of 15-minute averages based on the number of horizontal coordinates.
     * (Blood oxygen)
     */
    @RequestMapping(value =  "/getTime/bos",method = RequestMethod.POST)
    public List<Double> getBloodOxygen(@RequestParam("count")int count) {
        return healthDataService.getManyAvgBOS(count);
    }

    /**
     * (heart rate),and number of HR client needs
     */
    @RequestMapping(value =  "/getTime/hr",method = RequestMethod.POST)
    public List<Double> getHeartRate(@RequestParam("count")int count){
        return healthDataService.getManyAvgHR(count);
    }

    /**
     * For drawing data acquisition, the front end returns a number of abscissa,
     * Take a number of 15-minute averages based on the number of horizontal coordinates.
     * (sleep time)
     */
    @RequestMapping(value = "/getTime/sleep", method = RequestMethod.POST)
    public List<CalculatedSleepData> getSleep(@RequestParam("count")int count){
        return healthDataService.getManySleep(count);
    }

    /**
     * Get sleep time, calculate the depth of sleep and store in the database
     */
    @RequestMapping(value = "/getTime/sleepTime",method = RequestMethod.POST)
    public Map<String,Double> getSleepTime(@RequestParam("startTime")String startTime,
                                           @RequestParam("endTime")String endTime) throws ParseException {
        sleepData.setEndTime(endTime);
        sleepData.setStartTime(startTime);
       return sleepService.calculateTwoKindsSleepTime();

    }

}
