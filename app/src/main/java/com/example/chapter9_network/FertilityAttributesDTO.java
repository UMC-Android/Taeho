package com.example.chapter9_network;

import com.google.gson.annotations.SerializedName;

public class FertilityAttributesDTO {

    @SerializedName("sig_kor_nm")
    private String sig_kor_nm;
    @SerializedName("f2017")
    private double f2017;

    public String getSig_kor_nm() {
        return sig_kor_nm;
    }

    public void setSig_kor_nm(String sig_kor_nm) {
        this.sig_kor_nm = sig_kor_nm;
    }

    public double getF2017() {
        return f2017;
    }

    public void setF2017(double f2017) {
        this.f2017 = f2017;
    }

}
