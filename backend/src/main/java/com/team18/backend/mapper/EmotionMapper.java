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
     */
    @Select("select eeg from arduinodb.eeg_data order by eegId desc limit 1;")
    int getEmotionByEEG();

    /**
     * 存放用户输入的情绪
     */
    @Insert("insert into arduinodb.emotion_data (emotion) value (#{emotion});")
    void storeEmotionByUser(@Param("emotion") int emotion);

    /**
     * 返回前十条用户储存的心情
     */
    @Select("select emotion from arduinodb.emotion_data order by emotionId desc limit 10;")
    List<BigDecimal> getEmotionList();

    /**
     * 返回用户所有的心情数据
     */
    @Select("select emotion from arduinodb.emotion_data order by emotionId desc;")
    List<BigDecimal> getAllEmotion();

    /**
     * 返回最新一条用户输入的数据
     */
    @Select("select emotion from arduinodb.emotion_data order by emotionId desc limit 1;")
    int getNewestEmotion();


    //@Select("select * from arduinodb.eeg_data group by eeg order by eegId desc limit 100");

}
