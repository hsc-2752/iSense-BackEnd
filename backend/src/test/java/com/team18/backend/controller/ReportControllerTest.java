package com.team18.backend.controller;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.mapper.HealthMapper;
import com.team18.backend.pojo.SleepData;
import com.team18.backend.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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


@WebMvcTest(ReportController.class)
@Import({EnvReportService.class,HealthReportService.class,SleepData.class,OverallReportService.class})
@AutoConfigureMybatis
@AutoConfigureMockMvc
class ReportControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    EVDataMapper evDataMapper;
    @MockBean
    EVDataService evDataService;
    @MockBean
    RuleModeService ruleModeService;
    @MockBean
    SleepService sleepService;
    @MockBean
    HealthMapper healthMapper;


    @Test
    void environmentReport() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/getEVReport")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("The environment condition is perfect!!!! According to statistic of humidity, Among all indoor humidity data, the maximum value in the wettest case is:0.0 , and the minimum value in the driest case is:0.0. In short, the average humidity of the humidity data is:NaN. The average air quality data for the first three hours shows that air in your room is fresh.According to statistic of air quality data, In the worst case of all air quality data, the API(Air Pollution Index) reaches:0.0. In the best case of all air quality data, the lowest API value is:0.0. Overall, the average air pollution index is:NaN. According to statistic of indoor light data, In all indoor light data, the strongest light reaches:0.0 lux, and the lowest indoor light is:0.0lux. And average brightness is :NaNluvAccording to statistics of noisy data, In the environmental decibel data of the first three hours, the highest value is:0.0 , and the lowest value is:0.0 And the average value is :NaNSlightly higher barometric data during the first three hours account for the total NaN%In the indoor air pressure data of the first three hours, the highest value is: 0.0, and the lowest value is:0.0 And the average value is:NaNAll other conditions are normal!"
                ,response.getContentAsString());
    }

    @Test
    void healthReport() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/getHealReport")
                        .param("startTime","2022-03-22 22:48:40")
                        .param("endTime","2022-03-23 08:56:41")
                        .param("isAwaken","false")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("Your health condition is generally good, but some aspect can be improved! In the past 15 minutes,  HR data ErrorIn the past 15 minutes, BOS data errorYou had a really good sleep! Congratulations!All other conditions are very good!"
                ,response.getContentAsString());

    }

    @Test
    void overallReport() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/getOverallReport")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(" For more detailed reports on environment and heart rate, go to the Environment or Health page."
                ,response.getContentAsString());

    }
}