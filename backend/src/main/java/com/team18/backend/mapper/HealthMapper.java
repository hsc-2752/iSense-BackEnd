package com.team18.backend.mapper;

import com.team18.backend.pojo.HealthData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface HealthMapper {
    //TODO: add SQL statement
    @Select("SELECT HeartRate, BOS from ArduinoDB.MEGA_data order by MEGAid limit 1;")
    List<HealthData> findAll();



    //TODO add SQL statement
    @Select("")
    String findSleep();

    /**
     *获取十五分钟内的血氧平均值
     */
    @Select("SELECT AVG(BOS)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{bosTime})  " +
            "AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{bosTime}) -900;")
    String findBOS(@Param("bosTime") String bosTime);

    /**
     *获取十五分钟内的心率平均值
     */
    @Select("SELECT AVG(HeartRate)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{hrTime})  " +
            "AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{hrTime}) -900;")
    String findHR(@Param("hrTime") String hrTime);

    /**
     * 获取一天的bmi/bmi平均值,待补充
     */
    @Select("")
    String findBMI(@Param("bmiTime") String bmiTime);

}
