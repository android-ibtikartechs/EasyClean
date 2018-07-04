package com.ibtikar.app.easyclean.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CleanerDetails {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("details")
    @Expose
    private String details;
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
    @SerializedName("delivary_val")
    @Expose
    private int delivaryVal;
    @SerializedName("review")
    @Expose
    private int review;
    @SerializedName("comments")
    @Expose
    private int comments;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("Gallery")
    @Expose
    private List<Gallery> gallery = null;
    @SerializedName("Categories")
    @Expose
    private List<Category> categories = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public int getDelivaryVal() {
        return delivaryVal;
    }

    public void setDelivaryVal(int delivaryVal) {
        this.delivaryVal = delivaryVal;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<Gallery> getGallery() {
        return new ArrayList<>(gallery);
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public ArrayList<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
