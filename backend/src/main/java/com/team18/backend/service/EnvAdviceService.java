package com.team18.backend.service;

import com.team18.backend.pojo.EnvironmentData;

import java.util.List;

/**
 *
 */
public class EnvAdviceService {

    private EnvironmentData evData;


    public EnvAdviceService(EnvironmentData evData) {
        this.evData = evData;
    }


//
//    private String pressueCalculate(double pressure) {
//    }
//
//    private String noiseCalculate(double voice) {
//    }
//
//    private String brightCalculate(double brightness) {
//    }
//
//    private String airCalculate(double hcho) {
//    }
//
//    /**
//     * 分析湿度
//     */
//    private String humCalculate(double humidity) {
//        if (humidity <= 30){
//            condition = -2;
//        }
//        if (humidity >30 && humidity <= 40){
//            condition = -1;
//        }
//        if (humidity > 40 && humidity <= 60){
//            condition = 0;
//        }
//        if (humidity > 60 && humidity <= 80){
//            condition = 1;
//        }
//        //too wet
//
//          return "";
//
//    }

    /**
     * 分析温度，返回建议的字符串
     */
    public String tempCalculate(double temp) {
        //too cold
       if(temp <=11 ){
           return"It is too cold in the room. It is suggested to open the heater to avoid catching cold.";
       }
       //little cold
       if (temp >11 && temp <= 18 ){
           return "Indoor is a little cold, can wear a bit thicker home clothes.";
       }
       //comfort
        if (temp > 18 && temp <= 25){
           return "The temperature in the room is comfortable.";
        }
        //little hot
        if (temp > 25 && temp <= 32){
            return "The room is a little hot, it is recommended to turn the air conditioning to dehumidification or ventilation mode.";
        }
        //too hot

           return "It's too hot in the room, you'd better turn on the air conditioner to cool down.";

    }
//    public String temAndHumCalculate(double temp, double humidity ){
//
//    }
}
