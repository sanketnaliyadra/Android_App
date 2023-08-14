package com.example.myapplication.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.example.myapplication.R;
import com.example.myapplication.data.Product;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvTitle, tvSubTitle, tvPrice, item,itemPrice,editQnt;
    ImageButton back;
    Button save;
    AppCompatButton addQuantity,removeQuantity;
    CardView viewCartItem;
    int price = 0,pos=-1;
    Product product;
    float defaultCart = 0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setFindViewById();
        setData();
        showViewCartItem();
        setOnClickListner();
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        pos = getIntent().getIntExtra("pos", -1);
        product = new Gson().fromJson( getIntent().getStringExtra("product"),Product.class);
        price = product.price;
        defaultCart = product.cartCount;
        tvPrice.setText("Price: "+"$" + price);
        tvTitle.setText(product.title);
        tvSubTitle.setText(product.subTitle);
        imageView.setImageResource(product.imageId);
    }

    @SuppressLint("SetTextI18n")
    private void setOnClickListner() {
        back.setOnClickListener(v -> onBackPressed());

        save.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("pos", pos);
            resultIntent.putExtra("cartCount", defaultCart);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        addQuantity.setOnClickListener(v -> {
            defaultCart++;
            showViewCartItem();
        });

        removeQuantity.setOnClickListener(v -> {
            if (defaultCart > 0) {
                defaultCart--;
                showViewCartItem();
            }
        });

    }

    private void setFindViewById() {
        imageView = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.title);
        tvSubTitle = findViewById(R.id.subTitle);
        tvPrice = findViewById(R.id.price);
        back = findViewById(R.id.imageButtonBack);
        save = findViewById(R.id.save);
        addQuantity = findViewById(R.id.addQuantity);
        removeQuantity = findViewById(R.id.removeQuantity);
        editQnt = findViewById(R.id.editQnt);
        viewCartItem = findViewById(R.id.viewCartItem);
        item = findViewById(R.id.item);
        itemPrice = findViewById(R.id.itemPrice);
    }

    @SuppressLint("SetTextI18n")
    private void showViewCartItem(){
        editQnt.setText("" + defaultCart);
        defaultCart = Float.parseFloat(editQnt.getText().toString());
        if(defaultCart == 0){
            save.setText("Save");
        }else{
            save.setText("Add to cart");
        }
        if (defaultCart > 0.0) {
            viewCartItem.setVisibility(View.VISIBLE);
            item.setText(editQnt.getText().toString() + " Item");
            itemPrice.setText("$" + defaultCart * price);
        } else {
            viewCartItem.setVisibility(View.GONE);
        }
    }
}