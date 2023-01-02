package com.example.wagba.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.View.OrderDetailActivity;
import com.example.wagba.databinding.MyOrdersItemBinding;
import com.example.wagba.model.Order;
import com.example.wagba.utils.Constant;
import com.example.wagba.utils.ImageLoader;

import java.text.MessageFormat;
import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {
    List<Order> orders;
    Context context;

    public MyOrdersAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        MyOrdersItemBinding binding = MyOrdersItemBinding.inflate(inflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.binding.tvName.setText(order.getRestaurantName());
        holder.binding.tvPrice.setText(MessageFormat.format("{0} EGP",
                Float.toString(order.getPrice())));
        holder.binding.tvQuantity.setText(MessageFormat.format("{0} items ordered",
                Integer.toString(order.getQuantity())));
        holder.binding.tvDate.setText(order.getDate());
        ImageLoader.load(context, holder.binding.ivLogo, order.getLogo());
        Intent intent = new Intent(holder.binding.getRoot().getContext(),
                OrderDetailActivity.class);
        intent.putExtra(Constant.ORDER_ID, order.getUid());
        holder.binding.getRoot().setOnClickListener(view -> {
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MyOrdersItemBinding binding;

        public ViewHolder(@NonNull MyOrdersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
