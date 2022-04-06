package com.team18.backend.mapper;

import com.team18.backend.pojo.EnvironmentData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface
EVDataMapper {

    @Select("SELECT temp,humidity,pressure,voice,brightness,HCHO FROM ArduinoDB.NANO_data,ArduinoDB.MEGA_data" +
            " order by NANO_data.NANOid desc limit 1;")
    EnvironmentData findAll();

    /**
     * Get the average temperature over an hour
     */
    @Select("SELECT AVG(temp)" +
            " FROM arduinodb.nano_data" +
            " WHERE NANOid < (SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{temTime})" +
            " AND NANOid >=(SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{temTime}) -3600;")
    Double findTem(@Param("temTime") String temTime);

     /**
     * Get the average humidity over an hour
     */
     @Select("SELECT AVG(humidity)" +
             " FROM arduinodb.nano_data" +
             " WHERE NANOid < (SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{humTime})  " +
             "AND NANOid >=(SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{humTime}) -3600;")
     Double findHum(@Param("humTime") String humTime);


    /**
     * Obtain the average indoor noise in 1 hour
     */
    @Select("SELECT AVG(voice)" +
            " FROM arduinodb.nano_data" +
            " WHERE NANOid < (SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{noiseTime}) " +
            "AND NANOid >=(SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{noiseTime}) -3600;")
    Double findVoice(@Param("noiseTime") String noiseTime);

    /**
     * Get the average illumination in half an hour
     */

    @Select("SELECT AVG(brightness)" +
            " FROM arduinodb.nano_data" +
            " WHERE NANOid < (SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{brightTime}) " +
            "AND NANOid >=(SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{brightTime}) -1800;")
    Double findBrightness(@Param("brightTime")String brightTime);

    /**
     * Get air quality within half an hour
     */
    @Select("SELECT AVG(HCHO)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{airTime}) " +
            "AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{airTime}) -1800;")
    Double findHCHO(@Param("airTime")String airTime);

    /**
     * Get three hours of environmental data
     */
    @Select("select temp, humidity, pressure,voice,brightness,HCHO " +
            "from arduinodb.mega_data  join arduinodb.nano_data " +
            "on mega_data.TimeIndex = nano_data.TimeIndex " +
            "order by MEGAid desc limit 60*60*3")
    List<EnvironmentData> reportData();


    /**
     * Used to retrieve and analyze the ambient data during sleep
     */
    @Select("SELECT temp, humidity, voice, brightness,HCHO" +
            " FROM arduinodb.mega_data join arduinodb.nano_data " +
            "on mega_data.TimeIndex = nano_data.TimeIndex " +
            "where arduinodb.nano_data.TimeIndex between #{startTime} and #{endTime}")
    List<EnvironmentData> sleepEVData(String startTime, String endTime);


    /**
     * The first 15*60*60(i.e., 15 minutes of temperature data) were taken for overall report
     */
    @Select("select temp from arduinodb.nano_data order by NANOid desc limit 15*60*60;")
    List<Double> findReportTemp();

    /**
     * Extract the first 15*60*60(i.e. 15 minutes of noise data) for overall report
     */
    @Select("select voice from arduinodb.nano_data order by NANOid desc limit 15*60*60;")
    List<Double> findReportNoise();

    /**
     * The first 15*60*60(15 minutes of air quality data) were taken out for overall report
     */
    @Select("select HCHO from arduinodb.mega_data order by MEGAid desc limit 15*60*60;")
    List<Double> findReportAirQuality();




}
