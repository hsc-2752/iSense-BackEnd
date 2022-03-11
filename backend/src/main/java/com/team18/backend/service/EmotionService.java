package com.team18.backend.service;


import com.team18.backend.mapper.EmotionMapper;
import lombok.val;
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


    private List<BigDecimal> emoList;

    public void storeEmotion(int emotion){
        emoMapper.storeEmotionByUser(emotion);
    }

    public int getEmotionByEmotion(){
        return emoMapper.getEmotionByEEG();
    }


    public String getEmotionAdvice(){
        setEmoList();
        if(emoList == null)
        {
            return "";
        }
        //因为取出来的list是从最新到最后，所以数据为Descend即心情数据上升,ascending即心情数据下降
        if (ruleModeService.isDescending(emoList)) {
            return "";
        }
        else if(ruleModeService.isAscending(emoList)) {
            return "";
        }
        else
            return "";

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
