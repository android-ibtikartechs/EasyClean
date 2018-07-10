package com.ibtikar.app.easyclean.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.easyclean.data.models.CleanerCategoriesDetails;

public class CleanerCategoriesResponse {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("Details")
    @Expose
    private CleanerCategoriesDetails details;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CleanerCategoriesDetails getDetails() {
        return details;
    }

    public void setDetails(CleanerCategoriesDetails details) {
        this.details = details;
    }
}
