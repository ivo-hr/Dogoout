package com.example.dogoout.forgottenPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.dogoout.R;
import com.example.dogoout.login.LoginActivity;

public class ForgottenPassword2Activity extends AppCompatActivity {

    Button buttonGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password2);

        buttonGoLogin = findViewById(R.id.btnOkay);

        buttonGoLogin.setOnClickListener(view -> {

            Intent intentNextActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentNextActivity);

        });

    }
}