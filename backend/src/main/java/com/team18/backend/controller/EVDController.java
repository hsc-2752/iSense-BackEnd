package com.team18.backend.controller;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EVDController {
    @Autowired
    EVDataMapper evDataMapper;

    @RequestMapping(value = "/getEVD",method  = RequestMethod.GET)
    @ResponseBody
    public List<EnvironmentData> evdMapper() {
        List<EnvironmentData> environmentData = evDataMapper.findAll();
        return environmentData;
    }
}
