package com.ibtikar.app.easyclean.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.easyclean.data.models.CleanerItemModel;

import java.util.ArrayList;

public class HomeResponse {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("List")
    @Expose
    private java.util.List<CleanerItemModel> list = null;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<CleanerItemModel> getList() {
        return new ArrayList<>(list);
    }

    public void setList(java.util.List<CleanerItemModel> list) {
        this.list = list;
    }

}
