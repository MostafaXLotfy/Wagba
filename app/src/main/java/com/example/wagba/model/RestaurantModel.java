package com.example.wagba.model;

import com.example.wagba.Restaurant.Meals.MealsModel;

import java.util.ArrayList;

public class RestaurantModel {
    private String name;
    private String description;
    private double deliveryFees;
    private ArrayList<MealsModel> mealsModels;

    public RestaurantModel(){}

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public ArrayList<MealsModel> getMealsModels() {
        return mealsModels;
    }
}
