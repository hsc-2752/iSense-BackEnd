package com.team18.backend.service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用于判断连续上升，连续下降，高于某个值，低于某个值
 */
public interface RuleModeService {

    /**
     * 是否连续上涨
     * @param list
     * @return
     */
    boolean isAscending(List<BigDecimal> list);

    /**
     * 是否连续下降
     * @param list
     * @return
     */
    boolean isDescending(List<BigDecimal> list);

    /**
     * 是否高于
     * @param ruleRestrictions
     * @param list
     * @return
     */
    boolean isHigher(Integer ruleRestrictions, List<BigDecimal>list);


    /**
     * 是否低于
     */
    boolean isLess(Integer ruleRestrictions, List<BigDecimal>list);

    /**
     * 最高值与最小值是否相差n
     */
    boolean isDifferN(Integer differ, List<BigDecimal>list);

    /**
     * 平均值是否高于
     */
    boolean isAvgHigher(Integer ruleRestrictions, List<BigDecimal>list);

    /**
     * 是否平均低于
     */
    boolean isAvgLower(Integer ruleRestrictions, List<BigDecimal>list);
}
