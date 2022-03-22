package com.team18.backend.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 只用于advice的对数据库的操作
 */
@Repository
public interface EVAdviceMapper {

    /**
     * 取前十秒的数据（温度）的平均值
     */
    @Select("select avg(temp) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getTempAdviceData();

    /**
     * 取前十秒的数据（湿度）的平均值
     */
    @Select("select avg(humidity) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getHumAdviceData();

    /**
     * 取前十秒的数据（气压）的平均值
     */
    @Select("select avg(pressure) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getPressureAdviceData();

    /**
     * 取前十秒的数据（噪音）的平均值
     */
    @Select("select avg(voice) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getNoiseAdviceData();

    /**
     * 取前十秒的数据（亮度）的平均值
     */
    @Select("select avg(brightness) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getBrightAdviceData();

    /**
     * 取前十秒的数据（空气质量）的平均值
     */
    @Select("select avg(HCHO) from arduinodb.mega_data " +
            "where MEGAid between " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1)-9" +
            " and " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1);")
    double getAirAdviceData();
}
