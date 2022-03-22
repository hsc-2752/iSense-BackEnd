package com.team18.backend.service;

import com.team18.backend.mapper.HealthAdviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class HealthAdviceService {

    @Autowired
    private HealthAdviceMapper adviceMapper;

    //心率标准值
    private static final int NORMAL_HR_STAND = 60;
    private static final int SLOW_HR_STAND = 45;
    private static final int FAST_HR_STAND = 160;

    //血氧标准值
    private static final int FULL_BOS_STAND = 100;
    private static final int NORMAL_BOS_STAND = 95;
    private static final int LOW_BOS_STAND = 90;

    //bmi标准值
    private static final double SKINNY_STAND = 18.5;
    private static final double NORMAL_STAND = 24.9;
    private static final double OVERWEIGHT_STAND = 25.0;
    private static final double  PRE_OBESE_STAND= 29.9;
    private static final double  OBESE_STAND= 30.0;


    /**
     * 获取心率有关建议
     * @return
     */
    public String getHRAdvice(){
        int heartRate = adviceMapper.getHRAverageData();
        return heartRateCalculate(heartRate);

    }

    /**
     * 分析十秒内的心率平均数据得出建议
     */
    private String heartRateCalculate(int heartRate) {
        if(heartRate>SLOW_HR_STAND && heartRate < NORMAL_HR_STAND){
            return "Your heart rate is too slow, " +
                    "and you may experience symptoms of dizziness, fatigue, fatigue, and lethargy. " +
                    "Please seek medical attention as soon as possible.";
        }
        else if(heartRate > NORMAL_HR_STAND && heartRate < FAST_HR_STAND){
            return "Your heart rate is normal.";
        }
        else if(heartRate > FAST_HR_STAND){
            return "Your heart rate is too fast, " +
                    "if you are not in the situation of strenuous exercise, " +
                    "and you have palpitations, chest tightness and other discomfort. " +
                    "Please go to the hospital for a detailed examination to prevent heart disease.";
        }
        else
            return "DATA ERROR, CHECK YOUR DEVICE.";
    }

    /**
     * 获取血氧有关建议
     */
    public String getBOSAdvice(){
        int bos = adviceMapper.getBOSAverageData();
        return bloodCalculate(bos);
    }

    /**
     * 获取bmi有关建议
     */
    public String getBMIAdvice(){
        double bmi = adviceMapper.getNewestBMI();
        return bmiCalculate(bmi);
    }

    /**
     * 对最新的一条bmi进行分析
     */
    private String bmiCalculate(double bmi) {
        if(bmi < SKINNY_STAND){
            return "According to your latest BMI, your BMI is too low, " +
                    "please pay attention to eat regularly and regularly. " +
                    "A low BMI can lead to a weakened immune system, " +
                    "sagging stomach due to osteoporosis, gallstones and anemia.";
        }
        else if(bmi >= SKINNY_STAND && bmi <= NORMAL_STAND){
            return "According to your latest BMI, your BMI is normal. Please keep it up. " +
                    "It is recommended that you work and sleep regularly every day, " +
                    "eat and exercise properly at ordinary times.";
        }
        else if(bmi >= OVERWEIGHT_STAND && bmi<PRE_OBESE_STAND){

            return "According to your latest BMI, your BMI is normal. Please keep it up. " +
                    "It is recommended that you work and sleep regularly every day, " +
                    "eat and exercise properly at ordinary times.";
        }
        else if(bmi > PRE_OBESE_STAND && bmi < OBESE_STAND){
            return "According to your latest BMI, your BMI index has reached pre-obesity. " +
                    "In this range, obesity is more likely than being overweight. " +
                    "It is recommended to eat a light diet, " +
                    "don't stay up late and keep exercising for more than half an hour at least once or twice a week.";
        }
        else if(bmi > OBESE_STAND)
        {
            return "According to your latest BMI, your BMI index has reached the obese range. " +
                    "Studies have shown that people with a BMI of 30 or above are at increased risk for cancer, " +
                    "heart disease, respiratory disease, kidney disease and diabetes. " +
                    "It is recommended that you seek medical attention as soon as possible and make a weight loss plan.";
        }
        return "DATA ERROR, CHECK YOUR INPUT.";
    }

    /**
     * 分析十秒内的血氧数据得出建议
     */
    private String bloodCalculate(int bos) {
        if(bos <= FULL_BOS_STAND && bos >= NORMAL_BOS_STAND)
        {
            return "Please rest assured that your oxygen saturation is normal and healthy.";
        }
        else if(bos <NORMAL_BOS_STAND && bos > LOW_BOS_STAND){
            return "Your blood oxygen saturation is low, your body may have some hypoxia, " +
                    "which may be caused by staying up late. If the blood oxygen continues to be low, " +
                    "please see a doctor immediately.";
        }
        else{
            return "Your oxygen saturation is very low and unsafe. Please go to the hospital immediately for examination.";
        }

    }



}
