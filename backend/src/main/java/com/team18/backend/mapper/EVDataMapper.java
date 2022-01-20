package com.team18.backend.mapper;

import com.team18.backend.pojo.EnvironmentData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EVDataMapper {

    @Select("SELECT temp,humidity,pressure,voice,brightness,HCHO FROM ArduinoDB.NANO_data,ArduinoDB.MEGA_data order by NANO_data.NANOid limit 1;")
    List<EnvironmentData> findAll();

    /**
     * Obtain the newest Temperature and humidity data
     * @return String list which contains temperature and humidity
     */
    @Select("SELECT temp,humidity FROM ArduinoDB.NANO_data order by NANO_data.NANOid limit 1;")
    List<String> findTemHum();

    /**
     * Obtain the latest indoor air pressure
     */
    @Select("SELECT pressure FROM ArduinoDB.NANO_data order by NANO_data.NANOid limit 1;")
   String findPressure();

    /**
     * Obtain the latest indoor noise
     */
    @Select("SELECT voice FROM ArduinoDB.NANO_data order by NANO_data.NANOid limit 1;")
    String findVoice();

    /**
     * Obtain the latest indoor brightness
     */
    @Select("SELECT brightness FROM ArduinoDB.NANO_data order by NANO_data.NANOid limit 1;")
    String findBrightness();

    /**
     * Obtain the latest air quality data
     * @return
     */
    @Select("SELECT HCHO FROM ArduinoDB.Mega_data order by MEGA_data.MEGAid limit 1;")
    String findHCHO();
}
