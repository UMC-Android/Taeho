package com.example.chapter9_network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IncheonFertilityRateAPI {

    @GET("군구별_합계출산율_정보/FeatureServer/4/query")
    Call<FertilityResponseDTO> getFertilityData(
            @Query("where") String where,
            @Query("outFields") String outFields,
            @Query("outSR") String outSR,
            @Query("f") String format
    );

}
