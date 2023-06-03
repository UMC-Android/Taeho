package com.example.chapter9_network;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ResponseData {
    @SerializedName("result")
    private List<ResultData> result;

    public List<ResultData> getResult() {
        return result;
    }

    public void setResult(List<ResultData> result) {
        this.result = result;
    }
}
