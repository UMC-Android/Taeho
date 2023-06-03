package com.example.chapter9_network;

import com.google.gson.annotations.SerializedName;

public class FertilityResponseDTO {

    @SerializedName("features")
    private FertilityFeaturesDTO[] features;


    public FertilityFeaturesDTO[] getFeatures() {
        return features;
    }

    public void setFeatures(FertilityFeaturesDTO[] features) {
        this.features = features;
    }

}
