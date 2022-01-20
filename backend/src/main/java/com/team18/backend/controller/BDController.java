package com.team18.backend.controller;

import com.team18.backend.mapper.BDataMapper;
import com.team18.backend.pojo.BodyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BDController {

    @Autowired
    BDataMapper bDataMapper;

    /**
     * Obtain all body data
     */
    @RequestMapping(value = "/getBD",method = RequestMethod.GET)
    public List<BodyData> bdMapper(){
        return bDataMapper.findAll();
    }

    /**
     * Obtain heart rate
     */
    @RequestMapping(value = "/getBD/HeartRate",method = RequestMethod.GET)
    public String heartMapper(){
        return bDataMapper.findHeartRate();
    }
    /**
     * Obtain blood oxygen
     */
    @RequestMapping(value = "/getBD/BloodOxygen",method = RequestMethod.GET)
    public String oxygenMapper(){
        return bDataMapper.findBloodOxygen();
    }

    /**
     * Returns the data from the front end to the Service layer for assignment
     */
    //TODO improve method body and return value
    @RequestMapping(value = "/getBD/assignBody",method = RequestMethod.POST)
    public void assignBody(@RequestParam("weight") double weight,
                         @RequestParam("height") double height){

    }
    /**
     * Get the BMI results from the Service layer and return to the front end
     */
    //TODO improve method body and return value based on service class
    @RequestMapping(value = "/getBD/BMI",method = RequestMethod.GET)
    public double bmiMapper(){
        double temp = 1.0;
        return temp;
    }

}
