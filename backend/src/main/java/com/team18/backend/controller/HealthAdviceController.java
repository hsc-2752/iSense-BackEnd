package com.team18.backend.controller;

import com.team18.backend.service.HealthAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthAdviceController {

    private HealthAdviceService adviceService;
    @Autowired
    HealthAdviceController (HealthAdviceService adviceService){
        this.adviceService = adviceService;
    }
    /**
     * 获取有关心率的建议
     * @return heart rate advice
     */
    @RequestMapping(value = "/Advice/HeartRate",method = RequestMethod.GET)
    public String heartRateAdvice(){
        return adviceService.getHRAdvice();
    }

    /**
     * 获取有关血氧建议
     * @return blood oxygen advice
     */
    @RequestMapping(value = "/Advice/BloodOxygen",method = RequestMethod.GET)
    public String bloodOxygenAdvice(){
        return adviceService.getBOSAdvice();
    }

    /**
     * 获取有关最新一条bmi的建议
     * @return bmi advice
     */
    @RequestMapping(value = "/Advice/BMI",method = RequestMethod.GET)
    public String bmiAdvice(){
        return adviceService.getBMIAdvice();
    }
}
