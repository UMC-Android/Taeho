package com.example.chapter9_network;

import com.google.gson.annotations.SerializedName;

public class WeatherMainDTO {

    @SerializedName("humidity")
    private int humidity;
    @SerializedName("temp")
    private double temperature;

    public int getHumidity() {
        return humidity;
    }

    public double getTemperature() {
        return temperature;
    }
}
