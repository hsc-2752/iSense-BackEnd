package com.team18.backend.service;

import com.team18.backend.mapper.EVDataMapper;
import com.team18.backend.mapper.HealthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 对心率和温度，噪音，空气质量综合分析得出环境和健康结合的报告
 */
@Service
public class OverallReportService {

    @Autowired
    private HealthMapper healthMapper;

    @Autowired
    private EVDataMapper evDataMapper;

    @Autowired
    private RuleModeService ruleModeService;


    private List<BigDecimal> tempList;

    private List<BigDecimal> airList;

    private List<BigDecimal> noiseList;

    private List<BigDecimal> hrList;

    //温度标准值
    private static final int COMFORT_STAND_TEMP = 25;
    private static final int MOST_DIFFER_TEMP = 5;

    //空气质量标准值
    private static final int NORMAL_LEVEL_AQ = 35;


    //噪音标准值
    private static final int NOISY_STAND = 95;
    private static final int QUITE_NOISY_AVG_STAND = 70;

    //心率标准值
    private static final int HIGH_HR_STAND = 90;
    private static final int LOW_HR_STAND = 60;



    public String getOverallReport(){
        return getHRReport();
    }


    /**
     * 根据论文 [1]兰丽. 室内环境对人员工作效率影响机理与评价研究[D].上海交通大学,2010. ，显示，在温度高于人体舒适的室内进行工作会使人工作效率下降，
     * 而Witterseh研究了温度对模拟办公任务的影响，发现从中性环境到稍暖的环境温度对
     * 人的办公效率没有很大影响，(21 - 25);而Lorsch提出存在临界温度（32.2-35）.
     * 室内高于这个温度会显著地降低脑力任务地绩效。且根据[2]张英杰. 狭小空间内温度和二氧化碳对人体舒适性影响实验研究[D].重庆大学,2018.
     * 随着温度上升，心率也会增长
     * 即按照温度在21以下，21-25区间，25-32.2区间，32以上四个标准分析。
     *
     * 根据[1]谢骁旭. 中国20-49岁人群PM_(2.5)长期暴露对血压和心率的健康效应及其归因风险研究[D].北京协和医学院,2019.DOI:10.27648/d.cnki.gzxhu.2019.000285.
     * pm2.5 和 心跳过速 大至时呈线性增长的。
     * 而在同一温度下，在通风不良的环境中人体会始终处于一种精神紧张的状态，从而引起心率上升。
     * 以及 [2]张英杰. 狭小空间内温度和二氧化碳对人体舒适性影响实验研究[D].重庆大学,2018.
     *
     * 由于设备限制，只能得到空气质量的综合指数，按照在35以下为空气质量优，35-45为空气质量良，45以上为空气质量差三个标准分析
     *
     *
     * 根据[1]杨雅茹. 室内噪音对工作绩效的影响[D].浙江理工大学,2020.DOI:10.27786/d.cnki.gzjlg.2020.000472.
     * [1]景国勋,王远声,周霏,郭绍帅.短期噪声暴露对作业人员心率的影响[J].工业安全与环保,2019,45(08):70-73.
     * ( 1) 噪声刺激会影响人的心率，造成人心率升
     * 高;
     * ( 2) 噪声刺激水平和暴露时间大小都对被试者
     * 的心率产生了显著性影响;
     * ( 3) 由于人耳对噪声的适应性，在同一噪声水
     * 平刺激下，随着暴露时间的延长，心率会有短暂恢
     * 复;
     * ( 4) 在噪声水平＜95 dB 的噪声刺激下，随着暴
     * 露时间的增加，人对噪声的适应性比较明显，此时的
     * ·72·
     * 噪声暴露时间是影响人心率的主导因素，且在噪声
     * 暴露 40～50 min 时，由于人耳对噪声的适应性会出
     * 现短暂的心率恢复的现象，人体出现疲劳。
     * ( 5) 在噪声水平＞95 dB 的噪声刺激下，随着暴
     * 露时间的增加，人对噪声无明显适应性，心率随着噪
     * 声分贝的增大在缓慢上升，此时的噪声水平是影响
     * 人的心率变化的主导因素。
     *
     *
     * 1.1分析心率是否上升且噪音>95；
     * 1.2当噪音<=95，心率是否不处于上升状态
     * 2.分析心率是否上升 且 空气质量指数是否高于35 且 温度最大值和最小值差距不超过5°
     * 3.分析心率是否呈上升趋势 且 环境温度高于 25
     * @return
     */
    private String getHRReport() {
        convertDataType();
        String hrReport = "";
        //因为数据库拿出来的数据是倒序的，所以这里用判断是否下降的方法
        //当数据为持续上升或过快时
        if (ruleModeService.isDescending(hrList) || ruleModeService.isHigher(HIGH_HR_STAND,hrList) ) {
            hrReport+= " Your heart rate is increasing or staying around a high value, which may be caused by the following reasons: ";
           if(ruleModeService.isHigher(NOISY_STAND, noiseList)){
               hrReport+=" Data shows that ambient noise is greater than 95 decibels within 15 minutes. " +
                       "According to research, when people are exposed to noise above 95 decibels for a long time, " +
                       "they have no obvious adaptation to noise and their heart rate will continue to increase.";
           }
           if( ruleModeService.isHigher(NORMAL_LEVEL_AQ, airList)){

               hrReport += " The data shows that your indoor air quality is average or even not very good. ";

               if(ruleModeService.isDifferN(MOST_DIFFER_TEMP ,tempList))
               {
                   hrReport +=" According to the research, when the person is in a certain range of temperature environment " +
                           "with little fluctuation and in the poor ventilation environment, the human experience is always in a state of mental tension, " +
                           "which causes the heart rate to rise.";
               }
               else if(ruleModeService.isHigher(COMFORT_STAND_TEMP,tempList))
               {
                   hrReport+=" Research shows that people who work indoors in warmer temperatures than they are comfortable with are less productive. " +
                           "You can even have a tachycardia.";
               }
               hrReport +=" And staying in a stuffy room for a long time may make people feel tired.";

           }else if(ruleModeService.isLess(NORMAL_LEVEL_AQ,airList)){
               hrReport +=" The data shows that the air in your room is fine now.";

               if(ruleModeService.isHigher(COMFORT_STAND_TEMP,tempList)){
                   hrReport += " Research shows that people who work indoors in warmer temperatures than they are comfortable with are less productive. " +
                           "You can even have a tachycardia.";
               }
                    hrReport+=" Aside from environmental causes, it could also be because you stayed up late last night or didn't get enough rest.";

           }
        }
        //心率持续下降
        else if(ruleModeService.isAscending(hrList)){
            if(!ruleModeService.isAvgHigher(NOISY_STAND,noiseList)){
                if(ruleModeService.isLess(NOISY_STAND,noiseList) && ruleModeService.isAvgHigher(QUITE_NOISY_AVG_STAND,noiseList) ){
                    hrReport+=" The data shows that your ambient noise for the first fifteen minutes is always less than 95 but on average greater than 70. " +
                            "According to research, people exposed to noise below 95 decibels experience a temporary recovery in heart rate due to ear adaptation to noise. " +
                            "But being in a noisy environment for a long time can make you feel tired.";
                }
            }
            hrReport+=" According to the cardiovascular medicine department of Affiliated Hospital of Yanbian University," +
                    " a case of heart rate drop caused by smoking accompanied by dizziness, chest pain and chest tightness was studied. " +
                    "Smoking can cause an immediate drop in heart rate. If you have smoked in the last 15 minutes, " +
                    "excluding environmental and physical conditions, your heart rate may have dropped in the last 15 minutes because of smoking.";

        }
        //心率既不持续下降也不持续上升且处于正常范围
        else if(ruleModeService.isAvgHigher(LOW_HR_STAND,hrList) && !ruleModeService.isAvgHigher(HIGH_HR_STAND,hrList))
        {
            hrReport+= " Your average heart rate for the last 15 minutes is within the normal range.";
        }
        hrReport+=" For more detailed reports on environment and heart rate, go to the Environment or Health page.";
       return hrReport;
    }

    /**
     * 转换数据类型
     */
    private void convertDataType() {
        tempList = new ArrayList<>();
        airList = new ArrayList<>();
        noiseList = new ArrayList<>();
        hrList = new ArrayList<>();
        for (double temp: evDataMapper.findReportTemp() ) {
            tempList.add(new BigDecimal(temp));
        }
        for(double air:evDataMapper.findReportAirQuality()){
            airList.add(new BigDecimal(air));
        }
        for(double noise : evDataMapper.findReportNoise()){
            noiseList.add(new BigDecimal(noise));
        }
        for (double heartRate:  healthMapper.findReportHR()){
            hrList.add(new BigDecimal(heartRate));
        }
    }


}
