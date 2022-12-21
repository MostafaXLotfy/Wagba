package com.example.wagba.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.View.RestaurantActivity;
import com.example.wagba.databinding.RestaurantsItemBinding;
import com.example.wagba.model.Restaurant;
import com.example.wagba.utils.Constant;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>{
    List<Restaurant> restaurantsModels;

    public RestaurantsAdapter(List<Restaurant> restaurantsModels){
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
        Restaurant restaurantsModel = restaurantsModels.get(position);
        holder.binding.tvName.setText(restaurantsModel.getName());
        holder.binding.tvDescription.setText(restaurantsModel.getDescription());

        holder.binding.getRoot().setOnClickListener(view ->{
            Context context = view.getContext();
            Intent intent = new Intent(context, RestaurantActivity.class);
            intent.putExtra(Constant.RESTAURANT_DATA, restaurantsModel);
            context.startActivity(intent);
        });
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
