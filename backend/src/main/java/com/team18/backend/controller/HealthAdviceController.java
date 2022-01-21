package com.team18.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthAdviceController {
    /**
     * Obtain heart rate advice from service layer, return it to front end.
     * @return heart rate advice
     */
    @RequestMapping(value = "/Advice/HeartRate",method = RequestMethod.GET)
    public String heartRateAdvice(){
        return "heart rate advice";
    }

    /**
     * Obtain blood oxygen advice from service layer, return it to front end
     * @return blood oxygen advice
     */
    @RequestMapping(value = "/Advice/BloodOxygen",method = RequestMethod.GET)
    public String bloodOxygenAdvice(){
        return "blood oxygen advice";
    }

    @RequestMapping(value = "/Advice/BMI",method = RequestMethod.GET)
    public String bmiAdvice(){
        return "BMI advice";
    }
}
