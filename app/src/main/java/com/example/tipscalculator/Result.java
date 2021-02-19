package com.example.tipscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    //tag
    private static final String TAG = Result.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get intent that started activity, and get extras
        Intent intent = getIntent();
        String tip = intent.getStringExtra("tip");
        String totalPrice = intent.getStringExtra("totalPrice");
        Log.d(TAG, tip + " " + totalPrice);

        //initialize layout elements
        TextView tipTextView = (TextView) findViewById(R.id.tipTextView);
        TextView totalPriceTextView = (TextView) findViewById(R.id.totalPriceTextView);

        tipTextView.setText("Tip: " + tip);
        totalPriceTextView.setText("Total price: " + totalPrice);
    }
}