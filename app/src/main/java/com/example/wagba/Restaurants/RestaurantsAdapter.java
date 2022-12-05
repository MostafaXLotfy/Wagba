package com.example.wagba.Restaurants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.RestaurantsItemBinding;

import java.util.ArrayList;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>{
    ArrayList<RestaurantsModel> restaurantsModels;

    public RestaurantsAdapter(ArrayList<RestaurantsModel> restaurantsModels){
        this.restaurantsModels  = restaurantsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RestaurantsItemBinding binding = RestaurantsItemBinding.inflate(inflater,parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestaurantsModel restaurantsModel = restaurantsModels.get(position);
        holder.binding.tvName.setText(restaurantsModel.getName());
        holder.binding.tvDescription.setText(restaurantsModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return restaurantsModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        RestaurantsItemBinding binding;


        public ViewHolder(RestaurantsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
