package com.team18.backend.pojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeartDataTest {
    HeartData hrData;
    @BeforeEach
    void setUp() {
        hrData = new HeartData();
        hrData.setBOS(98);
        hrData.setHeartRate(78);
    }

    @Test
    void getHeartRate() {
        assertEquals(78
        ,hrData.getHeartRate());
    }

    @Test
    void getBOS() {
        assertEquals(98
        ,hrData.getBOS());
    }
}