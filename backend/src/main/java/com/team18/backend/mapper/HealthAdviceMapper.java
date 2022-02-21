package com.team18.backend.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface HealthAdviceMapper {
    /**
     * 取前十秒的数据（心率）的平均值
     */
    @Select("select avg(HeatRate) from arduinodb.mega_data " +
            "where MEGAid between " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1)-9" +
            " and " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1);")
    int getHRAverageData();

    /**
     * 取前十秒的数据（血氧）的平均值
     */
    @Select("select avg(BOS) from arduinodb.mega_data " +
            "where MEGAid between " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1)-9" +
            " and " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1);")
    int getBOSAverageData();

    /**
     * 获取最新一条bmi
     */
    @Select("select bmi from arduinodb.bmi_data order by timeIndex desc limit 1;")
    double getNewestBMI();





}
