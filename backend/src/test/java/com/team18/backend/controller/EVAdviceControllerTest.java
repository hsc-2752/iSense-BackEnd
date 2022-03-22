package com.team18.backend.controller;

import com.team18.backend.mapper.EVAdviceMapper;
import com.team18.backend.service.EnvAdviceService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(EVAdviceController.class)
@Import(EnvAdviceService.class)
@AutoConfigureMybatis
class EVAdviceControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    EVAdviceMapper adviceMapper;

    @Test
    void temAndHumAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/TemAndHum")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("At present, the indoor temperature is too low and very dry, which may lead to dry and cracked skin. It is recommended to apply some moisturizing skin care products and pay attention to keep warm."
                ,response.getContentAsString());

    }

    @Test
    void pressureAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/Pressure")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("Abnormal indoor air pressure may be caused by the lack of circulation of indoor and outdoor air, which is not suitable for daily life and may cause some minor impact on your health. It is recommended that you open the window for ventilation."
                ,response.getContentAsString());
    }

    @Test
    void noiseAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/Noise")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("The surroundings are now quiet enough for activities that require intense concentration. But I suggest you take it easy."
                ,response.getContentAsString());
    }

    @Test
    void brightnessAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/Brightness")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("Indoor light intensity is suitable for rest, wish you have a good night."
                ,response.getContentAsString());
    }

    @Test
    void airQualityAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/Advice/AirQuality")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("Indoor air quality is very good for human health."
                ,response.getContentAsString());
    }
}