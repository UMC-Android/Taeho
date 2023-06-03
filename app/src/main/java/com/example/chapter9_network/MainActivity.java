package com.example.chapter9_network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherMapAPI weatherApi = retrofit.create(OpenWeatherMapAPI.class);
        Call<WeatherResponseDTO> call = weatherApi.getWeatherData("Incheon", "22c814ffabf7c6b8c030d86227e51493");
        call.enqueue(new Callback<WeatherResponseDTO>() {
            @Override
            public void onResponse(Call<WeatherResponseDTO> call, Response<WeatherResponseDTO> response) {
                if (response.isSuccessful()) {
                    WeatherResponseDTO weatherResponse = response.body();
                    if (weatherResponse != null) {
                        int humidity = weatherResponse.getMain().getHumidity();
                        double temperature = weatherResponse.getMain().getTemperature();
                        Log.d("Weather", "Location: Incheon");
                        Log.d("Weather", "Humidity: " + humidity);
                        Log.d("Weather", "Temperature: " + temperature);
                    }
                } else {
                    Log.e("Weather", "API request failed");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponseDTO> call, Throwable t) {
                Log.e("Weather", "API request failed: " + t.getMessage());
            }
        });
    }
}