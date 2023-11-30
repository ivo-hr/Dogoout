package com.example.dogoout.forgottenPassword;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dogoout.R;
import com.example.dogoout.registration.Register14Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgottenPassword1Activity extends AppCompatActivity {

    EditText txtInEmail;
    Button btnSendEmail;
    ImageView previousScreenBtn;
    Pattern pattern;
    Matcher matcher;

    static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password1);

        previousScreenBtn = findViewById(R.id.imgVBack);

        txtInEmail = findViewById(R.id.txtInEmail);
        btnSendEmail = findViewById(R.id.btnSendEmail);

        previousScreenBtn.setOnClickListener(view -> finish());

        btnSendEmail.setOnClickListener(view -> {
            if (isValidTextInputEmail()) {
                String email = txtInEmail.getText().toString().trim();

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                }
                            }
                        });


                Intent intentNextActivity = new Intent(getApplicationContext(), ForgottenPassword2Activity.class);
                startActivity(intentNextActivity);
            }


        });
    }


    protected boolean isValidTextInputEmail() {

        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        String email = txtInEmail.getText().toString().trim();
        if (!email.isEmpty()) {

            pattern = Pattern.compile(EMAIL_PATTERN);
            CharSequence cs = (CharSequence) txtInEmail.getText().toString();
            matcher = pattern.matcher(cs);

            if (matcher.matches() != true) {
                textInputLayout.setError("Invalid email.");
                return false;
            }

            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }

        // set the error and error message
        txtInEmail.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }
}