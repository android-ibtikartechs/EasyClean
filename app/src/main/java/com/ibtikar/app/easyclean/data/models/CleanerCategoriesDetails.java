package com.ibtikar.app.easyclean.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibtikar.app.easyclean.data.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CleanerCategoriesDetails {
    @SerializedName("Categories")
    @Expose
    private List<Category> categories = null;

    public ArrayList<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
