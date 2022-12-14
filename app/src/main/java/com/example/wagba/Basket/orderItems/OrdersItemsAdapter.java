package com.example.wagba.Basket.orderItems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.BasketOrderItemBinding;

import java.util.ArrayList;

public class OrdersItemsAdapter extends RecyclerView.Adapter<OrdersItemsAdapter.ViewHolder> {
    ArrayList<OrderItemsModel> orderItemsModels;

    public OrdersItemsAdapter(ArrayList<OrderItemsModel> orderItemsModels) {
        this.orderItemsModels = orderItemsModels;
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
        OrderItemsModel orderItemsModel = orderItemsModels.get(position);
        holder.binding.tvName.setText(orderItemsModel.getMealName());
        holder.binding.tvPrice.setText(orderItemsModel.getPrice());
        holder.binding.tvQuantity.setText(orderItemsModel.getQuantity());
        holder.binding.tvSize.setText(orderItemsModel.getSize());
    }

    @Override
    public int getItemCount() {
        return orderItemsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BasketOrderItemBinding binding;

        public ViewHolder(@NonNull BasketOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
