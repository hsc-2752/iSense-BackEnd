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
        int count = 0;
        if(CollectionUtil.isEmpty(list)){
            return false;
        }
        boolean result = true;
        BigDecimal prev = Convert.toBigDecimal(Double.MIN_VALUE);
        for (BigDecimal number:list) {
            if(CompareUtil.compare(number,prev)<=0){
                if(CompareUtil.compare(number,prev)<=number.add(number).negate().intValue()){
                   count+=2;
                }
                else{
                    count++;
                }
                //break;
            }
            //如果不符合的太多，为false
            if(count> (list.size()/2)){
                result = false;
                break;
            }
            prev = number;
        }
        return result;
    }

    @Override
    public boolean isDescending(List<BigDecimal> list) {
        int count = 0;
        if (CollectionUtil.isEmpty(list)) {
            return false;
        }
        boolean result = true;
        BigDecimal prev = Convert.toBigDecimal(Double.MAX_VALUE);
        for (BigDecimal number : list) {
            if(CompareUtil.compare(number, prev) >= 0){
                if(CompareUtil.compare(number,prev)>number.add(number).intValue()){
                    count+=2;
                }
                else {
                    count++;
                }
            }
            if(count>(list.size())/2){
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

    @Override
    public boolean isDifferN(Integer differ, List<BigDecimal> list) {
        if(Objects.isNull(list) || Objects.isNull(differ)){
            return false;
        }
        return CompareUtil.compare(list.stream().max(Comparable::compareTo).get(),list.stream().min(Comparable::compareTo).get())>differ;
    }

    @Override
    public boolean isAvgHigher(Integer ruleRestrictions, List<BigDecimal> list) {
        if (Objects.isNull(ruleRestrictions) || CollectionUtil.isEmpty(list)) {
            return false;
        }
        BigDecimal sum = BigDecimal.valueOf(0);
        for (BigDecimal number: list) {
            sum = sum.add(number);
        }
        return CompareUtil.compare(sum.divide(BigDecimal.valueOf(list.size())),Convert.toBigDecimal(ruleRestrictions))>0;

    }

    @Override
    public boolean isAvgLower(Integer ruleRestrictions, List<BigDecimal> list) {
        if(Objects.isNull(ruleRestrictions) || CollectionUtil.isEmpty(list)){
            return false;
        }
        BigDecimal sum = BigDecimal.valueOf(0);
        for (BigDecimal number : list){
            sum = sum.add(number);
        }
        return CompareUtil.compare(sum.divide(BigDecimal.valueOf(list.size())),Convert.toBigDecimal(ruleRestrictions))>0;
    }
}
