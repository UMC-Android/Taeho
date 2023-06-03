package com.example.chapter9_network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidStatusAPI {
    @GET("covid19CurrentStatusConfirmationsJson")
    Call<CovidResponseDTO> getCovidStatus(@Query("serviceKey") String serviceKey);
}
