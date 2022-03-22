package com.team18.backend.controller;

import com.team18.backend.service.EVDataService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EVDController.class)
@AutoConfigureMybatis
class EVDControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    EVDataService dataService;
    @Test
    void evdMapper() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/getEVD")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),"");

    }

    @Test
    void getTemperature() {
    }

    @Test
    void getHumidity() {
    }

    @Test
    void getAirQuality() {
    }

    @Test
    void getBrightness() {
    }

    @Test
    void getNoiseLevel() {
    }
}