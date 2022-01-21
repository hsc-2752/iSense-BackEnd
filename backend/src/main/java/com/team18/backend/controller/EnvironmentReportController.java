package com.team18.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EnvironmentReportController {

    /**
     * Obtain overall report from service layer, return it to the front end
     * @return overall report
     */
    @RequestMapping(value = "/getEVReport",method = RequestMethod.GET)
    public String overallReport(){
        return "overall report";
    }

//  separate reports
//    /**
//     * Obtain temperature and humidity part report from service layer, return it to the front end
//     * @return temperature and humidity report
//     */
//    @RequestMapping(value = "/getEVReport/TemAndHum",method = RequestMethod.GET)
//    public String temAndHumReport(){
//        return "temperature and humidity report";
//    }
//
//    /**
//     * Obtain pressure report from service layer, return it to the front end
//     * @return pressure report
//     */
//    @RequestMapping(value = "/getEVReport/Pressure",method = RequestMethod.GET)
//    public String pressureReport(){
//        return "pressure report";
//    }
//
//    /**
//     * Obtain noise report from service layer, return it to the front end
//     * @return noise report
//     */
//    @RequestMapping(value = "/getEVReport/Noise",method = RequestMethod.GET)
//    public String noiseReport(){
//        return "noise report";
//    }
//
//    /**
//     * Obtain brightness report from service layer, return it to the front end
//     * @return brightness report
//     */
//    @RequestMapping(value = "/getEVReport/Brightness",method = RequestMethod.GET)
//    public String brightnessReport(){
//        return "brightness report";
//    }
//    /**
//     * Obtain Air Quality report from service layer, return it to the front end
//     * @return air quality report
//     */
//    @RequestMapping(value = "/getEVReport/AirQuality", method = RequestMethod.GET)
//    public String airQualityReport(){
//        return "air quality report";
//    }
}
