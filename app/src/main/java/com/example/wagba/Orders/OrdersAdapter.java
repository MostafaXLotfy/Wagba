package com.example.wagba.Orders;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.OrdersItemBinding;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    ArrayList<OrdersModel> ordersModels;
    public OrdersAdapter(ArrayList<OrdersModel> ordersModels) {
        this.ordersModels = ordersModels;
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        OrdersItemBinding binding = OrdersItemBinding.inflate(inflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        OrdersModel ordersModel = ordersModels.get(position);
        holder.binding.tvName.setText(ordersModel.getRestaurantName());
        holder.binding.tvPrice.setText(ordersModel.getPrice());
        holder.binding.tvQuantity.setText(ordersModel.getQuantity());
        holder.binding.tvDate.setText(ordersModel.getDate());
    }

    @Override
    public int getItemCount() {
        return ordersModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        OrdersItemBinding binding;
        public ViewHolder(@NonNull OrdersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
