package com.team18.backend.service;

import com.team18.backend.mapper.HealthMapper;
import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.HuData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class HealthDataService {
    @Autowired
    private HealthMapper healthMapper;

    /**
     * 获取最新一条健康原始数据
     */
    public HeartData getNewestData(){
        return healthMapper.findAll();
    }

    /**
     * 获取并存储bmi
     * @param weight
     * @param height
     * @return
     */
    public double getAndStoreBMI(double weight, double height){
        double bmi = weight/ (height*height);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = dateFormat.format(new Date());
        healthMapper.storeBMI(bmi, time);
        return bmi;
    }
    /**
     * 获取所有bmi，以一个list返回，其中有bmi和时间
     */
    public List<HuData> getAllBMI(){
       return healthMapper.getBMI();
    }

    /**
     * 用于画图的数据获取，前端返回一个横坐标个数，
     * 根据横坐标个数决定获取几个十五分钟的平均值。
     * (血氧)
     */
    public List<Double> getManyAvgBOS(int count){
        List<Double> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeMill = System.currentTimeMillis();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {

            list.add(healthMapper.findBOS(time));

            timeMill = timeMill - 1000*5;
            time = dateFormat.format(timeMill);
        }
        return list;
    }
    /**
     * 用于画图的数据获取，前端返回一个横坐标个数，
     * 根据横坐标个数决定获取几个十五分钟的平均值。
     * (心率)
     *
     */
    //TODO 开发测试为每五秒的平均值，正式测试改为15分钟的平均值
    public List<Double> getManyAvgHR(int count){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(healthMapper.findHR(time));

            timeMill -= 1000*5;
           time = dateFormat.format(timeMill);

        }
        return list;
    }




}
