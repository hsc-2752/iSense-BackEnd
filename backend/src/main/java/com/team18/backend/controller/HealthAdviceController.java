package com.team18.backend.controller;

import com.team18.backend.service.HealthAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller controls all connection about health advice.
 */
@RestController
public class HealthAdviceController {

     HealthAdviceService adviceService;
    @Autowired
    HealthAdviceController (HealthAdviceService adviceService){
        this.adviceService = adviceService;
    }
    /**
     * Get advice on heart rate
     * @return heart rate advice
     */
    @RequestMapping(value = "/Advice/HeartRate",method = RequestMethod.GET)
    public String heartRateAdvice(){
        return adviceService.getHRAdvice();
    }

    /**
     * Get advice on blood oxygen
     * @return blood oxygen advice
     */
    @RequestMapping(value = "/Advice/BloodOxygen",method = RequestMethod.GET)
    public String bloodOxygenAdvice(){
        return adviceService.getBOSAdvice();
    }

    /**
     * Get the latest advice on BMI
     * @return bmi advice
     */
    @RequestMapping(value = "/Advice/BMI",method = RequestMethod.GET)
    public String bmiAdvice(){
        return adviceService.getBMIAdvice();
    }
}
