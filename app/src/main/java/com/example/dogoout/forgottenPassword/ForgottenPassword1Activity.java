package com.example.dogoout.forgottenPassword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogoout.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgottenPassword1Activity extends AppCompatActivity {

    // DECLARATION OF COMPONENTS
    ImageView imgVBack;
    EditText txtInEmail;
    Button btnSendEmail;

    static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password1);

        // INICIALIZATION OF COMPONENTS
        imgVBack = findViewById(R.id.imgVBack);
        txtInEmail = findViewById(R.id.txtInEmail);
        btnSendEmail = findViewById(R.id.btnSendEmail);

        // EVENT LISTENERS
        imgVBack.setOnClickListener(view -> finish());
        btnSendEmail.setOnClickListener(view -> {
            // Check if the email is valid
            if (!isValidTextInputEmail())
                return;

            // TODO: send email to user with link to reset password

            // Open ForgottenPassword2Activity
            Intent intent = new Intent(this, ForgottenPassword2Activity.class);
            startActivity(intent);
        });
    }

    protected boolean isValidTextInputEmail() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        String email = txtInEmail.getText().toString().trim();
        if (!email.isEmpty()) {

            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            CharSequence cs = (CharSequence) txtInEmail.getText().toString();
            Matcher matcher = pattern.matcher(cs);

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