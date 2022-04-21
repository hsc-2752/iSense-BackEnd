package com.team18.backend.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * This mapper consists of all health advice operation SQL
 */
@Repository
public interface HealthAdviceMapper {
    /**
     * Take the average of the previous 10 seconds (heart rate)
     */
    @Select("select avg(HeatRate) from arduinodb.mega_data " +
            "where MEGAid between " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1)-9" +
            " and " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1);")
    int getHRAverageData();

    /**
     * Take the average of the previous 10 seconds of data (blood oxygen)
     */
    @Select("select avg(BOS) from arduinodb.mega_data " +
            "where MEGAid between " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1)-9" +
            " and " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1);")
    int getBOSAverageData();

    /**
     * Get the latest BMI
     */
    @Select("select bmi from arduinodb.bmi_data order by timeIndex desc limit 1;")
    double getNewestBMI();


}
