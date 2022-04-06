package com.team18.backend.service;

import com.team18.backend.mapper.HealthMapper;
import com.team18.backend.pojo.CalculatedSleepData;
import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.BMIData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This service is used to analysis
 */
@Service
public class HealthDataService {
    @Autowired
    private HealthMapper healthMapper;

    /**
     * Get the latest piece of raw health data
     */
    public HeartData getNewestData(){
        return healthMapper.findAll();
    }

    /**
     * Obtain and store bmi
     */
    public double getAndStoreBMI(double weight, double height){
        double bmi = weight/ (height*height);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = dateFormat.format(new Date());
        healthMapper.storeBMI(bmi, time);
        return bmi;
    }
    /**
     * Get all BMIs and return them as a list with BMI and time
     */
    public List<BMIData> getAllBMI(){
       return healthMapper.getBMI();
    }

    /**
     * For drawing data acquisition, the front end returns a number of abscissa,
     * Take a number of 15-minute averages based on the number of horizontal coordinates.
     * (Blood oxygen)
     */
    public List<Double> getManyAvgBOS(int count){
        List<Double> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeMill = System.currentTimeMillis();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {

            list.add(healthMapper.findBOS(time));

            timeMill = timeMill - 1000*60*15;
            time = dateFormat.format(timeMill);
        }
        return list;
    }
    /**
     * For drawing data acquisition, the front end returns a number of abscissa,
     * get a number of 15-minute averages based on the number of horizontal coordinates
     * (Heart rate)
     *
     */

    public List<Double> getManyAvgHR(int count){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(healthMapper.findHR(time));

            timeMill -= 1000*60*15;
           time = dateFormat.format(timeMill);

        }
        return list;
    }

    /**
     * For drawing data acquisition, the front end returns a number of abscissa,
     * Take a number of 15-minute averages based on the number of horizontal coordinates。
     * (sleep)
     * Return all the sleep data if there is not so much data in the database
     *
     */
    @ExceptionHandler
    public List<CalculatedSleepData> getManySleep(int count){
            List<CalculatedSleepData> sleep;
        try{
             sleep = healthMapper.findSleep(count);
        }catch (DataAccessException e){
            sleep = healthMapper.findAllSleep();
        }
        return sleep;
    }


}
