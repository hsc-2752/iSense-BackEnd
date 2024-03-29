package com.team18.backend.service;

import com.team18.backend.mapper.HealthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Reports used to obtain health data, including analysis combining the previous 15 minutes of health data, sleep reports,
 * And analysis of each different data type, including proportion of inappropriate data, maximum value, minimum value, average value.
 */
@Service
@Transactional
public class HealthReportService {
    //Standard value
    public static final int LOW_STD = 60;
    public static final int SUB_LOW_STD = 70;
    public static final int HIGH_STAD = 90;
    public static final int TO_HIGH_STAD = 100;
    public static final int O_LACK = 90;
    public static final int O_STAD = 95;
    public static final double rartioErr = 0.05;
    @Autowired
    private HealthMapper healthMapper;

    /**
     * Heart rate for the last 15 minutes
     */
    private List<Double> heartRateList;
    /**
     * Blood oxygen for 15 minutes
     */
    private List<Double> bloodOxygenList;

    @Autowired
    private SleepService sleepService;

    private boolean isAwaken;
    private String sleep;
    private String report;


    public String getReport(boolean isAwaken){
        //initialize
        this.heartRateList = healthMapper.findReportHR();
        this.bloodOxygenList = healthMapper.findReportBOS();
        this.sleep = sleepService.evaluate();
        if(sleep == null){
            sleep = "0000";
        }
        this.isAwaken = isAwaken;
         report = "";
        report = report + generateOverall();
        report = report + findAbnormal();
        report += findHRAbnormal();
        report += findBOSAbnormal();
        sleepReport();
        report = report +"All other conditions are very good!";
        return report;
    }

    private int loopList(){
        int score = 1000;
        for (double s : heartRateList) {
            score = score - Math.abs(heartRateCondition(s));
        }
        for (double s : bloodOxygenList) {
            score = score - Math.abs(bosCondition(s));
        }
        score = score - (int) sleep.charAt(0);
        return score/10;
    }

    /**
     * generate sleep report
     */
    private void sleepReport(){
        // brightness, noise, air quality, temperature
        if(!isAwaken){
            report = report + "You had a really good sleep! Congratulations!";
        }
        else {
            switch(sleep){
                case "1000" :
                    report = report + "According to our environment record, you might have been waken up due to the overly bright environment brightness, you should check the brightness before sleep";
                    break;
                case "0100" :
                    report = report + "According to our environment record, you might have been waken up due to the overly high noise, as the research suggests, the suitable sound volume for good sleep quality" +
                            "should be less 50 dB，soundproofing materials might be helpful for you!";
                    break;
                case "0010" :
                    report = report + "According to our environment record, you might have been waken up due to not very good air quality, please ensure some time for ventilation before sleep";
                    break;
                case "0001" :
                    report = report + "According to our environment record, you might have been waken up due to unsuitable temperature inside room, as the research suggests, the best temperature for sleep " +
                            "is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                    break;
                case "1100" :
                    report = report + "According to our environment record, you might have been waken up due to overly bright environment brightness and high noise，you should check the brightness before sleep and" +
                            "soundproofing materials might be helpful for you!";
                    break;
                case "1010" :
                    report = report + "According to our environment record, you might have been waken up due to overly bright environment brightness and not very good air quality，you should check the brightness before sleep and" +
                            "ensure some time for ventilation before sleep";
                    break;
                case "1001" :
                    report = report + "According to our environment record, you might have been waken up due to overly bright environment brightness and unsuitable temperature，you should check the brightness before sleep and" +
                            "as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                    break;
                case "0110" :
                    report = report + "According to our environment record, you might have been waken up due to overly high noise and not very good air quality." + "as the research suggests, the suitable sound volume for good sleep quality" +
                            "should be less 50 dB，soundproofing materials might be helpful for you! Also, don't forget to ensure some time for ventilation before sleep!";
                    break;
                case "0101" :
                    report = report + "According to our environment record, you might have been waken up due to overly high noise and unsuitable temperature，using some soundproofing materials might be helpful for you" +
                            "Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                    break;
                case "0011" :
                    report = report + "According to our environment record, you might have been waken up due to not very good air quality and unsuitable temperature. As the research suggests, the best temperature for sleep is 20 degree Celsius，" +
                            "open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature, also, don't forget to" +
                            "ensure some time for ventilation before sleep";
                    break;
                case "1110" :
                    report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly bright environment brightness, high noise and not very good air quality, these are all elements that would greatly affect" +
                            "your sleep quality. Some time for ventilation before sleep, using soundproofing materials and eye patches would help!" ;
                    break;
                case "1011" :
                    report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly bright environment brightness, high temperature and not very good air quality, these are all elements that would greatly affect" +
                            "your sleep quality. Some time for ventilation, and using eye patches would be useful."+"Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                    break;
                case "0111" :
                    report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly high temperature and noise and not very good air quality, these are all elements that would greatly affect" +
                            "your sleep quality. Some time for ventilation, and using soundproofing materials would be useful."+"Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                    break;
                case "1111" :
                    report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly high temperature and brightness, noise and not very good air quality, these are all elements that would greatly affect" +
                            "your sleep quality. Some time for ventilation, and using soundproofing materials and eye patches would be useful."+"Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                    break;
                case "1101" :
                    report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly high temperature and brightness, noise and not very good air quality, these are all elements that would greatly affect" +
                            "your sleep quality. Using soundproofing materials and eye patches would be useful."+"Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                    break;
                case "0000" :
                    report = report + "There's a lot of reasons that one might woke up suddenly. Stress is one of them. If that's the case for you, maybe try do some exercise or listen to some music before sleep. However, if you wake up suddenly for several days in a row, please seek medical attention!";
                    break;
                default:
                   report+=" Sleep data error";
            }
        }

    }


    private int heartRateCondition(Double hr){
        //-5 is used in case the if statements are skipped
        int condition = -5;

        if (hr <= LOW_STD){
            condition = -2;
        }
        if (hr >LOW_STD && hr <= SUB_LOW_STD){
            condition = -1;
        }
        if (hr > SUB_LOW_STD && hr <= HIGH_STAD){
            condition = 0;
        }
        if (hr > HIGH_STAD && hr <= TO_HIGH_STAD){
            condition = 1;
        }
        if (hr > TO_HIGH_STAD){
            condition = 2;
        }
        return condition;
    }


    private int bosCondition(Double bos){
//-5 is used in case the if statements are skipped
        int condition = -5;
        if (bos <= O_LACK){
            condition = -50;
        }
        if (bos > O_LACK && bos <= O_STAD){
            condition = -25;
        }
        if (bos > O_STAD){
            condition = 0;
        }
        return condition;
    }


    private String generateOverall(){
        int score = loopList();
        String overall = "";
        if(score==100){
            overall = "Your health condition is perfect!!!! ";
        }
        if(score>=60 && score <100){
            overall = "Your health condition is generally good, but some aspect can be improved! ";
        }
        if (score <60){
            overall = "Your health condition is not very optimistic and quite a few actions might need to be taken! ";
        }
        if (score < 0){
            overall = "You need to seek medical attention immediately!";
        }
        return overall;
    }

    private String findAbnormal(){
        String abnormal = "";
        ArrayList<Integer> temp_2 = new ArrayList<>();
        ArrayList<Integer> temp_1 = new ArrayList<>();
        ArrayList<Integer> temp_n1 = new ArrayList<>();
        ArrayList<Integer> temp_n2 = new ArrayList<>();
        double tempMax = 0;
        double tempMin = 10000;

        for(int i = 0; i < heartRateList.size();i++){
            if (heartRateList.get(i)>TO_HIGH_STAD){
                temp_2.add(i);
                if (heartRateList.get(i)>tempMax){
                    tempMax = heartRateList.get(i);
                }
            }
            if (heartRateList.get(i)<= LOW_STD){
                temp_n2.add(i);
                if (heartRateList.get(i)<tempMin){
                    tempMin =heartRateList.get(i);
                }
            }
            if (heartRateList.get(i) >LOW_STD && heartRateList.get(i) <= SUB_LOW_STD){
                temp_n1.add(i);
            }
//            if (list.get(i).getTemp() > 18 && list.get(i).getTemp() <= 25){
//                temp_0.add(i);
//            }
            if (heartRateList.get(i) > HIGH_STAD && heartRateList.get(i)<= TO_HIGH_STAD){
                temp_1.add(i);
            }
        }
        double temp2Percent = (double)temp_2.size()/heartRateList.size();
        double temp1Percent = (double)temp_1.size()/heartRateList.size();
        double tempn1Percent = (double)temp_n1.size()/heartRateList.size();
        double tempn2Percent = (double)temp_n2.size()/heartRateList.size();
        if (temp2Percent>0){
            abnormal = abnormal + "The temperature for " +temp2Percent + "% of time in past three hours are too high";
            abnormal = abnormal + ", The temperature has reached the highest to "+tempMax+"degrees Celsius";
        }
        if (temp1Percent > 0){
            abnormal = abnormal +" ,"+temp2Percent + "% of time are a bit high";
        }
        if (tempn1Percent>0){
            abnormal = abnormal + ", the temperature for " +temp2Percent + "% of time in past three hours are a bit low";
        }
        if (tempn2Percent > 0){
            abnormal = abnormal +" ,"+temp2Percent + "% of time are too low";
            abnormal = abnormal + ", The temperature has dropped to the lowest to "+tempMin+"degrees Celsius";
        }
        return abnormal;
    }

    /**
     * Heart rate data were analyzed over a 15-minute period
     * report consist of ration of abnormal data, maximum value, minimum value
     * @return Heart rate report
     */
    private String findHRAbnormal(){
        int abCounter = 0;
        double abratio;
        double max, min;
        String abnormal;
        abnormal = "In the past 15 minutes, ";
        for (double hr: heartRateList ) {
            if (!(hr > SUB_LOW_STD && hr < HIGH_STAD))
                abCounter++;
        }
        Collections.sort(heartRateList);
        try{
            max = heartRateList.get(0);
            min = heartRateList.get(heartRateList.size()-1);
            abratio = abCounter/(double)heartRateList.size();
            if (abratio > rartioErr){
                abnormal += "if you are not exercising, " +
                        "your abnormal heart-rate proportion has exceeded 5%, and your " +
                        "maximum heart rate and minimum heart rate are: "+max+" and "+min;
            }else{
                abnormal += "your heart-rate was in a nonmal state," +
                        " and your maximum heart rate and minimum heart rate are: "+max+" and "+min;
            }
        } catch (Exception e){
            abnormal+=" HR data Error";
        }

        return abnormal;
    }

    /**
     * Blood oxygen was analyzed at 15 minutes
     * report consist of ration of abnormal data, maximum value, minimum value
     * @return BOS report
     */
    private String findBOSAbnormal(){
        String abnormal = "In the past 15 minutes, ";
        double sum = 0, max, min, avg;
        for (double bos: bloodOxygenList) {
            sum += bos;
        }
        Collections.sort(bloodOxygenList);
        try{
            max = bloodOxygenList.get(0);
            min = bloodOxygenList.get(bloodOxygenList.size()-1);
            avg = sum/bloodOxygenList.size();
            if (avg > 0.9377){
                abnormal += "your blood oxygen saturation is very normal, with maximum and minimum value: " + max + ", " + min;
            }else{
                abnormal += "your general blood oxygen saturation was abnormal, with maximum and minimum value: " + max + ", " + min;
            }
        }catch (Exception e){
            abnormal += "BOS data error";
        }

        return abnormal;
    }


}
