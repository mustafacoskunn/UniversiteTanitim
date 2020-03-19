package com.tanitim.universitetanitim.retrofit;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tanitim.universitetanitim.Models.Universiteler;

public class UniversitelerCevap {

    @SerializedName("universiteler")
    @Expose
    private List<Universiteler> universiteler = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Universiteler> getUniversiteler() {
        return universiteler;
    }

    public void setUniversiteler(List<Universiteler> universiteler) {
        this.universiteler = universiteler;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
