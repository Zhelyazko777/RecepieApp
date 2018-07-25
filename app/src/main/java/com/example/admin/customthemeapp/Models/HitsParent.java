package com.example.admin.customthemeapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HitsParent {
    @SerializedName("hits")
    private List<Hit> hits;

    public List<Hit> getRecipies() {
        return hits;
    }

    public void setRecipies(ArrayList<Hit> hits) {
        this.hits = hits;
    }

}
