package com.team18.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleModeServiceImplTest {

    List<BigDecimal> ascendList;
    List<BigDecimal> descendList;
    RuleModeService ruleModeService;

    @BeforeEach
    void setUp() {
        ruleModeService = new RuleModeServiceImpl();
        ascendList = new ArrayList<>();
        descendList = new ArrayList<>();
        int x = 0;
        while (x++<10){
            ascendList.add(BigDecimal.valueOf(x));
        }
        while (x-->1){
            descendList.add(BigDecimal.valueOf(x));
        }

    }

    @Test
    void isAscending() {
        assertTrue(ruleModeService.isAscending(ascendList));
        assertFalse(ruleModeService.isAscending(descendList));
    }

    @Test
    void isDescending() {
        assertFalse(ruleModeService.isAscending(descendList));
        assertTrue(ruleModeService.isAscending(ascendList));
    }

    @Test
    void isHigher() {
       assertTrue(ruleModeService.isHigher(0,descendList));
    }

    @Test
    void isLess() {
        assertTrue(ruleModeService.isLess(11,descendList));
    }

    @Test
    void isDifferN() {
        assertTrue(ruleModeService.isDifferN(9,descendList));
    }

    @Test
    void isAvgHigher() {
        assertTrue(ruleModeService.isAvgHigher(5,descendList));
    }

    @Test
    void isAvgLower() {
        assertTrue(ruleModeService.isAvgLower(6,ascendList));
    }
}