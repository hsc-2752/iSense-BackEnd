package com.team18.backend.mapper;

import com.team18.backend.pojo.EnvironmentData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EVDataMapper {

    @Select("SELECT temp,humidity,pressure,voice,brightness,HCHO FROM ArduinoDB.NANO_data,ArduinoDB.MEGA_data order by NANO_data.NANOid limit 1;")
    List<EnvironmentData> findAll();
}
