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
     * Calculate BMI based on height and weight sent from the front end
     * @return BMI
     */
    //TODO improve method body and return value
    @RequestMapping(value = "/getBD/BMI",method = RequestMethod.POST)
    public double getBMIdata(@RequestParam("weight") double weight,
                         @RequestParam("height") double height){
        return weight/(height*height);
    }


}
