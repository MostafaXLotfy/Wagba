package com.example.wagba.Restaurant;

import com.example.wagba.Restaurant.Meals.MealsModel;

import java.util.ArrayList;

public class RestaurantModel {
    private String name;
    private String description;
    private ArrayList<MealsModel> mealsModels;

    public RestaurantModel(String name, String description, ArrayList<MealsModel> mealsModels) {
        this.name = name;
        this.description = description;
        this.mealsModels = mealsModels;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<MealsModel> getMealsModels() {
        return mealsModels;
    }
}
