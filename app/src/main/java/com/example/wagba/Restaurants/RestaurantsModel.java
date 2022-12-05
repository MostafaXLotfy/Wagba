package com.example.wagba.Restaurants;

import android.graphics.drawable.Drawable;

public class RestaurantsModel {
    private String name;
    private String description;

    public RestaurantsModel(String name, String description){
        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
