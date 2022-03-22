package com.team18.backend.controller;

import com.team18.backend.service.EnvAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EVAdviceController {

     EnvAdviceService adviceService;
    @Autowired
    EVAdviceController(EnvAdviceService adviceService){
        this.adviceService = adviceService;
    }

    /**
     * Obtain heart rate advice from service layer, return it to the front end
     * @return temperature and humidity advice
     */
    @RequestMapping(value = "/Advice/TemAndHum",method = RequestMethod.GET)
    public String temAndHumAdvice(){

        return adviceService.getTemAndHumAdvice();
    }

    /**
     * Obtain pressure advice from service layer, return it to the front end
     * @return indoor pressure advice
     */
    @RequestMapping(value = "/Advice/Pressure",method = RequestMethod.GET)
    public String pressureAdvice(){
        return adviceService.getPressureAdvice();
    }
    /**
     * Obtain noise advice from service layer, return it to the front end
     * @return indoor Noise advice
     */
    @RequestMapping(value = "/Advice/Noise",method = RequestMethod.GET)
    public String noiseAdvice(){
        return adviceService.getNoiseAdvice();
    }

    /**
     * Obtain brightness advice from service layer, return it to the front end
     * @return indoor brightness advice
     */
    @RequestMapping(value = "/Advice/Brightness",method = RequestMethod.GET)
    public String brightnessAdvice(){
        return adviceService.getBrightAdvice();
    }

     /**
     * Obtain air quality advice from service layer, return it to the front end
     * @return indoor air quality advice
     */
    @RequestMapping(value = "/Advice/AirQuality",method = RequestMethod.GET)
    public String airQualityAdvice(){
        return adviceService.getAirQualityAdvice();
    }


}
