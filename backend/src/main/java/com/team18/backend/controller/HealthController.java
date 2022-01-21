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
     * Obtain sleep time from service layer, and return it to the front end
     * @return sleep time
     */
    //TODO change return type once service layer complete
   @RequestMapping(value = "/getHealthData/Sleep",method = RequestMethod.GET)
   @ResponseBody
   public String sleepTime(){
        return "deep sleep:5;paradox sleep:2";
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

}
