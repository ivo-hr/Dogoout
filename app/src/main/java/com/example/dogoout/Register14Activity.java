package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;

public class Register14Activity extends AppCompatActivity {


    //Declare complete reg button
    Button btnCompleteRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register14);

        //Initialize complete reg button
        btnCompleteRegistration = findViewById(R.id.btnCompleteRegistration);

        //Set on click listener for complete reg button
        btnCompleteRegistration.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        //Get the user builder from the intent
        UserBuilder userBuilder = (UserBuilder) getIntent().getSerializableExtra(Constants.USER_BUILDER_TAG);
        String email = getIntent().getStringExtra(Constants.EMAIL_TAG);
        String password = getIntent().getStringExtra(Constants.PASSWORD_TAG);

        User user = userBuilder.build();

        Log.d("User", user.toString());
    }
}