package com.example.dogoout.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.UserBuilder;


public class Register3Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        radioGroup = findViewById(R.id.humanPreference);
        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidRadioGroup()) {
                    // Get the builder from the previous activity
                    Intent intent = getIntent();
                    UserBuilder userBuilder = (UserBuilder) intent.getSerializableExtra(Constants.USER_BUILDER_TAG);

                    // Add the gender to the UserBuilder
                    if (radioGroup.getCheckedRadioButtonId() == R.id.rbtnMale) {
                        userBuilder = userBuilder.withGender("MALE");
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.rbtnFemale) {
                        userBuilder = userBuilder.withGender("FEMALE");
                    } else {
                        userBuilder = userBuilder.withGender("OTHER");
                    }

                    // Add the builder to the intent and pass it to the next activity
                    Intent intentNextActivity = new Intent(getApplicationContext(), Register4Activity.class);
                    intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                    startActivity(intentNextActivity);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register3Activity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Please select gender");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
            }
        });
    }

    protected boolean isValidRadioGroup() {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            return true;
        }
        return false;
    }
}