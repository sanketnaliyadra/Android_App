package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerView = findViewById(R.id.rcvRecyclerView);

        productArrayList.add(new Product(51, "Bingo Game", "Kids bingo game that improve logic", R.drawable.bingo_game));
        productArrayList.add(new Product(539, "Bicycle", "Kids bicycle to buy and ride on roads.", R.drawable.kids_bicycle));
        productArrayList.add(new Product(16, "Toy Car", "Good Looking toy car to play with fun and play", R.drawable.toy_car));
        productArrayList.add(new Product(539, "Umbrella", "kids umbrella that is very attractive for fashion", R.drawable.kids_umbrella));
        productArrayList.add(new Product(23, "Sunglsses", "Sunglsses for kids that looks cool", R.drawable.sunglasses));
        productArrayList.add(new Product(41, "Watches", "Kids fashionable watch that very good looking", R.drawable.watch));
        productArrayList.add(new Product(51, "Bingo Game", "Kids bingo game that improve logic", R.drawable.bingo_game));
        productArrayList.add(new Product(539, "Bicycle", "Kids bicycle to buy and ride on roads.", R.drawable.kids_bicycle));

        ProductAdapter productAdapter = new ProductAdapter(productArrayList, this, new ClickListener() {
            @Override
            public void onClick(int i) {
                Intent intent = new Intent(ProductActivity.this, DetailActivity.class);
                Product product = productArrayList.get(i);
                intent.putExtra("price", product.price);
                intent.putExtra("title", product.title);
                intent.putExtra("subTitle", product.subTitle);
                intent.putExtra("imagerId", product.imageId);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(productAdapter);
    }
}