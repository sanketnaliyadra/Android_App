package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClickListener;
import com.example.myapplication.data.Product;
import com.example.myapplication.ui.ProductAdapter;
import com.example.myapplication.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton back;

    ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerView = findViewById(R.id.rcvRecyclerView);
        back = findViewById(R.id.imageButtonBack);
        setData();
        back.setOnClickListener(v -> onBackPressed());
    }

    private void setData() {
        try {
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.product_list);
            Scanner scanner = new Scanner(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
            String jsonContent = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray(jsonContent);

            /*productArrayList.add(new Product(51, "Bingo Game", "Kids bingo game that improve logic", R.drawable.bingo_game));
            productArrayList.add(new Product(539, "Bicycle", "Kids bicycle to buy and ride on roads.", R.drawable.kids_bicycle));
            productArrayList.add(new Product(16, "Toy Car", "Good Looking toy car to play with fun and play", R.drawable.toy_car));
            productArrayList.add(new Product(539, "Umbrella", "kids umbrella that is very attractive for fashion", R.drawable.kids_umbrella));
            productArrayList.add(new Product(23, "Sunglsses", "Sunglsses for kids that looks cool", R.drawable.sunglasses));
            productArrayList.add(new Product(41, "Watches", "Kids fashionable watch that very good looking", R.drawable.watch));
            productArrayList.add(new Product(51, "Bingo Game", "Kids bingo game that improve logic", R.drawable.bingo_game));
            productArrayList.add(new Product(539, "Bicycle", "Kids bicycle to buy and ride on roads.", R.drawable.kids_bicycle));
            new Gson().toJson(productArrayList);*/
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int imageId = jsonObject.getInt("imageId");
                int price = jsonObject.getInt("price");
                String subTitle = jsonObject.getString("subTitle");
                String title = jsonObject.getString("title");
                Product product = new Product();
                product.setImageId(imageId);
                product.setPrice(price);
                product.setSubTitle(subTitle);
                product.setTitle(title);
                productArrayList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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