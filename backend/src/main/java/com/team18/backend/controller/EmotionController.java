package com.team18.backend.controller;

import com.team18.backend.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller relate to emotion part
 */
@RestController
public class EmotionController {

    @Autowired
    EmotionService emoService;


    /**
     * 从前端获取用户选择的心情，并存入数据库中
     */
    @RequestMapping(value = "/emotion/storeEmotion",method = RequestMethod.POST)
    public void storeEmotion(@RequestParam("emotion") int emotion){
        emoService.storeEmotion(emotion);
    }

    /**
     * 返回最新的EEG识别人情绪的结果
     */
    @RequestMapping(value = "/emotion/getEEGEmotion", method = RequestMethod.GET)
    public int getEEGEmotion(){
        return emoService.getEmotionByEmotion();
    }

    /**
     * 返回对近期用户输入的心情分析
     */
    @RequestMapping(value = "/emotion/getEmoAdvice",method = RequestMethod.GET)
    public String getEmoAdvice(){
        return emoService.getEmotionAdvice();
    }


}
