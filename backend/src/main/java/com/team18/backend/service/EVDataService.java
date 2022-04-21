package com.team18.backend.service;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This service provide environment data analysis result.
 */
@Service
public class EVDataService {

    @Autowired
    private EVDataMapper evDataMapper;

    /**
     * For drawing data acquisition, the front end returns a number of abscissa,
     * Take a number of 15-minute averages based on the number of horizontal coordinates.
     * (Temperature)
     *
     */
    public List<Double> getManyAvgTemp(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findTem(time));
            timeMill -= 1000*60*60;
            time = dateFormat.format(timeMill);
        }
       return list;
    }


    /**
     * Get the average humidity over an hour
     */
    public List<Double> getManyAvgHum(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findHum(time));
            timeMill -= 1000*60*60;
            time = dateFormat.format(timeMill);
        }
        return list;
    }

    /**
     * Obtain the average indoor noise
     */
    public List<Double> getManyAvgNoise(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findVoice(time));
            timeMill -= 1000*60*60;
            time = dateFormat.format(timeMill);
        }
        return list;
    }


    /**
     * Get the average illumination in half an hour
     */
    public List<Double> getManyAvgBright(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findBrightness(time));
            timeMill -= 1000*60*30;
            time = dateFormat.format(timeMill);
        }
        return list;
    }

    /**
     * Get air quality within half an hour
     */
    public List<Double> getManyAvgAQ(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        List<Double> list = new ArrayList<>();
        String time = dateFormat.format(timeMill);

        for (int i = 0; i < count; i++) {
            list.add(evDataMapper.findHCHO(time));
            timeMill -= 1000*60*30;
            time = dateFormat.format(timeMill);
        }
        return list;
    }

    /**
     * Data of various environmental factors during sleep were obtained
     */

    public List<EnvironmentData> getSleepEVDataList(String startTime, String endTime){
        return evDataMapper.sleepEVData(startTime,endTime);
    }

    /**
     * Gets the latest piece of data for the environment
     */
    public EnvironmentData getNewestData(){
        return evDataMapper.findAll();
    }
}
