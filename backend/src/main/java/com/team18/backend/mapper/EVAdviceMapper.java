package com.team18.backend.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Operations on databases for advice only
 */
@Repository
public interface EVAdviceMapper {

    /**
     * Take the average of the data (temperature) in the last ten seconds
     */
    @Select("select avg(temp) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getTempAdviceData();

    /**
     * Take the average of the previous 10 seconds of data (humidity)
     */
    @Select("select avg(humidity) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getHumAdviceData();

    /**
     * Take the average of the data(pressure) for the last ten seconds
     */
    @Select("select avg(pressure) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getPressureAdviceData();

    /**
     * Take the average of the previous 10 seconds of data (noise)
     */
    @Select("select avg(voice) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getNoiseAdviceData();

    /**
     * Take the average of the previous 10 seconds of data (brightness)
     */
    @Select("select avg(brightness) from arduinodb.nano_data " +
            "where NANOid between " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1)-9" +
            " and " +
            "(select NANOid from arduinodb.nano_data order by NANOid desc limit 1);")
    double getBrightAdviceData();

    /**
     * Take the average of the previous 10 seconds of data (air quality)
     */
    @Select("select avg(HCHO) from arduinodb.mega_data " +
            "where MEGAid between " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1)-9" +
            " and " +
            "(select MEGAid from arduinodb.mega_data order by MEGAid desc limit 1);")
    double getAirAdviceData();
}
