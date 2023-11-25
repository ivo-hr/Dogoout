package com.example.dogoout.forgottenPassword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogoout.R;
import com.example.dogoout.login.LoginActivity;

public class ForgottenPassword2Activity extends AppCompatActivity {

    // DECLARATION OF COMPONENTS
    Button btnOkay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password2);

        // INICIALIZATION OF COMPONENTS
        btnOkay = findViewById(R.id.btnOkay);

        // EVENT LISTENERS
        btnOkay.setOnClickListener(v -> {
            // Open login activity and clear activity stack
            Intent intent = new Intent(ForgottenPassword2Activity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}