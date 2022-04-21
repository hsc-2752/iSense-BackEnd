package com.team18.backend.controller;

import com.team18.backend.pojo.EnvironmentData;
import com.team18.backend.service.EVDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller controls all connection about environment data.
 */
@RestController
public class EVDController {


    EVDataService evDataService;
    @Autowired
    EVDController(EVDataService evDataService){
        this.evDataService = evDataService;
    }

    @RequestMapping(value = "/getEVD",method  = RequestMethod.GET)
    public EnvironmentData evdMapper() {
        return evDataService.getNewestData();
    }
    /**
     * gain a list of temperature data
     */
    @RequestMapping(value =  "/getTime/tem",method = RequestMethod.POST)
    public List<Double> getTemperature(@RequestParam("count")int  count){
        return evDataService.getManyAvgTemp(count);
    }
    /**
     * gain a list of humidity data
     */
    @RequestMapping(value =  "/getTime/hum",method = RequestMethod.POST)
    public List<Double> getHumidity(@RequestParam("count")int count){
        return evDataService.getManyAvgHum(count);
    }
    /**
     * gain a list of air quality data
     */
    @RequestMapping(value =  "/getTime/airQuality",method = RequestMethod.POST)
    public List<Double> getAirQuality(@RequestParam("count")int count){
        return evDataService.getManyAvgAQ(count);
    }
    /**
     * gain a list of illumination data
     */
    @RequestMapping(value =  "/getTime/brightness",method = RequestMethod.POST)
    public List<Double> getBrightness(@RequestParam("count")int count){
        return evDataService.getManyAvgBright(count);
    }
    /**
     * gain a list of decibel data
     */
    @RequestMapping(value =  "/getTime/noise",method = RequestMethod.POST)
    public List<Double> getNoiseLevel(@RequestParam("count")int count){
        return evDataService.getManyAvgNoise(count);
    }




}
