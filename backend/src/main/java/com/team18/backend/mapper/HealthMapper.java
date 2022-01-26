package com.team18.backend.mapper;

import com.team18.backend.pojo.HealthData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface HealthMapper {
    //TODO: add SQL statement
    @Select("SELECT HeartRate, BOS from ArduinoDB.MEGA_data order by MEGAid limit 1;")
    List<HealthData> findAll();

//
//    @Select("")
//    String findHeartRate();
//
//
//    @Select("")
//    String findBloodOxygen();

    //TODO add SQL statement
    @Select("")
    String findSleep();
}
