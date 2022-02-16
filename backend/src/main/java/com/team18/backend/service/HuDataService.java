package com.team18.backend.service;


import org.springframework.stereotype.Component;

@Component
public class HuDataService {
    public double BMICalculator(double height,double weight) {
        return weight/
                (height*height);
    }
}
