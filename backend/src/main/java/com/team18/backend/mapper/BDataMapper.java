package com.team18.backend.mapper;

import com.team18.backend.pojo.BodyData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 */
@Mapper
public interface BDataMapper {
    //TODO: add SQL statement
    @Select("")
    List<BodyData> findAll();

    //TODO: add SQL statement
    @Select("")
    String findHeartRate();

    //TODO: add SQL statement
    @Select("")
    String findBloodOxygen();
}
