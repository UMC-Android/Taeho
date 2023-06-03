package com.example.chapter9_network;

import com.google.gson.annotations.SerializedName;

public class FertilityFeaturesDTO {

    @SerializedName("attributes")
    private FertilityAttributesDTO attributes;


    public FertilityAttributesDTO getAttributes() {
        return attributes;
    }

    public void setAttributes(FertilityAttributesDTO attributes) {
        this.attributes = attributes;
    }
}
