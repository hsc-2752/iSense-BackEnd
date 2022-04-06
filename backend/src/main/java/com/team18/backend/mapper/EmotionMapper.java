package com.team18.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * This mapper contains all sql statement to operate emotion data
 */
@Repository
public interface EmotionMapper {

    /**
     * Get the latest EEG recognition of human emotions
     */
    @Select("select eeg from arduinodb.eeg_data order by eegId desc limit 1;")
    int getEmotionByEEG();

    /**
     * Store user input emotions
     */
    @Insert("insert into arduinodb.emotion_data (emotion) value (#{emotion});")
    void storeEmotionByUser(@Param("emotion") int emotion);

    /**
     * Returns the top ten stored moods of the user
     */
    @Select("select emotion from arduinodb.emotion_data order by emotionId desc limit 10;")
    List<BigDecimal> getEmotionList();

    /**
     * Returns all of the user's mood data
     */
    @Select("select emotion from arduinodb.emotion_data order by emotionId desc;")
    List<BigDecimal> getAllEmotion();

    /**
     * Returns the latest user input
     */
    @Select("select emotion from arduinodb.emotion_data order by emotionId desc limit 1;")
    int getNewestEmotion();


    /**
     * return all emotion data
     */
    @Select("SELECT emotion FROM arduinodb.emotion_data order by emotionId desc;")
    List<Integer> getAllEmotionAndIndex();
}
