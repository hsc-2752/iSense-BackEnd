package com.team18.backend.controller;

import com.team18.backend.pojo.EnvironmentData;
import com.team18.backend.service.EVDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class EVDController {

    @Autowired
    EVDataService evDataService;

    @RequestMapping(value = "/getEVD",method  = RequestMethod.GET)
    @ResponseBody
    public EnvironmentData evdMapper() {
        return evDataService.getNewestData();
    }
    /**
     * 获取前端发送的时间(温度)
     */
    @RequestMapping(value =  "/getTime/tem",method = RequestMethod.POST)
    public String getTemperature(@RequestParam("temTime")String temTime){
        return evDataService.findTemp(temTime);
    }
    /**
     * 获取前端发送的时间(湿度)
     */
    @RequestMapping(value =  "/getTime/hum",method = RequestMethod.POST)
    public String getHumidity( @RequestParam("humTime")String humTime){
        return evDataService.findHumidity(humTime);
    }
    /**
     * 获取前端发送的时间(空气质量)
     */
    @RequestMapping(value =  "/getTime/airQuality",method = RequestMethod.POST)
    public String getAirQuality(@RequestParam("airTime")String airTime){
        return evDataService.findHcho(airTime);
    }
    /**
     * 获取前端发送的时间(光照)
     */
    @RequestMapping(value =  "/getTime/brightness",method = RequestMethod.POST)
    public String getBrightness(@RequestParam("brightTime")String brightTime){
        return evDataService.findBright(brightTime);
    }
    /**
     * 获取前端发送的时间(噪音)
     */
    @RequestMapping(value =  "/getTime/noise",method = RequestMethod.POST)
    public String getNoiseLevel(@RequestParam("noiseTime")String noiseTime){
        return evDataService.findVoi(noiseTime);
    }




}
