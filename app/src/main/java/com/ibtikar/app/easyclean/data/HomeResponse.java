package com.ibtikar.app.easyclean.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.easyclean.data.models.CleanerItemModel;

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

    public java.util.List<CleanerItemModel> getList() {
        return list;
    }

    public void setList(java.util.List<CleanerItemModel> list) {
        this.list = list;
    }

}
