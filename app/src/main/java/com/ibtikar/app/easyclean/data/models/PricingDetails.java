package com.ibtikar.app.easyclean.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PricingDetails {
    @SerializedName("Items")
    @Expose
    private List<PricingItem> items = null;

    public ArrayList<PricingItem> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<PricingItem> items) {
        this.items = items;
    }
}
