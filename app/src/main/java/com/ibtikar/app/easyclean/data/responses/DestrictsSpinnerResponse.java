package com.ibtikar.app.easyclean.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.easyclean.data.models.Destrict;

import java.util.ArrayList;

public class DestrictsSpinnerResponse {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("List")
    @Expose
    private java.util.List<Destrict> list = null;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Destrict> getList() {
        return new ArrayList<>(list);
    }

    public void setList(java.util.List<Destrict> list) {
        this.list = list;
    }

}
