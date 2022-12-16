package com.example.wagba.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.OrderDetails.OrderDetailActivity;
import com.example.wagba.databinding.MyOrdersItemBinding;
import com.example.wagba.model.Order;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {
    List<Order> orders;
    Intent intent;
    public MyOrdersAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        MyOrdersItemBinding binding = MyOrdersItemBinding.inflate(inflater, parent, false);
        intent = new Intent(context, OrderDetailActivity.class);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.binding.tvName.setText(order.getRestaurantName());
        holder.binding.tvPrice.setText(order.getPrice());
        holder.binding.tvQuantity.setText(order.getQuantity());
        holder.binding.tvDate.setText(order.getDate());
        holder.binding.getRoot().setOnClickListener(view ->{
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        MyOrdersItemBinding binding;
        public ViewHolder(@NonNull MyOrdersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
