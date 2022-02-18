package com.team18.backend.service;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.mapper.HealthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class OverallReportService {

    @Autowired
    private HealthMapper healthMapper;

    @Autowired
    private EVDataMapper evDataMapper;

    private List<Double> tempList;

    private List<Double> airList;

    private List<Double> noiseList;

    private List<Double> hrList;

    //TODO eeg 待实现
    public String getOverallReport(){
        return getHRReport()+
        getEEGReport();
    }

    private String getEEGReport() {
     //   tempList = healthMapper.findReportHR();
        return "implementing..";
    }

    private String getHRReport() {
        return "implement later";
    }


}
