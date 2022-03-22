package com.team18.backend.controller;

import com.team18.backend.mapper.HealthAdviceMapper;
import com.team18.backend.service.HealthAdviceService;
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


@WebMvcTest(HealthAdviceController.class)
@AutoConfigureMybatis
@AutoConfigureMockMvc
@Import(HealthAdviceService.class)
class HealthAdviceControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    HealthAdviceMapper adviceMapper;

    @Test
    void heartRateAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/HeartRate")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("DATA ERROR, CHECK YOUR DEVICE."
                ,response.getContentAsString());
    }

    @Test
    void bloodOxygenAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/BloodOxygen")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("Your oxygen saturation is very low and unsafe. Please go to the hospital immediately for examination."
                ,response.getContentAsString());

    }

    @Test
    void bmiAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/BMI")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("According to your latest BMI, your BMI is too low, please pay attention to eat regularly. A low BMI can lead to a weakened immune system, sagging stomach due to osteoporosis, gallstones and anemia."
                ,response.getContentAsString());
    }
}