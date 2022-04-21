package com.team18.backend.pojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentDataTest {

    EnvironmentData envData;
    @BeforeEach
    void setUp() {
        envData = new EnvironmentData();
        envData.setBrightness(200);
        envData.setHCHO(36);
        envData.setHumidity(90);
        envData.setTemp(30);
        envData.setVoice(60);
        envData.setPressure(500);
    }

    @Test
    void getTemp() {
        assertEquals(30,
                envData.getTemp());
    }

    @Test
    void getHumidity() {
        assertEquals(90
        ,envData.getHumidity());
    }

    @Test
    void getHCHO() {
        assertEquals(36
        ,envData.getHCHO());
    }

    @Test
    void getBrightness() {
        assertEquals(200
        ,envData.getBrightness());
    }

    @Test
    void getVoice() {
        assertEquals(60
        ,envData.getVoice());
    }

    @Test
    void getPressure() {
        assertEquals(500
        ,envData.getPressure());
    }
}