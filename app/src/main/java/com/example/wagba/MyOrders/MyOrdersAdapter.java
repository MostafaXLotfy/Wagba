package com.example.wagba.MyOrders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.OrderDetails.OrderDetailActivity;
import com.example.wagba.databinding.MyOrdersItemBinding;
import com.example.wagba.databinding.OrderItemsItemBinding;

import java.util.ArrayList;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {
    ArrayList<MyOrdersModel> myOrdersModels;
    Intent intent;
    public MyOrdersAdapter(ArrayList<MyOrdersModel> myOrdersModels) {
        this.myOrdersModels = myOrdersModels;
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
        MyOrdersModel myOrdersModel = myOrdersModels.get(position);
        holder.binding.tvName.setText(myOrdersModel.getRestaurantName());
        holder.binding.tvPrice.setText(myOrdersModel.getPrice());
        holder.binding.tvQuantity.setText(myOrdersModel.getQuantity());
        holder.binding.tvDate.setText(myOrdersModel.getDate());
        holder.binding.getRoot().setOnClickListener(view ->{
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return myOrdersModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        MyOrdersItemBinding binding;
        public ViewHolder(@NonNull MyOrdersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
