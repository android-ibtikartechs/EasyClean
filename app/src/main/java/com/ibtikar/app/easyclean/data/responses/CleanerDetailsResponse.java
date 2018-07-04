package com.ibtikar.app.easyclean.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.easyclean.data.models.CleanerDetails;

public class CleanerDetailsResponse {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("Details")
    @Expose
    private CleanerDetails details;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CleanerDetails getDetails() {
        return details;
    }

    public void setDetails(CleanerDetails details) {
        this.details = details;
    }
}
