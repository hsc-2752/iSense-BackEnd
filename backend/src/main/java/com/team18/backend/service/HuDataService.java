package com.team18.backend.service;


import com.team18.backend.pojo.HuData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HuDataService {



    public double BMICalculator(double height,double weight) {
        return weight/
                (height*height);
    }
}
