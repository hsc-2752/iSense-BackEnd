package com.team18.backend.service;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EVDataService {

    @Autowired
    private EVDataMapper evDataMapper;

    /**
     * 用于画图的数据获取，前端返回一个横坐标个数，
     * 根据横坐标个数决定获取几个十五分钟的平均值。
     * (温度)
     *
     */
    public List<Double> getManyAvgTemp(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findTem(time));
            //TODO 开发测试为每五秒的平均值，正式测试差值改为一小时
            timeMill -= 1000*5;
            time = dateFormat.format(timeMill);
        }
       return list;
    }


    /**
     * 获取一小时内的湿度平均值
     */
    public List<Double> getManyAvgHum(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findHum(time));
            //TODO 开发测试为每五秒的平均值，正式测试差值改为一小时
            timeMill -= 1000*5;
            time = dateFormat.format(timeMill);
        }
        return list;
    }

    /**
     * Obtain the average indoor noise
     * 获取一小时内的噪音平均值
     */
    public List<Double> getManyAvgNoise(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findVoice(time));
            //TODO 开发测试为每五秒的平均值，正式测试差值改为一小时
            timeMill -= 1000*5;
            time = dateFormat.format(timeMill);
        }
        return list;
    }


    /**
     * 获取半小时内光照平均值
     */
    public List<Double> getManyAvgBright(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findBrightness(time));
            //TODO 开发测试为每五秒的平均值，正式测试差值改为半小时
            timeMill -= 1000*5;
            time = dateFormat.format(timeMill);
        }
        return list;
    }

    /**
     *获取半小时内空气质量
     */
    public List<Double> getManyAvgAQ(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findHCHO(time));
            //TODO 开发测试为每五秒的平均值，正式测试差值改为半小时
            timeMill -= 1000*5;
            time = dateFormat.format(timeMill);
        }
        return list;
    }

    /**
     * 获取睡眠期间各项环境因素数据
     */

    public List<EnvironmentData> getSleepEVDataList(String startTime, String endTime){
        return evDataMapper.sleepEVData(startTime,endTime);
    }

    /**
     * 获取环境最新的一条数据
     */
    public EnvironmentData getNewestData(){
        return evDataMapper.findAll();
    }
}
