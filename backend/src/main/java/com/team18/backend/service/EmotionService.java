package com.team18.backend.service;


import com.team18.backend.mapper.EmotionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.util.List;

/**
 * Server layer related to emotion
 * This server provides sentence to the agent
 */
@Service
public class EmotionService {

    @Autowired
    EmotionMapper emoMapper;
    @Autowired
    RuleModeService ruleModeService;

    static final int NATURAL_EMOTION = 5;
    static final int POSITIVE_EMOTION = 7;

    private List<BigDecimal> emoList;

    @ExceptionHandler
    public boolean storeEmotion(int emotion){
        try{
            emoMapper.storeEmotionByUser(emotion);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;

    }

    public int getEmotionByEmotion(){
        return emoMapper.getEmotionByEEG();
    }


    /**
     * Firstly, it analyzes whether the recorded emotions of users are generally increasing or decreasing.
     * Secondly, it analyzes whether the average is within a certain range and then gives corresponding advice
     */
    public String getEmotionAdvice(){
        String emoAdvice = "";
        setEmoList();
        if(emoList == null)
        {
            return "you haven't store your emotion yet.";
        }
        //Since the extracted list is from the latest to the last, the data is Descend,
        // indicating that mood data ascending, and ascending, indicating that mood data descending
        if (ruleModeService.isDescending(emoList)) {
            emoAdvice += "According to your recent record of mood, you seem to be in a better mood." +
                    " Did anything good happen?";
        }
        else if(ruleModeService.isAscending(emoList)) {
            emoAdvice += "According to your recent record of mood, You've been feeling down lately. " +
                    "If you are too stressed and tired, have a good rest.";
        }
        if (ruleModeService.isAvgHigher(NATURAL_EMOTION,emoList)){
                emoAdvice +="Your mood seems to be relatively calm recently. I wish you a happy life.";
            }
        else if(ruleModeService.isAvgHigher(POSITIVE_EMOTION,emoList)){
                emoAdvice +="Your recent mood record seems to be positive. I hope you will remain happy.";
        }
        else {
            emoAdvice += "Your average mood is lower than the negative mood index recently, may I ask what happened?";
        }
            return emoAdvice;
    }

    /**
     * Take the first ten for analysis, if less than ten, take all the data
     */
    @ExceptionHandler
    private void setEmoList(){
        try{
             emoList = emoMapper.getEmotionList();
        }
        catch (DataAccessException e){
            emoList = emoMapper.getAllEmotion();
        }
    }

    /**
     * Gets the emotion of the last user input
     */
    @ExceptionHandler
    public int getNewestEmotion(){
        try{
            return emoMapper.getNewestEmotion();
        }catch (DataAccessException e){
            System.out.println(e);
            return 404;
        }
    }

    /**
     * Get all emotion and return a list
     */
        public List<Integer> getAllEmotion(){
            return emoMapper.getAllEmotionAndIndex();
        }


}
