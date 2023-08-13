package com.example.myapplication.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.example.myapplication.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvTitle, tvSubTitle, tvPrice,viewCart, item,itemPrice;
    ImageButton back;
    AppCompatButton addQuantity,removeQuantity;
    EditText editQnt;
    CardView viewCartItem;
    int price = 0;

    float defaultCart = 0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setFindViewById();
        setData();
        setOnClickListner();
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        price = getIntent().getIntExtra("price", 0);
        String title = getIntent().getStringExtra("title");
        String subTitle = getIntent().getStringExtra("subTitle");
        int imagerId = getIntent().getIntExtra("imagerId", R.drawable.bingo_game);

        tvPrice.setText("Price: "+"$" + price);
        tvTitle.setText(title);
        tvSubTitle.setText(subTitle);
        imageView.setImageResource(imagerId);
    }

    @SuppressLint("SetTextI18n")
    private void setOnClickListner() {
        back.setOnClickListener(v -> onBackPressed());

        viewCart.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });

        addQuantity.setOnClickListener(v -> {
            defaultCart++;
            editQnt.setText("" + defaultCart);
            showViewCartItem();
        });

        removeQuantity.setOnClickListener(v -> {
            if (defaultCart > 0) {
                defaultCart--;
                editQnt.setText("" + defaultCart);
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
        viewCart = findViewById(R.id.viewCart);
        addQuantity = findViewById(R.id.addQuantity);
        removeQuantity = findViewById(R.id.removeQuantity);
        editQnt = findViewById(R.id.editQnt);
        viewCartItem = findViewById(R.id.viewCartItem);
        item = findViewById(R.id.item);
        itemPrice = findViewById(R.id.itemPrice);
    }

    @SuppressLint("SetTextI18n")
    private void showViewCartItem(){
        defaultCart = Float.parseFloat(editQnt.getText().toString());
        if (defaultCart > 0.0) {
            viewCartItem.setVisibility(View.VISIBLE);
            item.setText(editQnt.getText().toString() + " Item");
            itemPrice.setText("$" + defaultCart * price);
        } else {
            viewCartItem.setVisibility(View.GONE);
        }
    }
}