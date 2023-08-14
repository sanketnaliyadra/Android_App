package com.example.myapplication.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClickListener;
import com.example.myapplication.R;
import com.example.myapplication.data.Product;

import java.util.ArrayList;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {

    ArrayList<Product> list;

    Context context;

    public CartProductAdapter(ArrayList<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.view_cart_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void
    onBindViewHolder(final ViewHolder viewHolder, final int position) {
        int index = viewHolder.getAdapterPosition();
        viewHolder.productTitle.setText(list.get(position).title);
        viewHolder.productPrice.setText("$"+list.get(position).price);
        viewHolder.productImage.setImageResource(list.get(position).imageId);
        int count = (int)list.get(position).cartCount;
        viewHolder.cartCount.setText(""+count+"x");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productTitle, productPrice,cartCount;

        ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productTitle = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            cartCount = itemView.findViewById(R.id.cartCount);
        }
    }

}
