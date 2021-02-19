package com.example.tipscalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //using google's TextInputEditText and TextInputLayout for better input error handling
        //initialize layout elements
        Button calculateButton = (Button) findViewById(R.id.calculateButton);
        TextInputEditText priceEditText = (TextInputEditText) findViewById(R.id.priceEditText);
        TextInputEditText percentEditText = (TextInputEditText) findViewById(R.id.percentEditText);
        TextInputEditText numPeopleEditText = (TextInputEditText) findViewById(R.id.numPeopleEditText);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priceIn = priceEditText.getText().toString();
                String percentIn = percentEditText.getText().toString();
                String numPeopleIn = numPeopleEditText.getText().toString();


                if (priceIn.equals("")) {
                    //set error if empty and halt
                    priceEditText.setError("Price input required");
                    return;
                }
                if (percentIn.equals("")) {
                    //set error if empty and halt
                    percentEditText.setError("% tip input required");
                    return;
                }
                if (numPeopleIn.equals("")) {
                    //set error if empty and halt
                    numPeopleEditText.setError("Number of people input required");
                    return;
                }

                //parsing input values
                double price = Double.parseDouble(priceIn);
                int percent = Integer.parseInt(percentIn);
                int numPeople = Integer.parseInt(numPeopleIn);

                //checking input value of percent, must be 0-100
                if (percent < 0 || percent > 100) {
                    //set error if not within range and halt
                    percentEditText.setError("% tip must be wthin 0-100");
                    return;
                }
                if (numPeople < 1) {
                    //set error if 0 and halt
                    numPeopleEditText.setError("Number of people must be at least 1");
                    return;
                }

                double tip = 0;
                //input handling passed - perform calculations
                //adjust tip based on amount of people
                if (numPeople < 3) {
                    //1 or 2 regular tip
                    tip = price * (percent / 100.0);
                } else if (numPeople > 2 && numPeople < 6) {
                    //3 to 5 people -> 1 percent increase in tip
                    percent += 1;
                    tip = price * (percent / 100.0);
                } else if (numPeople > 5 && numPeople < 11) {
                    //6 to 10 people -> 2 percent increase
                    percent += 2;
                    tip = price * (percent / 100.0);
                } else {
                    //greater than 10 people -> 4 percent increase
                    percent += 4;
                    tip = price * (percent / 100.0);
                }

                DecimalFormat df = new DecimalFormat("0.00");
                //creates new intent and passes necessary data
                Intent intent = new Intent(Calculator.this, Result.class);
                intent.putExtra("tip", "$" + df.format(tip));
                intent.putExtra("totalPrice", "$" + df.format(price+tip));
                //start next activity
                Calculator.this.startActivity(intent);
            }
        });
    }
}