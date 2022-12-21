package com.example.wagba.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.View.MealActivity;
import com.example.wagba.model.Meal;
import com.example.wagba.View.RestaurantActivity;
import com.example.wagba.databinding.MealsItemBinding;
import com.example.wagba.model.Restaurant;
import com.example.wagba.utils.Constant;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {
    private List<Meal> meals;
    RestaurantActivity activity;
    Restaurant restaurant;

    public MealsAdapter(List<Meal> meals, Restaurant restaurant) {
        this.meals = meals;
        this.restaurant = restaurant;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        activity = (RestaurantActivity) context;
        LayoutInflater inflater = LayoutInflater.from(context);
        MealsItemBinding binding = MealsItemBinding.inflate(inflater,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.binding.tvName.setText(meal.getName());
        holder.binding.tvDescription.setText(meal.getDescription());
        holder.binding.tvPrice.setText(Float.toString(meal.getPrice()));
        holder.binding.tvAvailable.setText(meal.isAvailable());
        Intent intent = new Intent(holder.binding.getRoot().getContext(), MealActivity.class);

        intent.putExtra(Constant.MEAL_DATA, meal);
        intent.putExtra(Constant.RESTAURANT_DATA, restaurant);

        holder.binding.btnAdd.setOnClickListener(view -> {
            onMealClicked(intent);
        });
        holder.binding.getRoot().setOnClickListener(view ->{
            onMealClicked(intent);
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    private void onMealClicked(Intent intent){
        activity.startActivity(intent);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        MealsItemBinding binding;
        public ViewHolder(@NonNull MealsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
