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

    public void storeEmotion(int emotion){
        emoMapper.storeEmotionByUser(emotion);
    }

    public int getEmotionByEmotion(){
        return emoMapper.getEmotionByEEG();
    }


    /**
     * 首先分析用户记录的情绪是否大致为递增或递减，其次分析平均值是否处于某个区间内再给出相应的advice
     */
    public String getEmotionAdvice(){
        String emoAdvice = "";
        setEmoList();
        if(emoList == null)
        {
            return "you haven't store your emotion yet.";
        }
        //因为取出来的list是从最新到最后，所以数据为Descend即心情数据上升,ascending即心情数据下降
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
     * 取前十个进行分析，如果不满十个，则取全部数据
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
}
