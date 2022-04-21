package com.team18.backend.controller;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.service.EVDataService;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EVDController.class)
@Import(EVDataService.class)
@AutoConfigureMybatis
@AutoConfigureMockMvc
class EVDControllerTest {


    @Autowired
    MockMvc mvc;
    @MockBean
    EVDataMapper evDataMapper;


    @Test
    void evdMapper() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/getEVD")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //判断模拟http 请求的状态是否为请求已经成功 即状态码200
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(""
                ,response.getContentAsString());

    }

    @Test
    void getTemperature() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/tem")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //判断模拟http 请求的状态是否为请求已经成功 即状态码200
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[0.0,0.0,0.0,0.0,0.0]",response.getContentAsString());
    }

    @Test
    void getHumidity() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/hum")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //判断模拟http 请求的状态是否为请求已经成功 即状态码200
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[0.0,0.0,0.0,0.0,0.0]",response.getContentAsString());
    }

    @Test
    void getAirQuality() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/airQuality")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //判断模拟http 请求的状态是否为请求已经成功 即状态码200
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[0.0,0.0,0.0,0.0,0.0]",response.getContentAsString());

    }

    @Test
    void getBrightness() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/brightness")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //判断模拟http 请求的状态是否为请求已经成功 即状态码200
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[0.0,0.0,0.0,0.0,0.0]",response.getContentAsString());

    }

    @Test
    void getNoiseLevel() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/noise")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //判断模拟http 请求的状态是否为请求已经成功 即状态码200
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[0.0,0.0,0.0,0.0,0.0]",response.getContentAsString());

    }
}