package com.ibtikar.app.easyclean.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CleanerItemModel {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mincharge")
    @Expose
    private int mincharge;
    @SerializedName("visa")
    @Expose
    private boolean visa;
    @SerializedName("mastercard")
    @Expose
    private boolean mastercard;
    @SerializedName("delivary")
    @Expose
    private boolean delivary;
    @SerializedName("review")
    @Expose
    private int review;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMincharge() {
        return mincharge;
    }

    public void setMincharge(int mincharge) {
        this.mincharge = mincharge;
    }

    public boolean isVisa() {
        return visa;
    }

    public void setVisa(boolean visa) {
        this.visa = visa;
    }

    public boolean isMastercard() {
        return mastercard;
    }

    public void setMastercard(boolean mastercard) {
        this.mastercard = mastercard;
    }

    public boolean isDelivary() {
        return delivary;
    }

    public void setDelivary(boolean delivary) {
        this.delivary = delivary;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
