package com.team18.backend.service;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EVDataService {

    @Autowired
    private EVDataMapper evDataMapper;

    /**
     *获取一小时内的温度平均值
     */
    public String findTemp(String temTime) {
        return evDataMapper.findTem(temTime);
    }


    /**
     * 获取一小时内的湿度平均值
     */
    public String findHumidity(String humTime) {
        return evDataMapper.findHum(humTime);
    }

    /**
     * Obtain the average indoor noise
     * 获取一小时内的噪音平均值
     */
    public String findVoi(String noiseTime) {
        return evDataMapper.findVoice(noiseTime);
    }


    /**
     * 获取半小时内光照平均值
     */
    public String findBright(String brightTime) {
        return evDataMapper.findBrightness(brightTime);
    }


    /**
     *获取半小时内空气质量
     */
    public String findHcho(String airTime) {
        return evDataMapper.findHCHO(airTime);
    }

    /**
     * 获取三小时的环境数据
     */
    public List<EnvironmentData> threeHourEVdata() {
        return evDataMapper.reportData();
    }

    /**
     * 获取睡眠期间各项环境因素数据
     */

    public List<EnvironmentData> getSleepEVDataList(String st, String et){
        return evDataMapper.sleepEVData(st,et);
    }

    /**
     * 获取环境最新的一条数据
     */
    public EnvironmentData getNewestData(){
        return evDataMapper.findAll();
    }
}
