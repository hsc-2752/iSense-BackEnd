package com.team18.backend.mapper;

import com.team18.backend.pojo.CalculatedSleepData;
import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.HuData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HealthMapper {
    /**
     * 选择最新一条心跳血氧数据
     * @return 返回存有这两种数据的list
     */
    @Select("SELECT HeartRate,BOS FROM ArduinoDB.MEGA_data" +
            " ORDER BY MEGAid DESC LIMIT 1;")
    HeartData findAll();



    /**
     *获取十五分钟内的血氧平均值
     */
    //TODO 开发测试时为 5， 正式测试时改为 15*60*60
    @Select("SELECT AVG(BOS)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data" +
            " WHERE TimeIndex = #{bosTime})" +
            " AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{bosTime}) -5;")
    Double findBOS(@Param("bosTime") String bosTime);

    /**
     * 获取十五分钟内所有血氧值
     */
    //TODO 开发测试时limit50， 正式测试时改回limit 900
    @Select("SELECT BOS" +
            " FROM arduinodb.mega_data" +
            " ORDER BY MEGAid DESC LIMIT 50;")
    List<Double> findReportBOS();

    /**
     *获取十五分钟内的心率平均值
     */
    //TODO 开发测试时为 5， 正式测试时改为 15*60*60
    @Select(" SELECT AVG(HeartRate)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{hrTime})  " +
            " AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{hrTime}) -900;")
    Double findHR(@Param("hrTime") String hrTime);

    /**
     * 获取十五分钟内所有心率值,倒序
     */
    //TODO 开发测试时limit50， 正式测试时改回limit 900
    @Select("SELECT HearTRate " +
            "FROM arduinodb.mega_data " +
            "ORDER BY MEGAid DESC LIMIT 50")
    List<Double> findReportHR();

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
    void storeSleep(@Param("deepSleep") double deepSleep, @Param("paraSleep") double paraSleep, @Param("dateIndex") String dateIndex);

    /**
     * 更新睡眠时间
     */
    @Update( "UPDATE arduinodb.sleep_data SET deepSleep=#{deepSleep},paraSleep=#{paraSleep} WHERE dateIndex=#{dateIndex}")
    void updateSleep(@Param("deepSleep") double deepSleep, @Param("paraSleep") double paraSleep, @Param("dateIndex") String dateIndex);
    /**
     * 取出n个睡眠时间
     */
    @Select("SELECT * FROM arduinodb.sleep_data ORDER BY dateIndex desc limit #{count};")
    List<CalculatedSleepData> findSleep(@Param("count")int count);
    /**
     * 取出所有睡眠时间
     */
    @Select("SELECT * FROM arduinodb.sleep_data;")
    List<CalculatedSleepData> findAllSleep();

}
