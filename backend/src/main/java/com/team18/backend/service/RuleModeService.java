package com.team18.backend.service;

import java.math.BigDecimal;
import java.util.List;

/**
 * It is used to judge whether the data continuously goes up, continuously goes down, above a certain value, below a certain value
 */
public interface RuleModeService {

    /**
     * Determines whether the list is continuously ascending
     */
    boolean isAscending(List<BigDecimal> list);

    /**
     * Determines whether the list is continuously descending
     */
    boolean isDescending(List<BigDecimal> list);

    /**
     * Determines whether the list is hi higher than an int
     */
    boolean isHigher(Integer ruleRestrictions, List<BigDecimal>list);


    /**
     * Determines whether the list is Lower than an int
     */
    boolean isLess(Integer ruleRestrictions, List<BigDecimal>list);

    /**
     * Determines the maximum and minimum of list is different from an int
     */
    boolean isDifferN(Integer differ, List<BigDecimal>list);

    /**
     * Determines the average of list is higher than an int
     */
    boolean isAvgHigher(Integer ruleRestrictions, List<BigDecimal>list);

    /**
     * Determines the average of list is lower than an int
     */
    boolean isAvgLower(Integer ruleRestrictions, List<BigDecimal>list);
}
