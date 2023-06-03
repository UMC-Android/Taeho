package com.example.chapter9_network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapAPI {

    @GET("data/2.5/weather")
    Call<WeatherResponseDTO> getWeatherData( @Query("q") String location, @Query("appid") String apiKey);
}
