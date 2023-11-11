package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;


import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.UserBuilder;
import com.google.android.material.textfield.TextInputLayout;

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

        nextScreenBtn = findViewById(R.id.btnNext);

        // SET ON CLICK LISTENERS
        previousScreenBtn.setOnClickListener(view -> finish());
        nextScreenBtn.setOnClickListener(view -> {

            // Get the user builder from the previous screen
            Intent intentPreviousActivity = getIntent();
            UserBuilder userBuilder = (UserBuilder) intentPreviousActivity.getSerializableExtra(Constants.USER_BUILDER_TAG);

            Intent intentNextActivity;
            int numberOfDogs = numPNumberOfDogs.getValue();
            if (numberOfDogs == 0) {
                // If the user has no dogs, go to the user preferences screen
                intentNextActivity = new Intent(getApplicationContext(), Register12Activity.class);
                intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
            } else {
                // If the user has dogs, go to the dog data screen
                intentNextActivity = new Intent(getApplicationContext(), Register8Activity.class);
                intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                intentNextActivity.putExtra(Constants.NUMBER_OF_DOGS_TAG, numberOfDogs);
            }

            startActivity(intentNextActivity);
        });
    }
}