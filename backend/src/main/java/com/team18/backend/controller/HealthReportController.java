package com.team18.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthReportController {

    /**
     * Obtain health overall report from service layer, return it to the front end
     */
    @RequestMapping(value = "/getHealReport",method = RequestMethod.GET)
    public String overallReport(){
        return"Health overall report";
    }

//    separate health reports
//    /**
//     * Obtain heart rate report from service layer, return it to front end
//     * @return heart rate report
//     */
//    @RequestMapping(value = "/getHealReport/HeartRate",method = RequestMethod.GET)
//    public String heartRateReport(){
//        return "Heart rate report";
//    }
//
//    /**
//     * Obtain blood oxygen report from service layer, return it to front end
//     * @return blood oxygen report
//     */
//    @RequestMapping(value ="/getHealReport/BloodOxygen",method = RequestMethod.GET)
//    public String bloodOxygenReport(){
//        return "Blood Oxygen report";
//    }
//
//    /**
//     * Obtain BMI report from service layer, return it to front end
//     * @return BMI report
//     */
//    @RequestMapping(value = "/getHealReport/BMI",method = RequestMethod.GET)
//    public String bmiReport(){
//        return "BMI report";
//    }
//
//    /**
//     * Obtain sleep quality report from service layer, return it to front end
//     * @return sleep quality report
//     */
//    @RequestMapping(value = "/getHealReport/SleepQuality",method = RequestMethod.GET)
//    public String sleepReport(){
//        return "sleep quality report";
//    }
//    /**
//     * Obtain emotion report from service layer, return it to front end
//     * @return emotion report
//     */
//    @RequestMapping(value = "/getHealReport/Emotion",method = RequestMethod.GET)
//    public String emotionReport(){
//        return "emotion report";
//    }
}
