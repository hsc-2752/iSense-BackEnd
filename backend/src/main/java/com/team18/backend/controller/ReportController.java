package com.team18.backend.controller;

import com.team18.backend.pojo.SleepData;
import com.team18.backend.service.EnvReportService;
import com.team18.backend.service.HealthReportService;
import com.team18.backend.service.OverallReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller controls all report connection
 */
@RestController
public class ReportController {


     EnvReportService envReportService;
     HealthReportService healthReportService;
     SleepData sleepData;
     OverallReportService overallReportService;

    @Autowired
    ReportController (EnvReportService envReportService,
                      HealthReportService healthReportService,
                      SleepData sleepData,
                      OverallReportService overallReportService){
        this.envReportService = envReportService;
        this.healthReportService = healthReportService;
        this.sleepData = sleepData;
        this.overallReportService = overallReportService;
    }

    /**
     * gain environment report
     * @return return report to client
     */
    @RequestMapping(value = "/getEVReport",method = RequestMethod.GET)
    public String environmentReport(){
        return envReportService.getReport();
    }



    /**
     * To get a health report, you need to pass in a Boolean value for the start and end times of sleep
     * and whether or not you were woken up
     *
     * @return Assess your sleep for the day and pass it into the sleep reporting service
     * The anteriorly returned sleep report is obtained
     */
    @RequestMapping(value = "/getHealReport",method = RequestMethod.POST)
    public String healthReport(@RequestParam("startTime")String startTime,
                               @RequestParam("endTime")String endTime,
                               @RequestParam("isAwaken")boolean isAwaken){
        sleepData.setStartTime(startTime);
        sleepData.setEndTime(endTime);
        return healthReportService.getReport(isAwaken);
    }


    /**
     * gain overall report
     * @return overall report
     */
    @RequestMapping(value = "/getOverallReport",method = RequestMethod.GET)
    public String overallReport(){
        return overallReportService.getOverallReport();
    }
}
