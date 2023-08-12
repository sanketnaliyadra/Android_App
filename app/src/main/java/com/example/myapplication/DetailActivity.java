package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvTitle, tvSubTitle, tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int price = getIntent().getIntExtra("price", 0);
        String title = getIntent().getStringExtra("title");
        String subTitle = getIntent().getStringExtra("subTitle");
        int imagerId = getIntent().getIntExtra("imagerId", R.drawable.bingo_game);

        imageView = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.title);
        tvSubTitle = findViewById(R.id.subTitle);
        tvPrice = findViewById(R.id.price);

        tvPrice.setText("$" + price);
        tvTitle.setText(title);
        tvSubTitle.setText(subTitle);
        imageView.setImageResource(imagerId);
    }
}