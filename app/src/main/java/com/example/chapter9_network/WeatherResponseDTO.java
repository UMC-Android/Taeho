package com.example.chapter9_network;

import com.google.gson.annotations.SerializedName;

public class WeatherResponseDTO {
    @SerializedName("main")
    private WeatherMainDTO main;

    public WeatherMainDTO getMain() {
        return main;
    }
}
