package com.team18.backend.controller;

import com.team18.backend.mapper.EmotionMapper;
import com.team18.backend.service.EmotionService;
import com.team18.backend.service.RuleModeService;
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
import static org.mockito.BDDMockito.given;


@AutoConfigureMybatis
@AutoConfigureMockMvc
@WebMvcTest(EmotionController.class)
@Import(EmotionService.class)
class EmotionControllerTest {
    @Autowired
     MockMvc mvc;

    @MockBean
     RuleModeService ruleModeService;
    @MockBean
     EmotionMapper emoMapper;

    @Test
    void storeEmotion() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.post("/emotion/storeEmotion")
                .param("emotion","1"));

        given(this.emoMapper.getNewestEmotion()).willReturn(1);

    }

    @Test
    void getEEGEmotion() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/emotion/getEEGEmotion")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(),HttpStatus.OK.value());
        assertEquals("0"
                ,response.getContentAsString());
    }

    @Test
    void getEmoAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.get("/emotion/getEmoAdvice")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(),HttpStatus.OK.value());
        assertEquals("Your average mood is lower than the negative mood index recently, may I ask what happened?"
                ,response.getContentAsString());

    }
}