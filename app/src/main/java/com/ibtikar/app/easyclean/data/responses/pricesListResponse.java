package com.ibtikar.app.easyclean.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.easyclean.data.models.PricingDetails;

public class pricesListResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Details")
    @Expose
    private PricingDetails details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PricingDetails getDetails() {
        return details;
    }

    public void setDetails(PricingDetails details) {
        this.details = details;
    }

}
