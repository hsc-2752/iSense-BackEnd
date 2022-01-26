package com.team18.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EVAdviceController {
    /**
     * Obtain heart rate advice from service layer, return it to the front end
     * @return temperature and humidity advice
     */
    @RequestMapping(value = "/Advice/TemAndHum",method = RequestMethod.GET)
    public String temAndHumAdvice(){
        return "temperature and humidity advice";
    }

    /**
     * Obtain pressure advice from service layer, return it to the front end
     * @return indoor pressure advice
     */
    @RequestMapping(value = "/Advice/Pressure",method = RequestMethod.GET)
    public String pressureAdvice(){
        return "pressure advice";
    }
    /**
     * Obtain noise advice from service layer, return it to the front end
     * @return indoor Noise advice
     */
    @RequestMapping(value = "/Advice/Noise",method = RequestMethod.GET)
    public String noiseAdvice(){
        return "noise advice";
    }

    /**
     * Obtain brightness advice from service layer, return it to the front end
     * @return indoor brightness advice
     */
    @RequestMapping(value = "/Advice/Brightness",method = RequestMethod.GET)
    public String brightnessAdvice(){
        return "brightness advice";
    }

     /**
     * Obtain air quality advice from service layer, return it to the front end
     * @return indoor air quality advice
     */
    @RequestMapping(value = "/Advice/AirQuality",method = RequestMethod.GET)
    public String airQualityAdvice(){
        return "air quality advice";
    }


}
