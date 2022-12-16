package com.example.wagba.Restaurants;

public class Restaurant {
    private String name;
    private String description;

    public Restaurant(String name, String description){
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
