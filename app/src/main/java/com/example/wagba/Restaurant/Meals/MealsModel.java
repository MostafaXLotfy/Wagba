package com.example.wagba.Restaurant.Meals;

public class MealsModel {
    private String name;
    private String description;
    private float price;
    private boolean available;

    public MealsModel(String name, String description, float price, boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return Float.toString(price);
    }

    public String isAvailable() {
        return available ? "Available": "Not Available";
    }
}
