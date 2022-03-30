package com.team18.backend.pojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BMIDataTest {
    BMIData bmi;

    @BeforeEach
    void setUp() {
        bmi = new BMIData();
        bmi.setBmi(24.789);
        bmi.setTimeIndex("2020-2-12 08:20");
    }

    @Test
    void getTimeIndex() {
        assertEquals("2020-2-12 08:20"
        ,bmi.getTimeIndex());
    }

    @Test
    void getBmi() {
        assertEquals(24.789
        ,bmi.getBmi());
    }
}