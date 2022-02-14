package com.team18.backend.service;



import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class EnvReportService {
    private List<EnvironmentData> list;

    public EnvReportService(List<EnvironmentData>list){

      this.list = list;
    }
    private int loopList(){
        int score = 100000;
        for (EnvironmentData e:list) {
            score = score - getScore(e);
        }
        return score/1000;
    }

    private int tempCondition(EnvironmentData e){
        Double temp = e.getTemp();
        int condition = -5; //-5 is used in case the if statements are skipped
        if (temp <= 11){
            condition = -2;
        }
        if (temp >11 && temp <= 18){
            condition = -1;
        }
        if (temp > 18 && temp <= 25){
            condition = 0;
        }
        if (temp > 25 && temp <= 32){
            condition = 1;
        }
        if (temp > 32){
            condition = 2;
        }
        return condition;
    }

    private int humidityCondition(EnvironmentData e){
        Double temp = e.getHumidity();
        int condition = -5; //-5 is used in case the if statements are skipped
        if (temp <= 30){
            condition = -2;
        }
        if (temp >30 && temp <= 40){
            condition = -1;
        }
        if (temp > 40 && temp <= 60){
            condition = 0;
        }
        if (temp > 60 && temp <= 80){
            condition = 1;
        }
        if (temp > 80){
            condition = 2;
        }
        return condition;
    }

    private int airCondition(EnvironmentData e){
        //TODO
        return 0;
    }

    private int brightCondition(EnvironmentData e){
        //TODO
        return 0;
    }

    private int noiseCondition(EnvironmentData e){
        Double temp = e.getVoice();
        int condition = -5; //-5 is used in case the if statements are skipped
        if (temp <= 50){
            condition = 0;
        }
        if (temp > 50 && temp <= 70){
            condition = 1;
        }
        if (temp > 70){
            condition = 2;
        }
        return condition;
    }

    private int pressureCondition(EnvironmentData e){
        Double temp = e.getPressure();
        int condition = -5; //-5 is used in case the if statements are skipped
        if (temp <= 950){
            condition = -1;
        }
        else{
            condition = 0;
        }
        return condition;
    }

    private int getScore(EnvironmentData e){
        return 5*(20-(Math.abs(tempCondition(e))+Math.abs(humidityCondition(e))+Math.abs(airCondition(e))+Math.abs(brightCondition(e))+Math.abs(noiseCondition(e))+Math.abs(pressureCondition(e))));
    }

    private String generateOverall(){
        int score = loopList();
        String overall = "";
        if(score==100){
            overall = "The environment condition is perfect!!!! ";
        }
        if(score>=80 && score <100){
            overall = "The environment is generally good, but some can be improved! ";
        }
        if (score>=60 && score <80){
            overall = "oops! The environment might need to be adjusted a bit to live more comfortable! ";
        }
        if (score <60){
            overall = "The environment condition is not very optimistic and quite a few actions might need to be taken! ";
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

        for(int i = 0; i < list.size();i++){
            if (list.get(i).getTemp()>32){
                temp_2.add(i);
                if (list.get(i).getTemp()>tempMax){
                    tempMax = list.get(i).getTemp();
                }
            }
            if (list.get(i).getTemp() <= 11){
                temp_n2.add(i);
                if (list.get(i).getTemp()<tempMin){
                    tempMin = list.get(i).getTemp();
                }
            }
            if (list.get(i).getTemp() >11 && list.get(i).getTemp() <= 18){
                temp_n1.add(i);
            }
//            if (list.get(i).getTemp() > 18 && list.get(i).getTemp() <= 25){
//                temp_0.add(i);
//            }
            if (list.get(i).getTemp() > 25 && list.get(i).getTemp() <= 32){
                temp_1.add(i);
            }
        }
        double temp2Percent = (double)temp_2.size()/list.size();
        double temp1Percent = (double)temp_1.size()/list.size();
        double tempn1Percent = (double)temp_n1.size()/list.size();
        double tempn2Percent = (double)temp_n2.size()/list.size();
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
