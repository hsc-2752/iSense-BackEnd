package com.team18.backend.controller;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import com.team18.backend.service.EVDataService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EVDController.class)
@Import(EVDataService.class)
@AutoConfigureMybatis
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
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(""
                ,response.getContentAsString());

    }

    @Test
    void getTemperature() throws Exception {
        List<Double> list = new ArrayList<>();
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/tem")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[null,null,null,null,null]",response.getContentAsString());
    }

    @Test
    void getHumidity() throws Exception {
        List<Double> list = new ArrayList<>();
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/hum")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[null,null,null,null,null]",response.getContentAsString());
    }

    @Test
    void getAirQuality() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/airQuality")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[null,null,null,null,null]",response.getContentAsString());

    }

    @Test
    void getBrightness() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/brightness")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[null,null,null,null,null]",response.getContentAsString());

    }

    @Test
    void getNoiseLevel() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getTime/noise")
                        .param("count","5")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("[null,null,null,null,null]",response.getContentAsString());

    }
}