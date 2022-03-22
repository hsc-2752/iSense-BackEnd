package com.team18.backend.controller;

import com.team18.backend.mapper.EmotionMapper;
import com.team18.backend.service.EmotionService;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(EmotionController.class)
@AutoConfigureMybatis
class EmotionControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmotionService emotionService;
    @MockBean
    private EmotionMapper emoMapper;

    @Test
    void storeEmotion() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.post("/emotion/storeEmotion").param("emotion","1"));
        given(this.emotionService.getNewestEmotion()).willReturn(1);

    }

    @Test
    void getEEGEmotion() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(get("/emotion/getEEGEmotion")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(),HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),
                emoMapper.getEmotionByEEG()+"");
    }

    @Test
    void getEmoAdvice() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(get("/emotion/getEmoAdvice")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(),HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),
               "");

    }
}