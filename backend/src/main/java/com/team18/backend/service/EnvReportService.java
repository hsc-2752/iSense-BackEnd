package com.team18.backend.service;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A report used to obtain environmental data,
 * including the environmental score based on the analysis of the previous three hours of environmental data，
 * And analysis of each different data type, including proportion of inappropriate data, maximum, minimum, and average
 */
@Service
public class EnvReportService {

     static final int AIR_QUALITY_STAND1 = 35;
     static final int AIR_QUALITY_STAND2 = 45;
     static final int BRIGHT_STAND1 = 600;
     static final int BRIGHT_STAND2 = 500;

     static final int PRESSURE_STAND1 = 101;

     static final int HUM_STAND1 = 30;
     static final int HUM_STAND2 = 40;
     static final int HUM_STAND3 = 60;
     static final int HUM_STAND4 = 80;


     static final int NOISE_STAND1 = 50;
     static final int NOISE_STAND2 = 70;

    @Autowired
    private EVDataMapper evDataMapper;

    @Autowired
    private RuleModeService ruleService;
    /**
     * Environmental data within the last three hours
     */
    private List<EnvironmentData> list;

    /**
     * Analyze environmental data during the three-hour period
     * @return Environment advice
     */
    public String getReport(){
        this.list = evDataMapper.reportData();
        String report = "";
        report = report + generateOverall();
        //temperature analysis
        report = report + findTempAbnormal();
        //humidity analysis
        report += findHumAbnormal();
        //air quality analysis
        report += findAirAbnormal();
        //illumination analysis
        report += findBrightAbnormal();
        //decibel analysis
        report += findNoiseAbnormal();
        //pressure analysis
        report += findPressAbnormal();
        report = report +"All other conditions are normal!";
        return report;
    }



    private int loopList(){
        int score = 100000;
        for (EnvironmentData e:list) {
            score = score - getScore(e);
        }
        return score/1000;
    }

    private int tempCondition(EnvironmentData e){
        double temp = e.getTemp();
        //-5 is used in case the if statements are skipped
        int condition = -5;

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
        double temp = e.getHumidity();
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
        double air = e.getHCHO();
        int condition;
        if(air > AIR_QUALITY_STAND2){
            condition = 2;
        }
        else if(air > AIR_QUALITY_STAND1){
            condition = 1;
        }
        else
            condition = 0;
        return condition;
    }

    private int brightCondition(EnvironmentData e){
        double bright = e.getBrightness();
        int condition;
        if (bright > BRIGHT_STAND1){
            //if too bright
            if(bright > 2*BRIGHT_STAND1){
                condition = 2;
            }
            else{
                condition = 1;
            }

        }
        else if (bright < BRIGHT_STAND2){
            //if too dark
            if(bright < (BRIGHT_STAND2*0.5)){
                condition = 2;
            }
            else {
                condition = 1;
            }
        }
        else{
            condition = 0;
        }
        return condition;
    }

    private int noiseCondition(EnvironmentData e){
        double temp = e.getVoice();
        //-5 is used in case the if statements are skipped
        int condition = -5;
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
        double temp = e.getPressure();
        //-5 is used in case the if statements are skipped
        int condition = -5;
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

    private String findTempAbnormal(){
        String abnormal = "";
        ArrayList<Integer> temp_2 = new ArrayList<>();
        ArrayList<Integer> temp_1 = new ArrayList<>();
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

    private String findPressAbnormal() {
        String abnormal = "";
        List<BigDecimal> preList = new ArrayList<>();
        double min = 0;
        double max = 0;
        double avg = 0;
        double ratio = list.size();
        double abnormal_mount = 0;
        double current;
        //存入数据
        for (int i = 0; i < list.size(); i++) {
            current = list.get(i).getPressure();
            if(i==0){
                max = current;
                min = current;
            }
            preList.add(BigDecimal.valueOf(current));
            if(current>PRESSURE_STAND1)
            {
                abnormal_mount++;
            }
            max = Math.max(max,current);
            min = Math.min(min,current);
            avg+=current;
        }
        avg /= ratio;
        ratio = abnormal_mount / ratio;

        //data analysis
        //if pressure is too high
        if (ruleService.isAvgHigher(PRESSURE_STAND1,preList)) {
            abnormal += "Data shows that your recent average room pressure is higher than standard atmospheric pressure. ";
        }
        abnormal+="Slightly higher barometric data during the first three hours account for the total "+ ratio+"%" +
                "In the indoor air pressure data of the first three hours, the highest value is: "+ max+
                ", and the lowest value is:"+min+
                " And the average value is:"+avg+"";

        return abnormal;
    }

    /**
     * Analyze the environmental decibel data
     * @return analysis result
     */
    private String findNoiseAbnormal() {
        String abnormal = "";
        List<BigDecimal> noiseList = new ArrayList<>();
        double min = 0,max = 0, avg =0 ,current;
        int abnormal_amount1 = 0,abnormal_amount2 = 0;
        double ratio = list.size();
        for (int i = 0; i < ratio; i++) {
            current = list.get(i).getVoice();
            if(i == 0){
                max = current;
                min = current;
            }
            else {
                max = Math.max(max,current);
                min = Math.min(min,current);
            }
            if (current > NOISE_STAND1){
                if(current > NOISE_STAND2){
                    abnormal_amount2++;
                }
                else
                abnormal_amount1 ++;
            }
            noiseList.add(BigDecimal.valueOf(current));
            avg +=current;
        }
        avg /= ratio;
        if(ruleService.isAvgHigher(NOISE_STAND1,noiseList))
        {
            if(ruleService.isAvgHigher(NOISE_STAND2,noiseList)){
                abnormal+="The data shows that the average level of noise in your room over the last three hours is higher than the decibel level of the noisy environment.";
            }
            else {
                abnormal+="The data shows that the average level of noise in your room over the last three hours is slightly higher than the appropriate ambient decibel level.";
            }
        }
        abnormal += "According to statistics of noisy data, ";
        //Report the proportion of noise
        if(abnormal_amount1>0)
        {
            abnormal+="it's a little bit noisy about "+(abnormal_amount1/ratio)+" percent of the time. ";
        }
        if(abnormal_amount2>0)
        {
            abnormal+= (abnormal_amount2/ratio)+"percent of the time, indoor decibels are extremely noisy.";
        }

        abnormal+="In the environmental decibel data of the first three hours, the highest value is:"+ max +
                " , and the lowest value is:"+min+
                " And the average value is :"+avg;
       return abnormal;
    }

    private String findBrightAbnormal() {
        String abnormal = "";
        List<BigDecimal> brightList = new ArrayList<>();
        double min = 0,max = 0, avg =0 ,current = 0;
        int abnormal_amount1 = 0,abnormal_amount2 = 0;
        double ratio = list.size();

        for (int i = 0; i < ratio; i++) {
            current = list.get(i).getBrightness();
            if(i == 0){
                min = current;
                max = current;
            }else{
                max = Math.max(max,current);
                min = Math.min(min,current);
            }
            if(current > BRIGHT_STAND1){
                abnormal_amount1 ++;
            }
            else if(current < BRIGHT_STAND2){
                abnormal_amount2 ++;
            }
            brightList.add(BigDecimal.valueOf(current));
            avg += current;
        }
        avg /= ratio;
         if(ruleService.isAvgHigher(BRIGHT_STAND1,brightList))
             abnormal += "The data showed that the average indoor light in the first three hours was above the optimum range. ";
        else if(ruleService.isAvgLower(BRIGHT_STAND2,brightList))
            abnormal+= "Average indoor light data for the first three hours were below the optimum range.";

        abnormal += "According to statistic of indoor light data, ";
        if(abnormal_amount1>0)
            abnormal += "The proportion of indoor light higher than the appropriate range is:"+(abnormal_amount1/ratio)+"% ";
        if(abnormal_amount2>0)
            abnormal += "The proportion of indoor light lower than the optimum range is"+(abnormal_amount2/ratio)+" ";

        abnormal += "In all indoor light data, the strongest light reaches:"+max+" lux," +
                " and the lowest indoor light is:"+min+"lux. " +
                "And average brightness is :"+avg+"luv";
        return  abnormal;
    }

    /**
     * Analyze air data
     */
    private String findAirAbnormal() {
        String abnormal = "";
        List<BigDecimal> airList = new ArrayList<>();

        double min = 0,max = 0, avg =0 ,current = 0;
        int abnormal_amount1 = 0,abnormal_amount2 = 0;
        double ratio = list.size();
        for (int i = 0; i < ratio; i++) {
            if(i == 0){
                current = list.get(i).getHCHO();
                max = current;
                min = current;
            }else{
                max = Math.max(max, current);
                min = Math.min(min, current);
        }
            //Number of general air quality data
            if(current > AIR_QUALITY_STAND1 && current<AIR_QUALITY_STAND2)
            {
            abnormal_amount1 ++;
            }
            //The number of people with very poor air quality
            else if(current>=AIR_QUALITY_STAND2){
                abnormal_amount2++;
            }
            airList.add(BigDecimal.valueOf(current));
            avg+=current;
        }
    avg /= ratio;
        //If the average air quality is greater than 35 and less than 45
        if(ruleService.isAvgHigher(AIR_QUALITY_STAND1,airList) && ruleService.isAvgLower(AIR_QUALITY_STAND2,airList))
            abnormal+="The data showed that the average air quality in the first three hours was in the normal range. " +
                    "However, this means air indoor is not very fresh. ";
        //If the average air quality is greater than 45
        else if(ruleService.isAvgHigher(AIR_QUALITY_STAND2,airList))
            abnormal+="The data showed that the average air quality in the first three hours was in the worse range. " +
                    "This means you have poor indoor air. ";
        else
            //If the air quality is below 35
            abnormal += "The average air quality data for the first three hours shows that air in your room is fresh.";

        abnormal += "According to statistic of air quality data, ";

        if(abnormal_amount1>0)
            abnormal+="The percentage of general quality air data to the total air quality data is: "+ (abnormal_amount1/ratio)+" ";
        if (abnormal_amount2>0)
            abnormal+="The percentage of worse data to the whole air quality data is :"+(abnormal_amount2/ratio)+" ";
        //Maximum, minimum, average
        abnormal +="In the worst case of all air quality data, the API(Air Pollution Index) reaches:"+max+". " +
                "In the best case of all air quality data, the lowest API value is:"+min+". " +
                "Overall, the average air pollution index is:" +avg+". ";
        return abnormal;
    }

    /**
     * Analyze the humidity data
     * @return analysis
     */
    private String findHumAbnormal() {
        String abnormal = "";
        List<BigDecimal> humList = new ArrayList<>();

        double min = 0,max = 0, avg =0 ,current = 0;
        int abnormal_amount1 = 0,abnormal_amount2 = 0,abnormal_amount3 =0,abnormal_amount4=0;
        double ratio = list.size();
        for (int i = 0; i < ratio; i++) {
            if(i == 0){
                current = list.get(i).getHumidity();
                max = current;
                min = current;
            }else{
                max = Math.max(max, current);
                min = Math.min(min, current);
            }
            //temperature is low
            if(current <HUM_STAND2)
            {
                //humidity is too low
                if(current < HUM_STAND1)
                    abnormal_amount1++;
                else
                    abnormal_amount2 ++;
            }
            //humidity is quite high
            else if(current > HUM_STAND3)
                //humidity is too high
                if(current > HUM_STAND4)
                    abnormal_amount4++;
                else
                    abnormal_amount3++;

            humList.add(BigDecimal.valueOf(current));
            avg+=current;
        }
        avg /= ratio;
        //if humidity is quite low
        if(ruleService.isAvgLower(HUM_STAND2,humList) && ruleService.isAvgHigher(HUM_STAND1,humList))
            abnormal+="Ambient humidity data for the first three hours indicate low humidity in your room.(Less than "+HUM_STAND2+" but higher than "+HUM_STAND1 +") ";
        //if humidity is too low
        else if(ruleService.isAvgLower(HUM_STAND1,humList))
            abnormal+="The ambient humidity data for the last three hours indicate that your indoor humidity is very low.(Less than "+HUM_STAND1+" on average) ";
        //if humidity is quite high
        else if(ruleService.isAvgLower(HUM_STAND4,humList)&& ruleService.isAvgHigher(HUM_STAND3,humList))
            abnormal += "The ambient humidity data for the last three hours indicate that the humidity in your room is a little high. The average is above "+HUM_STAND3+" but below "+HUM_STAND4+". ";
        //if humidity is too high
        else if(ruleService.isAvgHigher(HUM_STAND4,humList))
            abnormal+="The ambient humidity data for the last three hours indicate that the humidity in your room is too high to living. The average is higher than "+HUM_STAND4+". ";
        abnormal += "According to statistic of humidity, ";
        //percentage
        if(abnormal_amount1>0)
            abnormal+="In all environmental humidity data, the data showing the environment is very dry accounted for "+(abnormal_amount1/ratio)+"% of the total. ";
        if (abnormal_amount2>0)
            abnormal+="Data showing that the environment is partly dry accounts for about "+(abnormal_amount2/ratio)+"% of the total. ";
        if(abnormal_amount3>0)
            abnormal+="The percentage of all ambient humidity data showing that the environment is partly wet is "+(abnormal_amount3/ratio)+"%. ";
        if(abnormal_amount4>0)
            abnormal+="In the environmental humidity data, the data showing that the environment is damp accounts for "+(abnormal_amount4/ratio)+"% of the total data. ";
        abnormal +="Among all indoor humidity data, the maximum value in the wettest case is:"+max+" , and the minimum value in the driest case is:"+min+". In short, the average humidity of the humidity data is:"+avg+". ";


        return abnormal;
    }

}
