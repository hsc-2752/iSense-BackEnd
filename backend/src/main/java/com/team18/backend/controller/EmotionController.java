package com.team18.backend.controller;

import com.team18.backend.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * Controller relate to emotion part
 */
@RestController
public class EmotionController {



    EmotionService emoService;
    @Autowired
    EmotionController (EmotionService emotionService){
        this.emoService = emotionService;
    }

    /**
     * The mood selected by the user is obtained from the front end and stored in the database
     */
    @RequestMapping(value = "/emotion/storeEmotion",method = RequestMethod.POST)
    public void storeEmotion(@RequestParam("emotion") int emotion){
        emoService.storeEmotion(emotion);
    }

    /**
     * Returns the latest EEG recognition of human emotions
     */
    @RequestMapping(value = "/emotion/getEEGEmotion", method = RequestMethod.GET)
    public int getEEGEmotion(){
        return emoService.getEmotionByEmotion();
    }

    /**
     * Returns a mood analysis of recent user input
     */
    @RequestMapping(value = "/emotion/getEmoAdvice",method = RequestMethod.GET)
    public String getEmoAdvice(){
        return emoService.getEmotionAdvice();
    }

    /**
     * return emotion
     */
    @RequestMapping(value = "/emotion/getEmotion",method = RequestMethod.GET)
    public List<Integer> getEmotion(){
        return emoService.getAllEmotion();
    }

}
