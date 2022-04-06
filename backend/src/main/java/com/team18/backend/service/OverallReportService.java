package com.team18.backend.service;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.mapper.HealthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A comprehensive analysis of heart rate and temperature, noise,
 * and air quality produces a report on the integration of environment and health
 */
@Service
public class OverallReportService {

    @Autowired
    private HealthMapper healthMapper;

    @Autowired
    private EVDataMapper evDataMapper;

    @Autowired
    private RuleModeService ruleModeService;


    private List<BigDecimal> tempList;

    private List<BigDecimal> airList;

    private List<BigDecimal> noiseList;

    private List<BigDecimal> hrList;

    //Temperature standard value
    private static final int COMFORT_STAND_TEMP = 25;
    private static final int MOST_DIFFER_TEMP = 5;

    //Air quality standard value
    private static final int NORMAL_LEVEL_AQ = 35;


    //decibel standard value
    private static final int NOISY_STAND = 95;
    private static final int QUITE_NOISY_AVG_STAND = 70;

    //Hreat rate standard value
    private static final int HIGH_HR_STAND = 90;
    private static final int LOW_HR_STAND = 60;



    public String getOverallReport(){
        return getHRReport();
    }


    /**
     According to the paper [1] LAN Li. Research on the influence mechanism and evaluation of indoor environment on personnel's work efficiency [D]. Shanghai Jiao Tong University,2010.
     * Witterseh, however, studied the effect of temperature on simulated office tasks and found that temperatures ranging from neutral to slightly warmer conditions had a significant effect
     * There is no significant impact on people's office efficiency (21-25); Lorsch proposed the existence of a critical temperature (32.2-35).
     * Indoor temperatures higher than this can significantly reduce performance on mental tasks. And according to [2] Zhang Yingjie. Experimental Study on the Effect of Temperature and CARBON dioxide on Human Comfort in Narrow Space [D]. Chongqing University,2018.
     * Heart rate increases as the temperature rises
     * That is, according to the temperature below 21, 21-25 range, 25-32.2 range, 32 above the four standard analysis.
     *
     * According to [1] Xie Xiaoxu. Health effects of long-term PM_(2.5) exposure on blood pressure and heart rate and its attributable risk in Chinese aged 20-49 years [D]. Beijing union medical college, 2019. DOI: 10.27648 /, dc nki. Gzxhu. 2019.000285.
     * PM2.5 and tachycardia showed a linear increase.
     * At the same temperature, in a poorly ventilated environment, the human body is always in a state of mental stress, which causes the heart rate to rise.
     * and [2] Yingjie Zhang. Experimental Study on the Effect of Temperature and CARBON dioxide on Human Comfort in Narrow Space [D]. Chongqing University,2018.
     *
     * Due to equipment limitations, only a comprehensive index of air quality can be obtained, which is analyzed according to three standards: excellent air quality below 35, good air quality between 35 and 45, and poor air quality above 45
     *
     *
     * According to [1] Yang Yaru. Indoor noise effects on job performance [D]. Zhejiang university of technology, 2020. The DOI: 10.27786 /, dc nki. GZJLG. 2020.000472.
     * [1] Jing Guoxun, Wang Yuansheng, Zhou Fei, Guo Shaoshuai. Effect of short-term noise exposure on heart rate of workers [J]. Industrial safety and environmental protection,2019,45(08):70-73.
     * (1) Noise stimulation will affect human heart rate, causing human heart rate to rise
     * high;
     * (2) The level of noise stimulation and exposure time were both opposite to the subjects
     * heart rate had a significant effect;
     * (3) Due to the adaptability of the human ear to noise, water in the same noise
     * Under flat stimulation, the heart rate will recover briefly as the exposure time increases
     * after;
     * (4) Under the noise level < 95 dB noise stimulation, with the violence
     * The increase of exposure time, people's adaptability to noise is more obvious, at this time
     *, 72,
     * Noise exposure time is the leading factor affecting human heart rate, and in noise
     * When exposed to 40 ~ 50 min, due to the adaptability of human ear to noise
     * There is a temporary recovery of heart rate and human fatigue.
     * (5) Under the noise level > 95 dB noise stimulation, with the violence
     * With the increase of exposure time, people have no obvious adaptability to noise, and the heart rate follows the noise
     * The increase in decibels is slowly rising, when the noise level is affected
     * The leading factor in human heart rate variation.
     *
     *
     * 1.1 Analyze whether the heart rate rises and the noise >95;
     * 1.2 When the noise <=95, whether the heart rate is not in a rising state
     * 2. Analyze whether the heart rate rises and the AIR quality index is higher than 35, and the difference between the maximum and minimum temperature is less than 5°
     * 3. Analyze whether the heart rate is increasing and the ambient temperature is higher than 25
     */
    private String getHRReport() {
        convertDataType();
        String hrReport = "";
        //Because the data from the database is in reverse order, we use the method to determine whether it's going down
        //When the data is rising continuously or too fast
        if (ruleModeService.isDescending(hrList) || ruleModeService.isHigher(HIGH_HR_STAND,hrList) ) {
            hrReport+= " Your heart rate is increasing or staying around a high value, which may be caused by the following reasons: ";
           if(ruleModeService.isHigher(NOISY_STAND, noiseList)){
               hrReport+=" Data shows that ambient noise is greater than 95 decibels within 15 minutes. " +
                       "According to research, when people are exposed to noise above 95 decibels for a long time, " +
                       "they have no obvious adaptation to noise and their heart rate will continue to increase.";
           }
           if( ruleModeService.isHigher(NORMAL_LEVEL_AQ, airList)){

               hrReport += " The data shows that your indoor air quality is average or even not very good. ";

               if(ruleModeService.isDifferN(MOST_DIFFER_TEMP ,tempList))
               {
                   hrReport +=" According to the research, when the person is in a certain range of temperature environment " +
                           "with little fluctuation and in the poor ventilation environment, the human experience is always in a state of mental tension, " +
                           "which causes the heart rate to rise.";
               }
               else if(ruleModeService.isHigher(COMFORT_STAND_TEMP,tempList))
               {
                   hrReport+=" Research shows that people who work indoors in warmer temperatures than they are comfortable with are less productive. " +
                           "You can even have a tachycardia.";
               }
               hrReport +=" And staying in a stuffy room for a long time may make people feel tired.";

           }else if(ruleModeService.isLess(NORMAL_LEVEL_AQ,airList)){
               hrReport +=" The data shows that the air in your room is fine now.";

               if(ruleModeService.isHigher(COMFORT_STAND_TEMP,tempList)){
                   hrReport += " Research shows that people who work indoors in warmer temperatures than they are comfortable with are less productive. " +
                           "You can even have a tachycardia.";
               }
                    hrReport+=" Aside from environmental causes, it could also be because you stayed up late last night or didn't get enough rest.";
           }
        }
        //Heart rate continues to drop
        else if(ruleModeService.isAscending(hrList)){
            if(!ruleModeService.isAvgHigher(NOISY_STAND,noiseList)){
                if(ruleModeService.isLess(NOISY_STAND,noiseList) && ruleModeService.isAvgHigher(QUITE_NOISY_AVG_STAND,noiseList) ){
                    hrReport+=" The data shows that your ambient noise for the first fifteen minutes is always less than 95 but on average greater than 70. " +
                            "According to research, people exposed to noise below 95 decibels experience a temporary recovery in heart rate due to ear adaptation to noise. " +
                            "But being in a noisy environment for a long time can make you feel tired.";
                }
            }
            hrReport+=" According to the cardiovascular medicine department of Affiliated Hospital of Yanbian University," +
                    " a case of heart rate drop caused by smoking accompanied by dizziness, chest pain and chest tightness was studied. " +
                    "Smoking can cause an immediate drop in heart rate. If you have smoked in the last 15 minutes, " +
                    "excluding environmental and physical conditions, your heart rate may have dropped in the last 15 minutes because of smoking.";

        }
        //The heart rate is neither continuously decreasing nor continuously increasing and is in the normal range
        else if(ruleModeService.isAvgHigher(LOW_HR_STAND,hrList) && !ruleModeService.isAvgHigher(HIGH_HR_STAND,hrList))
        {
            hrReport+= " Your average heart rate for the last 15 minutes is within the normal range.";
        }
        hrReport+=" For more detailed reports on environment and heart rate, go to the Environment or Health page.";
       return hrReport;
    }

    /**
     * Converting data types
     */
    private void convertDataType() {
        tempList = new ArrayList<>();
        airList = new ArrayList<>();
        noiseList = new ArrayList<>();
        hrList = new ArrayList<>();
        for (double temp: evDataMapper.findReportTemp() ) {
            tempList.add(new BigDecimal(temp));
        }
        for(double air:evDataMapper.findReportAirQuality()){
            airList.add(new BigDecimal(air));
        }
        for(double noise : evDataMapper.findReportNoise()){
            noiseList.add(new BigDecimal(noise));
        }
        for (double heartRate:  healthMapper.findReportHR()){
            hrList.add(new BigDecimal(heartRate));
        }
    }


}
