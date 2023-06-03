package com.example.chapter9_network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.GsonBuilder;

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
                        Log.d("Weather", "Humidity: " + humidity + "%");
                        Log.d("Weather", "Temperature(K): " + temperature);
                        Log.d("Weather", "Temperature(˚C): " + String.format("%.2f", (temperature - 273.15)));
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

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://smart.incheon.go.kr/server/rest/services/Hosted/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IncheonFertilityRateAPI fertilityAPI = retrofit2.create(IncheonFertilityRateAPI.class);
        Call<FertilityResponseDTO> call2 = fertilityAPI.getFertilityData("sig_kor_nm='동구'", "*", "4326", "json");
        call2.enqueue(new Callback<FertilityResponseDTO>() {
            @Override
            public void onResponse(Call<FertilityResponseDTO> call, Response<FertilityResponseDTO> response) {
                if (response.isSuccessful()) {
                    FertilityResponseDTO fertilityResponse = response.body();
                    if (fertilityResponse != null) {
                        String Location = fertilityResponse.getFeatures()[0].getAttributes().getSig_kor_nm();
                        double Fertility = fertilityResponse.getFeatures()[0].getAttributes().getF2017();
                        Log.d("Fertility", "Location: " + Location);
                        Log.d("Fertility", "Fertility for 2017: " + Fertility);
                    }
                } else {
                    Log.e("Fertility", "API request failed");
                }
            }

            @Override
            public void onFailure(Call<FertilityResponseDTO> call, Throwable t) {
                Log.e("Fertility", "API request failed");
            }
        });

        Retrofit retrofit3 = new Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/1790387/covid19CurrentStatusConfirmations/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        CovidStatusAPI covidAPI = retrofit3.create(CovidStatusAPI.class);

        Call<CovidResponseDTO> call3 = covidAPI.getCovidStatus("4O0lM8gAWi0D53hASZ%2BAXYrse4Y4b46c97nAwICyhyB2qfKUzqnr5nASQ6wgXSbAJV2i8jojOo5cDCP9GqjbGA%3D%3D");
        call3.enqueue(new Callback<CovidResponseDTO>() {
            @Override
            public void onResponse(Call<CovidResponseDTO> call, Response<CovidResponseDTO> response) {
                if (response.isSuccessful()) {
                    CovidResponseDTO covidResponseDTO = response.body();
                    if (covidResponseDTO != null) {
                        String cnt8 = covidResponseDTO.getResponse().getResult().get(0).getCnt8();
                        Log.d("Covid", "주간 일평균: " + cnt8);
                    }
                } else {
                    Log.e("Covid", "API request failed");
                }
            }

            @Override
            public void onFailure(Call<CovidResponseDTO> call, Throwable t) {
                Log.e("Covid", "API request failed: " + t.getMessage());
            }
        });
    }
}