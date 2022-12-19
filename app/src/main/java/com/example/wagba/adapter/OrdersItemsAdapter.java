package com.example.wagba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.BasketOrderItemBinding;
import com.example.wagba.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrdersItemsAdapter extends RecyclerView.Adapter<OrdersItemsAdapter.ViewHolder> {
    List<OrderItem> orderItems;

    public OrdersItemsAdapter(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public OrdersItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        BasketOrderItemBinding binding = BasketOrderItemBinding.inflate(inflater,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersItemsAdapter.ViewHolder holder, int position) {
        OrderItem orderItem = orderItems.get(position);
        holder.binding.tvName.setText(orderItem.getMealName());
        holder.binding.tvPrice.setText(Float.toString(orderItem.getPrice()));
        holder.binding.tvQuantity.setText(Integer.toString(orderItem.getQuantity()));
        holder.binding.tvSize.setText(orderItem.getSize());
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BasketOrderItemBinding binding;

        public ViewHolder(@NonNull BasketOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
