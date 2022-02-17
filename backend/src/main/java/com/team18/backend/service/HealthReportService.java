package com.team18.backend.service;

import java.util.ArrayList;
import java.util.List;


public class HealthReportService {
    private List<String> heartRateList;
    private List<String> bloodOxygenList;
    private String sleep;
    private String report;

    public HealthReportService(List<String> heartRateList,List<String> bloodOxygenList,String sleep){
        this.heartRateList = heartRateList;
        this.bloodOxygenList = bloodOxygenList;
        this.sleep = sleep;
        report = "";
    }

    private int loopList(){
        int score = 1000;
        for (int i = 0; i < heartRateList.size();i++) {
            score = score - Math.abs(heartRateCondition(Double.valueOf(heartRateList.get(i))));
        }
        for (int i = 0; i < bloodOxygenList.size();i++) {
            score = score - Math.abs(bosCondition(Double.valueOf(bloodOxygenList.get(i))));
        }
        score = score - (int) sleep.charAt(0);
        return score/10;
    }

    private void sleepReport(){
        // brightness, noise, air quality, temperature
        if(sleep.charAt(0)=='0'){
            report = report + "You had a really good sleep! Congratulations!";
        }
        switch(sleep){
            case "11000" : //
                report = report + "According to our environment record, you might have been waken up due to the overly bright environment brightness, you should check the brightness before sleep";
                break;
            case "10100" :
                report = report + "According to our environment record, you might have been waken up due to the overly high noise, as the research suggests, the suitable sound volume for good sleep quality" +
                        "should be less 50 dB，soundproofing materials might be helpful for you!";
                break;
            case "10010" :
                report = report + "According to our environment record, you might have been waken up due to not very good air quality, please ensure some time for ventilation before sleep";
                break;
            case "10001" :
                report = report + "According to our environment record, you might have been waken up due to unsuitable temperature inside room, as the research suggests, the best temperature for sleep " +
                        "is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                break;
            case "11100" :
                report = report + "According to our environment record, you might have been waken up due to overly bright environment brightness and high noise，you should check the brightness before sleep and" +
                        "soundproofing materials might be helpful for you!";
                break;
            case "11010" :
                report = report + "According to our environment record, you might have been waken up due to overly bright environment brightness and not very good air quality，you should check the brightness before sleep and" +
                        "ensure some time for ventilation before sleep";
                break;
            case "11001" :
                report = report + "According to our environment record, you might have been waken up due to overly bright environment brightness and unsuitable temperature，you should check the brightness before sleep and" +
                        "as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                break;
            case "10110" :
                report = report + "According to our environment record, you might have been waken up due to overly high noise and not very good air quality." + "as the research suggests, the suitable sound volume for good sleep quality" +
                "should be less 50 dB，soundproofing materials might be helpful for you! Also, don't forget to ensure some time for ventilation before sleep!";
                break;
            case "10101" : //
                report = report + "According to our environment record, you might have been waken up due to overly high noise and unsuitable temperature，using some soundproofing materials might be helpful for you" +
                        "Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                break;
            case "10011" :
                report = report + "According to our environment record, you might have been waken up due to not very good air quality and unsuitable temperature. As the research suggests, the best temperature for sleep is 20 degree Celsius，" +
                        "open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature, also, don't forget to" +
                        "ensure some time for ventilation before sleep";
                break;
            case "11110" :
                report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly bright environment brightness, high noise and not very good air quality, these are all elements that would greatly affect" +
                        "your sleep quality. Some time for ventilation before sleep, using soundproofing materials and eye patches would help!" ;
                break;
            case "11011" :
                report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly bright environment brightness, high temperature and not very good air quality, these are all elements that would greatly affect" +
                        "your sleep quality. Some time for ventilation, and using eye patches would be useful."+"Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                break;
            case "10111" :
                report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly high temperature and noise and not very good air quality, these are all elements that would greatly affect" +
                        "your sleep quality. Some time for ventilation, and using soundproofing materials would be useful."+"Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                break;
            case "11111" :
                report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly high temperature and brightness, noise and not very good air quality, these are all elements that would greatly affect" +
                        "your sleep quality. Some time for ventilation, and using soundproofing materials and eye patches would be useful."+"Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                break;
            case "11101" :
                report = report + "According to our environment record, you might have been waken up due to the mixed effect of overly high temperature and brightness, noise and not very good air quality, these are all elements that would greatly affect" +
                        "your sleep quality. Using soundproofing materials and eye patches would be useful."+"Also, as the research suggests, the best temperature for sleep is 20 degree Celsius，open or close the window, turn on or off the air conditioner can all help you adjust the indoor temperature!";
                break;
            case "10000" :
                report = report + "There's a lot of reasons that one might woke up suddenly. Stress is one of them. If that's the case for you, maybe try do some exercise or listen to some music before sleep. However, if you wake up suddenly for several days in a row, please seek medical attention!";
                break;
            default:
                System.out.println("not a valid sleep condition");
        }
    }


    private int heartRateCondition(Double hr){
        int condition = -5; //-5 is used in case the if statements are skipped
        if (hr <= 60){
            condition = -2;
        }
        if (hr >60 && hr <= 70){
            condition = -1;
        }
        if (hr > 70 && hr <= 90){
            condition = 0;
        }
        if (hr > 90 && hr <= 100){
            condition = 1;
        }
        if (hr > 100){
            condition = 2;
        }
        return condition;
    }


    private int bosCondition(Double bos){

        int condition = -5; //-5 is used in case the if statements are skipped
        if (bos <= 90){
            condition = -50;
        }
        if (bos > 90 && bos <= 95){
            condition = -25;
        }
        if (bos > 95){
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
       // ArrayList<Integer> temp_0 = new ArrayList<>();
        ArrayList<Integer> temp_n1 = new ArrayList<>();
        ArrayList<Integer> temp_n2 = new ArrayList<>();
        double tempMax = 0;
        double tempMin = 10000;

        for(int i = 0; i < heartRateList.size();i++){
            if (Integer.parseInt(heartRateList.get(i))>100){
                temp_2.add(i);
                if (Integer.parseInt(heartRateList.get(i))>tempMax){
                    tempMax = Integer.parseInt(heartRateList.get(i));
                }
            }
            if (Integer.parseInt(heartRateList.get(i))<= 60){
                temp_n2.add(i);
                if (Integer.parseInt(heartRateList.get(i))<tempMin){
                    tempMin = Integer.parseInt(heartRateList.get(i));
                }
            }
            if (Integer.parseInt(heartRateList.get(i)) >60 && Integer.parseInt(heartRateList.get(i)) <= 70){
                temp_n1.add(i);
            }
//            if (list.get(i).getTemp() > 18 && list.get(i).getTemp() <= 25){
//                temp_0.add(i);
//            }
            if (Integer.parseInt(heartRateList.get(i)) > 90 && Integer.parseInt(heartRateList.get(i))<= 100){
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

    public String getReport(){
        String report = "";
        report = report + generateOverall();
        report = report + findAbnormal();
        report = report +"All other conditions are very good!";
        return report;
    }
}
