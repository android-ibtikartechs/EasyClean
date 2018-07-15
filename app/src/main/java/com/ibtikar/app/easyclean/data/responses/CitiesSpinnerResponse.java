package com.ibtikar.app.easyclean.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.easyclean.data.models.City;

import java.util.ArrayList;

public class CitiesSpinnerResponse {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("List")
    @Expose
    private java.util.List<City> list = null;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<City> getList() {
        return new ArrayList<>(list);
    }

    public void setList(java.util.List<City> list) {
        this.list = list;
    }

}
