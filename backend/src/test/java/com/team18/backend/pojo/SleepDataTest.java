package com.team18.backend.pojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SleepDataTest {
    SleepData sleep;
    @BeforeEach
    void setUp() {
        sleep = new SleepData();
        sleep.setStartTime("2020-03-29 22:00");
        sleep.setEndTime("2020-03-30 06:23");
    }

    @Test
    void getStartTime() {
        assertEquals("2020-03-29 22:00"
        ,sleep.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals("2020-03-30 06:23"
                ,sleep.getEndTime());
    }
}