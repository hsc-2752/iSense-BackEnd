package com.team18.backend.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.convert.Convert;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Service("ruleModeService")
public class RuleModeServiceImpl implements RuleModeService{
    @Override
    public boolean isAscending(List<BigDecimal> list) {
        if(CollectionUtil.isEmpty(list)){
            return false;
        }
        boolean result = true;
        BigDecimal prev = Convert.toBigDecimal(Double.MIN_VALUE);
        for (BigDecimal number:list) {
            if(CompareUtil.compare(number,prev)<=0){
                result = false;
                break;
            }
            prev = number;
        }
        return result;
    }

    @Override
    public boolean isDescending(List<BigDecimal> list) {
        if (CollectionUtil.isEmpty(list)) {
            return false;
        }
        boolean result = true;
        BigDecimal prev = Convert.toBigDecimal(Double.MAX_VALUE);
        for (BigDecimal number : list) {
            if(CompareUtil.compare(number, prev) >= 0){
                result = false;
                break;
            }
            prev = number;
        }
        return result;
    }

    @Override
    public boolean isHigher(Integer ruleRestrictions, List<BigDecimal> list) {
        if (Objects.isNull(ruleRestrictions) || CollectionUtil.isEmpty(list)) {
            return false;
        }
        return CompareUtil.compare(list.stream().min(Comparable::compareTo).get(), Convert.toBigDecimal(ruleRestrictions)) > 0;
    }

    @Override
    public boolean isLess(Integer ruleRestrictions, List<BigDecimal> list) {
        if (Objects.isNull(ruleRestrictions) || CollectionUtil.isEmpty(list)) {
            return false;
        }
        return CompareUtil.compare(list.stream().max(Comparable::compareTo).get(), Convert.toBigDecimal(ruleRestrictions)) < 0;
    }
}
