package com.example.wagba.OrderDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.model.OrderItem;
import com.example.wagba.databinding.OrderDetailItemBinding;

import java.util.List;

public class OrderDetailItemAdapter extends RecyclerView.Adapter<OrderDetailItemAdapter.ViewHolder> {
    List<OrderItem> orderItems;

    public OrderDetailItemAdapter(List<OrderItem> orderItems){
        this.orderItems = orderItems;
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

    public static class ViewHolder extends RecyclerView.ViewHolder{
        OrderDetailItemBinding binding;

        public ViewHolder(@NonNull OrderDetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
