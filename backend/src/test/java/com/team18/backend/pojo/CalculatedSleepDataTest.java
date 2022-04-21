package com.team18.backend.pojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatedSleepDataTest {

    CalculatedSleepData caledSleepData;
    @BeforeEach
    void setUp() {
        caledSleepData = new CalculatedSleepData();
        caledSleepData.setDeepSleep(2.5);;
        caledSleepData.setParaSleep(5.0);
        caledSleepData.setDateIndex("2020-03-30 08:46");
    }

    @Test
    void getDeepSleep() {
        assertEquals(2.5
                ,caledSleepData.getDeepSleep());

    }

    @Test
    void setDeepSleep() {
        caledSleepData.setDeepSleep(6.3);
        assertEquals(6.3
        ,caledSleepData.getDeepSleep());
    }

    @Test
    void getParaSleep() {
        assertEquals(5.0
                ,caledSleepData.getParaSleep());
    }

    @Test
    void setParaSleep() {
        caledSleepData.setParaSleep(2.0);
        assertEquals(2.0
                ,caledSleepData.getParaSleep());
    }

    @Test
    void getDateIndex() {
        assertEquals("2020-03-30 08:46"
        ,caledSleepData.getDateIndex());
    }

}