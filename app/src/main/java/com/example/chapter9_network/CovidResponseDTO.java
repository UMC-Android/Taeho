package com.example.chapter9_network;

import com.google.gson.annotations.SerializedName;

public class CovidResponseDTO {
    @SerializedName("response")
    private ResponseData response;

    public ResponseData getResponse() {
        return response;
    }

    public void setResponse(ResponseData response) {
        this.response = response;
    }
}
