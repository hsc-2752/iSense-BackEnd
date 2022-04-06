package com.team18.backend.pojo;

/**
 * Environment data component can be qutowried
 */
public class EnvironmentData {
    private double temp;
    private double humidity;
    private double HCHO;
    private double brightness;
    private double voice;
    private double pressure;

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getHCHO() {
        return HCHO;
    }

    public double getBrightness() {
        return brightness;
    }

    public double getVoice() {
        return voice;
    }

    public double getPressure() {
        return pressure;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setHCHO(double HCHO) {
        this.HCHO = HCHO;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public void setVoice(double voice) {
        this.voice = voice;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
