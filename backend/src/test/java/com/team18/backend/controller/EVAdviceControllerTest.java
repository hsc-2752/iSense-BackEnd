package com.team18.backend.controller;

import com.team18.backend.service.EnvAdviceService;
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
@WebMvcTest(EVAdviceController.class)
@AutoConfigureMybatis
class EVAdviceControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    EnvAdviceService adviceService;

    @Test
    void temAndHumAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/TemAndHum")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),"");

    }

    @Test
    void pressureAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/Pressure")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),"");
    }

    @Test
    void noiseAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/Noise")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),"");
    }

    @Test
    void brightnessAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/Brightness")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),"");
    }

    @Test
    void airQualityAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/AirQuality")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),"");
    }
}