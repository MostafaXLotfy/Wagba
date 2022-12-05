package com.example.wagba.Basket.OrderDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.OrderDetailsItemBinding;

import java.util.ArrayList;

public class OrdersDetailsAdapter extends RecyclerView.Adapter<OrdersDetailsAdapter.ViewHolder> {
    ArrayList<OrderDetailsModel> orderDetailsModels;

    public OrdersDetailsAdapter(ArrayList<OrderDetailsModel> orderDetailsModels) {
        this.orderDetailsModels = orderDetailsModels;
    }

    @NonNull
    @Override
    public OrdersDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        OrderDetailsItemBinding binding = OrderDetailsItemBinding.inflate(inflater,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersDetailsAdapter.ViewHolder holder, int position) {
        OrderDetailsModel orderDetailsModel = orderDetailsModels.get(position);
        holder.binding.tvName.setText(orderDetailsModel.getMealName());
        holder.binding.tvPrice.setText(orderDetailsModel.getPrice());
        holder.binding.tvQuantity.setText(orderDetailsModel.getQuantity());
        holder.binding.tvSize.setText(orderDetailsModel.getSize());
    }

    @Override
    public int getItemCount() {
        return orderDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        OrderDetailsItemBinding binding;
        public ViewHolder(@NonNull OrderDetailsItemBinding binding) {
            super(binding.getRoot());
            this.binding  = binding;
        }
    }
}
