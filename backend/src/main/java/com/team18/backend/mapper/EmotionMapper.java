package com.team18.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmotionMapper {

    /**
     * 获取最新的eeg识别的人类情绪
     * @return
     */
    //TODO add SQL statement
    @Select("")
    int getEmotionByEEG();

    /**
     * 存放用户输入的情绪
     */
    //TODO add SQL statement
    @Insert("")
    void storeEmotionByUser(@Param("emotion") int emotion);

    /**
     * 返回前十条用户储存的心情
     * @return
     */
    @Select("")
    List<BigDecimal> getEmotionList();

    /**
     * 返回用户所有的心情数据
     */
    @Select("")
    List<BigDecimal> getAllEmotion();
}
