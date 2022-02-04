package com.team18.backend.mapper;

import com.team18.backend.pojo.EnvironmentData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EVDataMapper {

    @Select("SELECT temp,humidity,pressure,voice,brightness,HCHO FROM ArduinoDB.NANO_data,ArduinoDB.MEGA_data order by NANO_data.NANOid desc limit 1;")
    List<EnvironmentData> findAll();

    /**
     *获取一小时内的温度平均值
     */

    @Select("SELECT AVG(temp)" +
            " FROM arduinodb.nano_data" +
            " WHERE NANOid < (SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{temTime})  " +
            "AND NANOid >=(SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{temTime}) -3600;")
    String findTem(@Param("temTime") String temTime);

     /**
     * 获取一小时内的湿度平均值
     */
     @Select("SELECT AVG(humidity)" +
             " FROM arduinodb.nano_data" +
             " WHERE NANOid < (SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{humTime})  " +
             "AND NANOid >=(SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{humTime}) -3600;")
     String findHum(@Param("humTime") String humTime);


    /**
     * Obtain the average indoor noise
     * 获取一小时内的噪音平均值
     */
    @Select("SELECT AVG(voice)" +
            " FROM arduinodb.nano_data" +
            " WHERE NANOid < (SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{noiseTime})  " +
            "AND NANOid >=(SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{noiseTime}) -3600;")
    String findVoice(@Param("noiseTime") String noiseTime);

    /**
     * 获取半小时内光照平均值
     */
    @Select("SELECT AVG(brightness)" +
            " FROM arduinodb.nano_data" +
            " WHERE NANOid < (SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{brightTime})  " +
            "AND NANOid >=(SELECT NANOid FROM arduinodb.nano_data WHERE TimeIndex = #{brightTime}) -1800;")
    String findBrightness(@Param("brightTime")String brightTime);

    /**
     *获取半小时内空气质量
     */
    @Select("SELECT AVG(HCHO)" +
            " FROM arduinodb.mega_data" +
            " WHERE MEGAid < (SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{airTime})  " +
            "AND MEGAid >=(SELECT MEGAid FROM arduinodb.mega_data WHERE TimeIndex = #{airTime}) -1800;")
    String findHCHO(@Param("airTime")String airTime);
}
