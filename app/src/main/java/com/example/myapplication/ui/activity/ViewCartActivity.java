package com.example.myapplication.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.Product;
import com.example.myapplication.ui.adapter.CartProductAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ViewCartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton back;
    Button checkout;
    TextView cartItem,totalCount;
    ArrayList<Product> cartProductArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        recyclerView = findViewById(R.id.rcvRecyclerView);
        back = findViewById(R.id.imageButtonBack);
        cartItem = findViewById(R.id.cartItem);
        totalCount = findViewById(R.id.totalCount);
        checkout = findViewById(R.id.checkout);

        back.setOnClickListener(v -> onBackPressed());
        checkout.setOnClickListener(v -> {
            Intent intent = new Intent(ViewCartActivity.this, CheckoutActivity.class);
            startActivityForResult(intent,200);
        });

        getData();
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        cartProductArrayList = new Gson().fromJson(
                getIntent().getStringExtra("viewCartList"),
                new TypeToken<ArrayList<Product>>() {}.getType()
        );

        if(cartProductArrayList.size()>1){
            cartItem.setText(""+cartProductArrayList.size()+" Items");
        }else{
            cartItem.setText(""+cartProductArrayList.size()+" Item");
        }

        totalCount.setText("$"+getTotal());

        CartProductAdapter cartProductAdapter = new CartProductAdapter(cartProductArrayList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(cartProductAdapter);
    }

    float getTotal(){
        float total = 0;
        for (Product model : cartProductArrayList) {
            total += model.cartCount*model.price;
        }
        return total;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==200){
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}