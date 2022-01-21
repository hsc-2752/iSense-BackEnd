package com.team18.backend.mapper;

import com.team18.backend.pojo.HealthData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface HealthMapper {
    //TODO: add SQL statement
    @Select("")
    List<HealthData> findAll();

    //TODO: add SQL statement
    @Select("")
    String findHeartRate();

    //TODO: add SQL statement
    @Select("")
    String findBloodOxygen();
}
