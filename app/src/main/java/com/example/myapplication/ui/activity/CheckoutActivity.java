package com.example.myapplication.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class CheckoutActivity extends AppCompatActivity {

    ImageButton back;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        back = findViewById(R.id.imageButtonBack);
        button = findViewById(R.id.button);
        back.setOnClickListener(v -> onBackPressed());
        button.setOnClickListener(v -> {
            //Validation Requried...
            showDialog(CheckoutActivity.this,
                    "Order Successful",
                    ResourcesCompat.getDrawable(getResources(), R.drawable.ic_success, null),
                    2000);
            new Handler().postDelayed(() -> {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            },1000);
        });
    }

    public void showDialog(Activity context, String message, Drawable icon, Integer durationMillis) {
        try {
            Dialog dialog = new Dialog(context);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.r_custom_dialog_layout);
            TextView dialogTextView = dialog.findViewById(R.id.diaTextView);
            ImageView dialogImgView = dialog.findViewById(R.id.diaImageView);
            dialogTextView.setText(Html.fromHtml(message));
            dialogImgView.setImageDrawable(icon);
            dialog.show();
            final Handler handler = new Handler();
            handler.postDelayed(dialog::cancel, durationMillis);
        } catch (Exception e) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}