package com.example.wagba.Restaurant.Meals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.MealsItemBinding;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {
    private ArrayList<MealsModel> mealsModels;

    public MealsAdapter(ArrayList<MealsModel> mealsModels) {
        this.mealsModels = mealsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
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
