package com.team18.backend.controller;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.service.EnvReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EVReportController {

    /**
     * Obtain overall report from service layer, return it to the front end
     * @return overall report
     */
    @Autowired
    EVDataMapper evDataMapper;
    @RequestMapping(value = "/getEVReport",method = RequestMethod.GET)
    public String overallReport(){
        EnvReportService reportService = new EnvReportService(evDataMapper.reportData());
        return reportService.getReport();
    }


}
