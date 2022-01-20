package com.team18.backend.controller;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     * Obtain temperature and humidity data in response to front-end acquisition request
     * @return latest temperature and humidity data
     */
    @RequestMapping(value = "/getEVD/TemAndHum",method = RequestMethod.GET)
    @ResponseBody
    public List<String> temAndHumMapper(){
        List<String> temAndHumData = evDataMapper.findTemHum();
        return temAndHumData;
    }

    /**
     * Obtain indoor pressure data in response to front-end acquisition request
     * @return latest indoor pressure data
     */
    @RequestMapping(value = "/getEVD/Pressure",method = RequestMethod.GET)
    public String pressureMapper(){
        return evDataMapper.findPressure();
    }

    /**
     * Obtain indoor noise in response to front-end acquisition request
     * @return latest indoor noise data
     */
    @RequestMapping(value = "/getEVD/Noise",method = RequestMethod.GET)
    public String voiceMapper(){
        return evDataMapper.findVoice();
    }
    /**
     * Obtain indoor brightness in response to front-end acquisition request
     * @return latest indoor brightness data
     */
    @RequestMapping(value = "/getEVD/Brightness",method = RequestMethod.GET)
    public String brightMapper(){
        return evDataMapper.findBrightness();
    }

    /**
     * Obtain indoor air quality in response to front-end acquisition request
     * @return latest indoor air quality data
     */
    @RequestMapping(value = "/getEVD/HCHO",method = RequestMethod.GET)
    public String hchoMapper(){
        return evDataMapper.findHCHO();
    }

}
