package com.team18.backend.service;

import com.team18.backend.mapper.EVAdviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Service
public class EnvAdviceService {

    @Autowired
    private EVAdviceMapper adviceMapper;


    //气压值标准
    private  static final double HIGH_PRESSURE_STAND = 100.0;
    private  static final double LOW_PRESSURE_STAND = 10.0;

    //分贝标准值
    private  static final double ZERO_DECIBEL_STAND = 0.0;
    private  static final double LOW_DECIBEL_STAND = 15.0;
    private  static final double NORMAL_DECIBEL_STAND = 50.0;
    private  static final double HIGH_DECIBEL_STAND = 90.0;

    //温度标准值
    private  static final double FIRST_LEVEL_TEMP_STAND = 11.0;
    private  static final double SECOND_LEVEL_TEMP_STAND = 18.0;
    private  static final double THIRD_LEVEL_TEMP_STAND = 25.0;
    private  static final double FOURTH_LEVEL_TEMP_STAND = 32.0;

    //湿度标准值
    private static final double FIRST_LEVEL_HUM_STAND = 30.0;
    private static final double SECOND_LEVEL_HUM_STAND = 40.0;
    private static final double THIRD_LEVEL_HUM_STAND = 60.0;
    private static final double FOURTH_LEVEL_HUM_STAND = 80.0;

    //光照标准值
    private static final double FIRST_LEVEL_LUX_STAND = 30.0;
    private static final double SECOND_LEVEL_LUX_STAND = 75.0;
    private static final double THIRD_LEVEL_LUX_STAND = 100.0;
    private static final double FOURTH_LEVEL_LUX_STAND = 150.0;

    //空气质量标准值
    private static final double FRESH_AIR_STAND = 27;
    private static final double NORMAL_AIR_STAND = 35;
    private static final double POLLUTE_AIR_STAND = 45;



    /**
     * 获取温度和湿度的建议
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
    return pressureCalculate(pressure);
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
        return airQualityCalculate(air);
    }

    /**
     * 根据光照和现在时间获取光照有关建议
     */
    public String getBrightAdvice(){
        double bright = adviceMapper.getBrightAdviceData();
        boolean isNight = timeCalculate();
        return brightCalculate(bright,isNight);
    }

    /**
     * 分析空气质量得到建议
     */
    private String airQualityCalculate(double air) {
    if(air<= FRESH_AIR_STAND ){
        return "Indoor air quality is very good for human health.";
    }
    else if(air <= NORMAL_AIR_STAND && air > FRESH_AIR_STAND){
        return "Indoor air quality is better, suitable for daily life.";
    }
    else if(air <= POLLUTE_AIR_STAND && air > NORMAL_AIR_STAND){
        return "Indoor air quality is general, " +
                "there will be no great impact on the human body, " +
                "but it is still recommended that you often open Windows for ventilation.";
    }
    else
        return "Indoor air quality is poor, " +
                "and long-term exposure to polluted air can cause lung diseases and other serious illnesses. " +
                "It is recommended that you open all doors and Windows to improve indoor air and exhaust indoor air pollution to the outside.";
    }

    /**
     * 根据时间和光照强度综合分析
     * @param bright
     * @param isNight
     * @return
     */
    private String brightCalculate(double bright, boolean isNight) {
        if(isNight){
            if (bright <= FIRST_LEVEL_LUX_STAND){
                return "Indoor light intensity is suitable for rest, wish you have a good night.";
            }
            else if(bright>FIRST_LEVEL_LUX_STAND && bright< SECOND_LEVEL_LUX_STAND){
                return "Indoor brightness is normal, suitable for general night activities.";
            }
            else if(bright>= SECOND_LEVEL_LUX_STAND && bright <THIRD_LEVEL_LUX_STAND){
                return "The interior lighting is bright enough for reading." +
                        " It is recommended that you rest your eyes after reading for some time.";
            }
            else if(bright >= THIRD_LEVEL_LUX_STAND && bright <FOURTH_LEVEL_LUX_STAND){
                return "Indoor lighting is a little too bright for rest, " +
                        "which may interfere with your melatonin production and make it difficult to fall asleep. " +
                        "I suggest you turn off some lights.";
            }
            else {
                return "Indoor lighting is too bright, and too bright light intensity at night will affect the body's circadian rhythm. " +
                        "It is recommended that you turn off some non-essential lights.";
            }
        }
        else {
            if (bright <= FIRST_LEVEL_LUX_STAND){
                return "The brightness of the room is too dark in the morning." +
                        " I suggest you open the curtains. Have a nice day.";            }
            else if(bright>FIRST_LEVEL_LUX_STAND && bright< SECOND_LEVEL_LUX_STAND){
                return "Indoor brightness is normal, suitable for general daily activities.";
            }
            else if(bright>= SECOND_LEVEL_LUX_STAND && bright <THIRD_LEVEL_LUX_STAND){
                return "The interior lighting is bright enough for reading." +
                        " It is recommended that you rest your eyes after reading for some time.";
            }
            else if(bright >= THIRD_LEVEL_LUX_STAND && bright <FOURTH_LEVEL_LUX_STAND){
                return "Now the indoor brightness is a little bright, " +
                        "if it is to turn on the light to cause such a bright environment, " +
                        "it is recommended that you do not turn on so many lights for a long time, " +
                        "save electricity.";
            }
            else {
                return "Now the indoor brightness is a little too bright, " +
                        "if the light is caused by such a bright environment, " +
                        "it is recommended that you do not turn on so many lights for a long time, " +
                        "which will affect the eye health and in order to save electricity, " +
                        "it is suggested that you turn off some non-essential lights.";
            }
        }
    }

    /**
     * 判断现在是否为夜晚
     * @return true 为夜晚，false 为白天
     */
    private boolean timeCalculate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        int time = Integer.parseInt(dateFormat.format(date));
        return (time > 8 && time <= 24) || (time>=0 && time <=6);
    }

    /**
     * 分析环境气压
     * @param pressure 十秒气压平均值
     * @return 对这个气压分析后的结果
     */
    private String pressureCalculate(double pressure) {
        if(pressure >LOW_PRESSURE_STAND && pressure < HIGH_PRESSURE_STAND){
            return "Indoor air pressure normal";
        }
        else {
            return "Abnormal indoor air pressure may be caused by the lack of circulation of indoor and outdoor air," +
                    " which is not suitable for daily life and may cause some minor impact on your health." +
                    " It is recommended that you open the window for ventilation.";
        }

    }

    /**
     * 分析环境音量
     * @param noise
     * @return 建议
     */
    private String noiseCalculate(double noise) {
        if(noise >=ZERO_DECIBEL_STAND && noise <=LOW_DECIBEL_STAND )
        {
            return "The surroundings are now quiet enough for activities that require intense concentration. But I suggest you take it easy.";
        }
        else if(noise >LOW_DECIBEL_STAND && noise <=NORMAL_DECIBEL_STAND)
        {
            return "The surrounding seems so right and the ambient decibel value is very normal, suitable for daily life, some relaxed activities.";
        }
        else if(noise >NORMAL_DECIBEL_STAND && noise<=HIGH_DECIBEL_STAND )
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
        if (humidity <= FIRST_LEVEL_HUM_STAND){
            condition = -2;
        }
        if (humidity >FIRST_LEVEL_HUM_STAND && humidity <= SECOND_LEVEL_HUM_STAND){
            condition = -1;
        }
        if (humidity > THIRD_LEVEL_HUM_STAND && humidity <= FOURTH_LEVEL_HUM_STAND){
            condition = 1;
        }
        //too wet
        if (humidity >FOURTH_LEVEL_HUM_STAND){
            condition = 2;
        }
          return condition;

    }
    /**
     * 分析温度和湿度，返回建议的字符串
     */
    private String temAndHumCalculate(double temp, double humidity ){
        //too cold
        if(temp <=FIRST_LEVEL_TEMP_STAND ){
            return switch (humCalculate(humidity)) {
                case -2 -> "At present, the indoor temperature is too low and very dry, which may lead to dry and cracked skin. It is recommended to apply some moisturizing skin care products and pay attention to keep warm.";
                case -1 -> "The room temperature is too low and a little dry, pay attention to water and warm.";
                case 1 -> "The indoor temperature is too low and the humidity is too high, which may cause cold hands and feet. Keep warm.";
                case 2 -> "At present, the indoor temperature is too low and the humidity is too high, which will cause the body temperature to drop. Pay attention to dehumidification and warmth in the room.";
                default -> "It is too cold in the room but humidity is normal. It is suggested to open the heater to avoid catching cold.";
            };
        }
        //little cold
        if (temp >FIRST_LEVEL_TEMP_STAND && temp <= SECOND_LEVEL_TEMP_STAND ){
            return switch (humCalculate(humidity)) {
                case -2 -> "Now the indoor temperature is a little low and very dry, please add more clothes and try not to turn on air conditioning, air conditioning will make the environment more dry.";
                case -1 -> "Now the indoor temperature is a little low and a little dry, please add more clothes and turn on less air conditioning, pay attention to add water.";
                case 1 -> "Now the indoor temperature is a little low but the humidity is high, you can consider turning on the air conditioning to heat up and dehumidify.";
                case 2 -> "Now the indoor temperature is a little low but the air is very humid. Please turn on the air conditioner to warm up and dehumidify.";
                default -> "Indoor is a little cold but humidity is normal, can wear a bit thicker home clothes.";
            };
        }
        //comfort
        if (temp > SECOND_LEVEL_TEMP_STAND && temp <= THIRD_LEVEL_TEMP_STAND){
            return switch (humCalculate(humidity)){
                case -2 -> "The indoor temperature is appropriate, but the ambient humidity is too low. Please turn on the humidifier.";
                case -1 -> "Now the indoor temperature is appropriate, the environmental humidity is a little low, you can consider turning on the humidifier, pay attention to water.";
                case 1 -> "Now indoor temperature is appropriate, environmental humidity is a little high, you can consider opening the window for ventilation.";
                case 2 -> "The indoor temperature is suitable but the ambient humidity is too high. Turn on the dehumidifier or the dehumidification mode of the air conditioner.";
                default -> "Indoor temperature and ambient humidity are suitable";
            };
        }
        //little hot
        if (temp > THIRD_LEVEL_TEMP_STAND && temp <= FOURTH_LEVEL_TEMP_STAND){
            return switch (humCalculate(humidity)){
                case -2 -> "Now the indoor temperature is a little high, the environmental humidity is too low, pay attention to add water.";
                case -1 -> "The indoor temperature is a little high, and the environmental humidity is a little low. Drink more water and pay attention to ventilation.";
                case 1 -> "The indoor temperature is a little high, the ambient humidity is a little high, pay attention to dehumidification.";
                case 2 -> "The indoor temperature is a little high now, and the environment is very humid, which will increase the body temperature and easily breed bacteria. Please turn on the air conditioner to cool down and dehumidify.";
                default -> "The room is a little hot but humidity is normal, it is recommended to turn the air conditioning to dehumidification or ventilation mode.";
            };

        }
        //too hot
        if(temp > FOURTH_LEVEL_TEMP_STAND){
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
