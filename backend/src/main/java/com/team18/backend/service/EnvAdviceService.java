package com.team18.backend.service;

import com.team18.backend.mapper.EVAdviceMapper;
import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.pojo.EnvironmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EnvAdviceService {

    @Autowired
    private EVAdviceMapper adviceMapper;

    /**
     * 获取温度和湿度的建议
     * @return
     */
    public String getTemAndHumAdvice(){
       double temp = adviceMapper.getTempAdviceData();
       double humidity = adviceMapper.getHumAdviceData();
        return temAndHumCalculate(temp,humidity);
    }

    /**
     * 获取气压的建议
     */
    public String getPressureAdvice(){
    double pressure = adviceMapper.getPressureAdviceData();
    return "developing";
    }

    /**
     * 获取噪音的有关建议
     */
    public String getNoiseAdvice(){
       double noise = adviceMapper.getNoiseAdviceData();
        return noiseCalculate(noise);
    }

    /**
     * 获取空气质量的有关建议
     */
    public String getAirQualityAdvice(){
        double air = adviceMapper.getAirAdviceData();
        return "developing";
    }
    /**
     * 获取光照有关建议
     */
    public String getBrightAdvice(){
        double bright = adviceMapper.getBrightAdviceData();
        return "developing";
    }

    /**
     * 分析环境音量
     * @param noise
     * @return 建议
     */
    private String noiseCalculate(double noise) {
        if(noise >=0 && noise <=15 )
        {
            return "The surroundings are now quiet enough for activities that require intense concentration. But I suggest you take it easy.";
        }
        else if(noise >15 && noise <=50)
        {
            return "The surrounding seems so right and the ambient decibel value is very normal, suitable for daily life, some relaxed activities.";
        }
        else if(noise >50 && noise<=100 )
        {
            return "You are in bear garden, if you turn on the sound caused by the high decibel environment, " +
                    "it is recommended that you do not stay in this environment for a long time; If the noise is caused by outdoor noise," +
                    " you are advised to wear ear muffs or report the situation to the relevant authorities.";
        }
        else {
            return "Your surroundings are too vociferous, noise can damage people's hearing ability," +
                    " people's visual system and even affect other parts of the body. I suggest you get away from this noisy environment.";
        }

    }


    /**
     * 分析湿度
     */
    private int humCalculate(double humidity) {
        int condition = 0;
        if (humidity <= 30){
            condition = -2;
        }
        if (humidity >30 && humidity <= 40){
            condition = -1;
        }
        if (humidity > 60 && humidity <= 80){
            condition = 1;
        }
        //too wet
        if (humidity >80){
            condition = 2;
        }
          return condition;

    }
    /**
     * 分析温度和湿度，返回建议的字符串
     */
    private String temAndHumCalculate(double temp, double humidity ){
        //too cold
        if(temp <=11 ){
            return switch (humCalculate(humidity)) {
                case -2 -> "At present, the indoor temperature is too low and very dry, which may lead to dry and cracked skin. It is recommended to apply some moisturizing skin care products and pay attention to keep warm.";
                case -1 -> "The room temperature is too low and a little dry, pay attention to water and warm.";
                case 1 -> "The indoor temperature is too low and the humidity is too high, which may cause cold hands and feet. Keep warm.";
                case 2 -> "At present, the indoor temperature is too low and the humidity is too high, which will cause the body temperature to drop. Pay attention to dehumidification and warmth in the room.";
                default -> "It is too cold in the room but humidity is normal. It is suggested to open the heater to avoid catching cold.";
            };
        }
        //little cold
        if (temp >11 && temp <= 18 ){
            return switch (humCalculate(humidity)) {
                case -2 -> "Now the indoor temperature is a little low and very dry, please add more clothes and try not to turn on air conditioning, air conditioning will make the environment more dry.";
                case -1 -> "Now the indoor temperature is a little low and a little dry, please add more clothes and turn on less air conditioning, pay attention to add water.";
                case 1 -> "Now the indoor temperature is a little low but the humidity is high, you can consider turning on the air conditioning to heat up and dehumidify.";
                case 2 -> "Now the indoor temperature is a little low but the air is very humid. Please turn on the air conditioner to warm up and dehumidify.";
                default -> "Indoor is a little cold but humidity is normal, can wear a bit thicker home clothes.";
            };
        }
        //comfort
        if (temp > 18 && temp <= 25){
            return switch (humCalculate(humidity)){
                case -2 -> "The indoor temperature is appropriate, but the ambient humidity is too low. Please turn on the humidifier.";
                case -1 -> "Now the indoor temperature is appropriate, the environmental humidity is a little low, you can consider turning on the humidifier, pay attention to water.";
                case 1 -> "Now indoor temperature is appropriate, environmental humidity is a little high, you can consider opening the window for ventilation.";
                case 2 -> "The indoor temperature is suitable but the ambient humidity is too high. Turn on the dehumidifier or the dehumidification mode of the air conditioner.";
                default -> "Indoor temperature and ambient humidity are suitable";
            };
        }
        //little hot
        if (temp > 25 && temp <= 32){
            return switch (humCalculate(humidity)){
                case -2 -> "Now the indoor temperature is a little high, the environmental humidity is too low, pay attention to add water.";
                case -1 -> "The indoor temperature is a little high, and the environmental humidity is a little low. Drink more water and pay attention to ventilation.";
                case 1 -> "The indoor temperature is a little high, the ambient humidity is a little high, pay attention to dehumidification.";
                case 2 -> "The indoor temperature is a little high now, and the environment is very humid, which will increase the body temperature and easily breed bacteria. Please turn on the air conditioner to cool down and dehumidify.";
                default -> "The room is a little hot but humidity is normal, it is recommended to turn the air conditioning to dehumidification or ventilation mode.";
            };

        }
        //too hot
        if(temp > 32){
            return switch (humCalculate(humidity)){
                case -2 -> "Now the indoor temperature is too high and the air is very dry. The indoor environment is abnormal. Please open more Windows for ventilation.";
                case -1 -> "Now the indoor temperature is too high and the air is a little dry, pay attention to cooling and moisturizing, be careful not to heat stroke.";
                case 1 -> "Now the indoor temperature is too high and the air is a little humid, which may make bacteria more easily breeding, please open the window for ventilation.";
                case 2 -> "The indoor temperature is too high and the air is very humid. Open the window for ventilation and turn on the dehumidifier. Also check for perishable or corroded items stored in the house.";
                default -> "It's too hot in the room but humidity is normal, you'd better turn on the air conditioner to cool down.";
            };

    }
        return "no data is obtained, check the network or sensor";
    }
}
