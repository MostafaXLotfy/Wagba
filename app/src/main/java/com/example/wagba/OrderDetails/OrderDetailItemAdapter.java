package com.example.wagba.OrderDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.Basket.orderItems.OrderItemsModel;
import com.example.wagba.databinding.OrderDetailItemBinding;

import java.util.ArrayList;

public class OrderDetailItemAdapter extends RecyclerView.Adapter<OrderDetailItemAdapter.ViewHolder> {
    ArrayList<OrderItemsModel> orderItemsModels;

    public OrderDetailItemAdapter(ArrayList<OrderItemsModel> orderItemsModels){
        this.orderItemsModels = orderItemsModels;
    }
    @NonNull
    @Override
    public OrderDetailItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        OrderDetailItemBinding binding = OrderDetailItemBinding.inflate(inflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailItemAdapter.ViewHolder holder, int position) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder{
        OrderDetailItemBinding binding;

        public ViewHolder(@NonNull OrderDetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
