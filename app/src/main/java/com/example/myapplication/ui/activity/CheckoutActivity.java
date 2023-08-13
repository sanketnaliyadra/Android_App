package com.example.myapplication.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.myapplication.R;

public class CheckoutActivity extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        back = findViewById(R.id.imageButtonBack);
        back.setOnClickListener(v -> onBackPressed());
    }
}