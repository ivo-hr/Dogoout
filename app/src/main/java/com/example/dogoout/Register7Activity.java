package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class Register7Activity extends AppCompatActivity {

    // DECLARE COMPONENTS
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    NumberPicker numPNumberOfDogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register7);

        // INITIALIZE COMPONENTS
        numPNumberOfDogs = findViewById(R.id.numPNumberOfDogs);
        numPNumberOfDogs.setMaxValue(3);
        numPNumberOfDogs.setMinValue(0);

        previousScreenBtn = findViewById(R.id.imgVBack);

        nextScreenBtn = findViewById(R.id.btnNext);

        // SET ON CLICK LISTENERS
        previousScreenBtn.setOnClickListener(view -> finish());
        nextScreenBtn.setOnClickListener(view -> {
            Intent intent;

            if (numPNumberOfDogs.getValue() == 0) {
                // If the user has no dogs, go to the email verification screen
                intent = new Intent(getApplicationContext(), Register14Activity.class);
            } else {
                // If the user has dogs, go to the dog data screen
                // TODO: send the number of dogs to the next screen and current data
                intent = new Intent(getApplicationContext(), Register8Activity.class);
            }

            startActivity(intent);
        });
    }
}