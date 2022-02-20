package com.team18.backend.controller;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.SleepData;
import com.team18.backend.service.EnvReportService;
import com.team18.backend.service.HealthReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class ReportController {

    @Autowired
    private EnvReportService envReportService;
    /**
     * 获取环境报告
     * @return 返回给前端的环境报告
     */
    @RequestMapping(value = "/getEVReport",method = RequestMethod.GET)
    public String environmentReport(){
        return envReportService.getReport();
    }

    @Autowired
    private HealthReportService healthReportService;
    @Autowired
    private SleepData sleepData;
    /**
     * 获得健康报告， 需要传入睡眠的开始和结束时间和是否被吵醒的布尔值
     *
     * @return 评估今天的睡眠，将其传入睡眠报告service
     * 向前端返回得到的睡眠报告
     */
    @RequestMapping(value = "/getHealReport",method = RequestMethod.POST)
    public String healthReport(@RequestParam("startTime")String startTime,
                               @RequestParam("endTime")String endTime,
                               @RequestParam("isAwaken")boolean isAwaken){
        sleepData.setStartTime(startTime);
        sleepData.setEndTime(endTime);
        return healthReportService.getReport(isAwaken);
    }
}
