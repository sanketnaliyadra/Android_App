package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> list = new ArrayList<>();

    Context context;
    ClickListener listiner;

    public ProductAdapter(ArrayList<Product> list, Context context, ClickListener clickListener) {
        this.list = list;
        this.context = context;
        this.listiner = clickListener;
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

    @Override
    public void
    onBindViewHolder(final ViewHolder viewHolder, final int position) {
        int index = viewHolder.getAdapterPosition();
        viewHolder.productTitle.setText(list.get(position).title);
        viewHolder.productSubTitle.setText(list.get(position).subTitle);
        viewHolder.productPrice.setText("$"+list.get(position).price);
        viewHolder.productImage.setImageResource(list.get(position).imageId);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listiner.onClick(index);
            }
        });
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
        TextView productTitle, productSubTitle, productPrice;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
            productTitle = (TextView) itemView.findViewById(R.id.productTitle);
            productSubTitle = (TextView) itemView.findViewById(R.id.productSubTitle);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
            view = itemView;
        }
    }


}
