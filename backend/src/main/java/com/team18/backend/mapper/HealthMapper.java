package com.team18.backend.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.HuData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Mapper
public interface HealthMapper {
    //TODO: add SQL statement
    @Select("SELECT HeartRate, BOS from ArduinoDB.MEGA_data order by MEGAid desc limit 1;")
    List<HeartData> findAll();



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
     * 获取十五分钟内所有血氧值
     */
    @Select(" SELECT BOS+" +
            " FROM arduinodb.mega_data " +
            " ORDER BY MEGAid DESC LIMT 900")
    List<String> findReportBOS();

    /**
     *获取十五分钟内的心率平均值
     */
    @Select("SELECT AVG(HeartRate)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{hrTime})  " +
            "AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{hrTime}) -900;")
    String findHR(@Param("hrTime") String hrTime);

    /**
     * 获取十五分钟内所有心率值
     */
    @Select(" SELECT HearRate+" +
            " FROM arduinodb.mega_data " +
            " ORDER BY MEGAid DESC LIMT 900")
    List<String> findReportHR();

    /**
     * 存入bmi数据
     */
    @Insert("INSERT INTO arduinodb.bmi_data (bmi,timeIndex) VALUE (#{bmi},#{time})")
    void storeBMI(@Param("bmi")double bmi, @Param("time")String time);

    /**
     * 取bmi数据
     */
    @Select("SELECT timeIndex, bmi FROM arduinodb.bmi_data")
    List<HuData>  getBMI();

    /**
     * 存入睡眠时间
     */
    @Insert("INSERT INTO arduinodb.sleep_data (deepSleep, paraSleep, dateIndex) VALUE (#{deepSleep}, #{paraSleep},#{dateIndex})")
    void storeSleep(@Param("deepSleep") double deepSleep, @Param("paraSleep") double paraSleep, @Param("dateIndex") Date dateIndex);

    /**
     * 取出睡眠时间
     */
    //TODO 和前端讨论作图的横坐标
    //@Select("SELECT deepSleep, paraSleep from arduinodb.sleep_data ")

}
