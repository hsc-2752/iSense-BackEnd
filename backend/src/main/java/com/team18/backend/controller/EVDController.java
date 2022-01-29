package com.team18.backend.controller;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EVDController {
    @Autowired
    EVDataMapper evDataMapper;

    @RequestMapping(value = "/getEVD",method  = RequestMethod.GET)
    @ResponseBody
    public List<EnvironmentData> evdMapper() {
        List<EnvironmentData> environmentData = evDataMapper.findAll();
        return environmentData;
    }
    /**
     * 获取前端发送的时间(温度)
     */
    @RequestMapping(value =  "/getTime/tem",method = RequestMethod.POST)
    public String getTemperature(@RequestParam("temTime")String temTime){
        return evDataMapper.findTem(temTime);
    }
    /**
     * 获取前端发送的时间(湿度)
     */
    @RequestMapping(value =  "/getTime/hum",method = RequestMethod.POST)
    public String getHumidity( @RequestParam("humTime")String humTime){
        return evDataMapper.findHum(humTime);
    }

    /**
     * 获取前端发送的时间(空气质量)
     */
    @RequestMapping(value =  "/getTime/airQuality",method = RequestMethod.POST)
    public String getAirQuality(@RequestParam("airTime")String airTime){
        return evDataMapper.findHCHO(airTime);
    }
    /**
     * 获取前端发送的时间(光照)
     */
    @RequestMapping(value =  "/getTime/brightness",method = RequestMethod.POST)
    public String getBrightness(@RequestParam("brightTime")String brightTime){
        return evDataMapper.findBrightness(brightTime);
    }
    /**
     * 获取前端发送的时间(噪音)
     */
    @RequestMapping(value =  "/getTime/noise",method = RequestMethod.POST)
    public String getNoiseLevel(@RequestParam("noiseTime")String noiseTime){
        return evDataMapper.findVoice(noiseTime);
    }



}
