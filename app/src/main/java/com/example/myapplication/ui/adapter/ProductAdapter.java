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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> list;

    Context context;
    ClickListener listener;

    public ProductAdapter(ArrayList<Product> list, Context context, ClickListener clickListener) {
        this.list = list;
        this.context = context;
        this.listener = clickListener;
    }

    @Override
    public ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void
    onBindViewHolder(final ViewHolder viewHolder, final int position) {
        int index = viewHolder.getAdapterPosition();
        viewHolder.productTitle.setText(list.get(position).title);
        viewHolder.productSubTitle.setText(list.get(position).subTitle);
        viewHolder.productPrice.setText("$"+list.get(position).price);
        viewHolder.productImage.setImageResource(list.get(position).imageId);
        if(list.get(position).cartCount > 0){
            viewHolder.cartCount.setVisibility(View.VISIBLE);
            int count = (int)list.get(position).cartCount;
            viewHolder.cartCount.setText(""+count);
        }else{
            viewHolder.cartCount.setVisibility(View.GONE);
        }

        viewHolder.view.setOnClickListener(view -> listener.onClick(index));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productTitle, productSubTitle, productPrice,cartCount;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productTitle = itemView.findViewById(R.id.productTitle);
            productSubTitle = itemView.findViewById(R.id.productSubTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            cartCount = itemView.findViewById(R.id.cartCount);
            view = itemView;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Product> list){
        this.list = list;
        notifyDataSetChanged();
    }

}
