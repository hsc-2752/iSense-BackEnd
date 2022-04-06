package com.team18.backend.mapper;

import com.team18.backend.pojo.CalculatedSleepData;
import com.team18.backend.pojo.HeartData;
import com.team18.backend.pojo.BMIData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This mapper consists all health data operating SQL statement
 */

@Repository
public interface HealthMapper {
    /**
     * Select the latest pulse oximetry data
     * @return Returns a list containing both types of data
     */
    @Select("SELECT HeartRate,BOS FROM ArduinoDB.MEGA_data" +
            " ORDER BY MEGAid DESC LIMIT 1;")
    HeartData findAll();



    /**
     * Average blood oxygen for 15 minutes
     */
    @Select("SELECT AVG(BOS)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data" +
            " WHERE TimeIndex = #{bosTime})" +
            " AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{bosTime}) -900;")
    Double findBOS(@Param("bosTime") String bosTime);

    /**
     * Get all oxygen levels for 15 minutes
     */
    @Select("SELECT BOS" +
            " FROM arduinodb.mega_data" +
            " ORDER BY MEGAid DESC LIMIT 900;")
    List<Double> findReportBOS();

    /**
     * Average heart rate over 15 minutes
     */
    @Select(" SELECT AVG(HeartRate)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{hrTime})  " +
            " AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{hrTime}) -900;")
    Double findHR(@Param("hrTime") String hrTime);

    /**
     * Get all heart rates for the last 15 minutes, in reverse order
     */
    @Select("SELECT HearTRate " +
            "FROM arduinodb.mega_data " +
            "ORDER BY MEGAid DESC LIMIT 900")
    List<Double> findReportHR();

    /**
     * Store BMI data in database
     */
    @Insert("INSERT INTO arduinodb.bmi_data (bmi,timeIndex) VALUE (#{bmi},#{time})")
    void storeBMI(@Param("bmi")double bmi, @Param("time")String time);

    /**
     * gain BMI data from database
     */
    @Select("SELECT timeIndex, bmi FROM arduinodb.bmi_data")
    List<BMIData>  getBMI();

    /**
     * store sleep time to database
     */
    @Insert("INSERT INTO arduinodb.sleep_data (deepSleep, paraSleep, dateIndex) VALUE (#{deepSleep}, #{paraSleep},#{dateIndex})")
    void storeSleep(@Param("deepSleep") double deepSleep, @Param("paraSleep") double paraSleep, @Param("dateIndex") String dateIndex);

    /**
     * update sleep time
     */
    @Update( "UPDATE arduinodb.sleep_data SET deepSleep=#{deepSleep},paraSleep=#{paraSleep} WHERE dateIndex=#{dateIndex}")
    void updateSleep(@Param("deepSleep") double deepSleep, @Param("paraSleep") double paraSleep, @Param("dateIndex") String dateIndex);
    /**
     * select a list of sleep time
     */
    @Select("SELECT * FROM arduinodb.sleep_data ORDER BY dateIndex desc limit #{count};")
    List<CalculatedSleepData> findSleep(@Param("count")int count);
    /**
     * select all sleep time
     */
    @Select("SELECT * FROM arduinodb.sleep_data;")
    List<CalculatedSleepData> findAllSleep();

}
