package com.example.wagba.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.MealActivity;
import com.example.wagba.model.MealsModel;
import com.example.wagba.View.RestaurantActivity;
import com.example.wagba.databinding.MealsItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {
    private List<MealsModel> mealsModels;
    RestaurantActivity activity;
    Intent intent;

    public MealsAdapter(List<MealsModel> mealsModels) {
        this.mealsModels = mealsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        activity = (RestaurantActivity) context;
        intent = new Intent(context, MealActivity.class);
        LayoutInflater inflater = LayoutInflater.from(context);
        MealsItemBinding binding = MealsItemBinding.inflate(inflater,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsModel mealsModel = mealsModels.get(position);
        holder.binding.tvName.setText(mealsModel.getName());
        holder.binding.tvDescription.setText(mealsModel.getDescription());
        holder.binding.tvPrice.setText(mealsModel.getPrice());
        holder.binding.tvAvailable.setText(mealsModel.isAvailable());
        holder.binding.btnAdd.setOnClickListener(view -> {
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mealsModels.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        MealsItemBinding binding;
        public ViewHolder(@NonNull MealsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
