package com.team18.backend.mapper;

import com.team18.backend.pojo.Tester;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface TestMapper {

    @Select("select * from Test")
    List<Tester> findAll();
}
