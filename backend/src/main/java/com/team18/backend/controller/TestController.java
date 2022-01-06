package com.team18.backend.controller;


import com.team18.backend.mapper.TestMapper;
import com.team18.backend.pojo.Tester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestMapper testMapper;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Tester> testMapper(){
        List<Tester> testers = testMapper.findAll();
        return testers;
    }
}
