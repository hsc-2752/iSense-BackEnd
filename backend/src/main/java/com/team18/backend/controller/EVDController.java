package com.team18.backend.controller;

import com.team18.backend.pojo.EnvironmentData;
import com.team18.backend.service.EVDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EVDController {

    @Autowired
    EVDataService evDataService;

    @RequestMapping(value = "/getEVD",method  = RequestMethod.GET)
    public EnvironmentData evdMapper() {
        return evDataService.getNewestData();
    }
    /**
     * 获取前端发送的时间(温度)
     */
    @RequestMapping(value =  "/getTime/tem",method = RequestMethod.POST)
    public List<Double> getTemperature(@RequestParam("count")int  count){
        return evDataService.getManyAvgTemp(count);
    }
    /**
     * 获取前端发送的时间(湿度)
     */
    @RequestMapping(value =  "/getTime/hum",method = RequestMethod.POST)
    public List<Double> getHumidity(@RequestParam("count")int count){
        return evDataService.getManyAvgHum(count);
    }
    /**
     * 获取前端发送的时间(空气质量)
     */
    @RequestMapping(value =  "/getTime/airQuality",method = RequestMethod.POST)
    public List<Double> getAirQuality(@RequestParam("count")int count){
        return evDataService.getManyAvgAQ(count);
    }
    /**
     * 获取前端发送的时间(光照)
     */
    @RequestMapping(value =  "/getTime/brightness",method = RequestMethod.POST)
    public List<Double> getBrightness(@RequestParam("count")int count){
        return evDataService.getManyAvgBright(count);
    }
    /**
     * 获取前端发送的时间(噪音)
     */
    @RequestMapping(value =  "/getTime/noise",method = RequestMethod.POST)
    public List<Double> getNoiseLevel(@RequestParam("count")int count){
        return evDataService.getManyAvgNoise(count);
    }




}
