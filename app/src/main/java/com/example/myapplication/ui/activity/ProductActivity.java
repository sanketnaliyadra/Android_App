package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClickListener;
import com.example.myapplication.data.Product;
import com.example.myapplication.ui.adapter.ProductAdapter;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton back;
    FloatingActionButton fabAdd;

    ArrayList<Product> productArrayList = new ArrayList<>();
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerView = findViewById(R.id.rcvRecyclerView);
        back = findViewById(R.id.imageButtonBack);
        fabAdd = findViewById(R.id.fabAdd);
        setData();
        back.setOnClickListener(v -> onBackPressed());
        fabAdd.setOnClickListener(v -> {
             Intent intent = new Intent(ProductActivity.this, ViewCartActivity.class);
             intent.putExtra("viewCartList",new Gson().toJson(getModelsWithValueGreaterThanZero(productArrayList)));
             startActivityForResult(intent,200);
        });
    }

    private void setData() {
        try {
            /*Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.product_list);
            Scanner scanner = new Scanner(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
            String jsonContent = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray(jsonContent);
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
            }*/
            productArrayList.add(new Product(51, "Bingo Game", "Kids bingo game that improve logic", R.drawable.bingo_game));
            productArrayList.add(new Product(539, "Bicycle", "Kids bicycle to buy and ride on roads.", R.drawable.kids_bicycle));
            productArrayList.add(new Product(16, "Toy Car", "Good Looking toy car to play with fun and play", R.drawable.toy_car));
            productArrayList.add(new Product(539, "Umbrella", "kids umbrella that is very attractive for fashion", R.drawable.kids_umbrella));
            productArrayList.add(new Product(23, "Sunglsses", "Sunglsses for kids that looks cool", R.drawable.sunglasses));
            productArrayList.add(new Product(41, "Watches", "Kids fashionable watch that very good looking", R.drawable.watch));
            productArrayList.add(new Product(51, "Bingo Game", "Kids bingo game that improve logic", R.drawable.bingo_game));
            productArrayList.add(new Product(539, "Bicycle", "Kids bicycle to buy and ride on roads.", R.drawable.kids_bicycle));
            new Gson().toJson(productArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productAdapter = new ProductAdapter(productArrayList, this, new ClickListener() {
            @Override
            public void onClick(int i) {
                Intent intent = new Intent(ProductActivity.this, DetailActivity.class);
                Product product = productArrayList.get(i);
                intent.putExtra("product",new Gson().toJson(product));
                intent.putExtra("pos", i);
                startActivityForResult(intent,100);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 100){
                assert data != null;
                Bundle extras = data.getExtras();
                if (extras != null) {
                    int pos = extras.getInt("pos");
                    float cartCount = extras.getFloat("cartCount");
                    productArrayList.get(pos).setCartCount(cartCount);
                    productAdapter.setData(productArrayList);
                    viewCartItem();
                }
            }

            if(requestCode == 200){
                setAllValuesToZero();
                productAdapter.setData(productArrayList);
                viewCartItem();
            }

        }
    }


    void viewCartItem(){
        if(hasValueGreaterThanOne(productArrayList)){
            fabAdd.setVisibility(View.VISIBLE);
        }else{
            fabAdd.setVisibility(View.GONE);
        }
    }

    private boolean hasValueGreaterThanOne(ArrayList<Product> models) {
        for (Product model : models) {
            if (model.cartCount > 0) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Product> getModelsWithValueGreaterThanZero(ArrayList<Product> models) {
        ArrayList<Product> filteredModels = new ArrayList<>();
        for (Product model : models) {
            if (model.cartCount > 0) {
                filteredModels.add(model);
            }
        }
        return filteredModels;
    }

    private void setAllValuesToZero() {
        for (Product model : productArrayList) {
            model.setCartCount(0);
        }
    }
}