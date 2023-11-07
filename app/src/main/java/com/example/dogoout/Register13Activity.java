package com.example.dogoout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register13Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    EditText txtInEmail;
    EditText txtInPassword;
    EditText txtInConfirmPassword;
    Pattern pattern;
    Matcher matcher;
    static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\?!@#\\$%\\^&\\*]).{8,}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register13);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        txtInEmail = findViewById(R.id.txtInEmail);
        txtInPassword = findViewById(R.id.txtInPassword);
        txtInConfirmPassword = findViewById(R.id.txtInConfirmPassword);
        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (isValidTextInputEmail() & isValidTextInputPassword() & isValidTextInputConfirmPassword()) {

                    Intent intent = new Intent(getApplicationContext(), Register14Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    protected boolean isValidTextInputEmail() {

        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        if (!txtInEmail.getText().toString().isEmpty()) {

            pattern = Pattern.compile(EMAIL_PATTERN);
            CharSequence cs = (CharSequence) txtInEmail.getText().toString();
            matcher = pattern.matcher(cs);
            if(matcher.matches()!=true) {

                textInputLayout.setError("Invalid email.");
                textInputLayout.requestFocus();
                return false;
            }

            // is its not empty its okay
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
            return true;
        }

        // set the error and error message
        txtInEmail.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidTextInputPassword() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout1);

        String password = txtInPassword.getText().toString();

        // Check if the edit text is empty
        if (!password.isEmpty()) {

            pattern = Pattern.compile(PASSWORD_PATTERN);
            CharSequence cs = (CharSequence) password;
            matcher = pattern.matcher(cs);


            if (matcher.matches()!=true){
                textInputLayout.setError("Your password is too weak.");
                textInputLayout.requestFocus();
                return false;
            }
            // is its not empty its okay
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
            return true;
        }
        // set the error and error message
        txtInPassword.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidTextInputConfirmPassword() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout2);
        // Check if the edit text is empty
        if (!txtInConfirmPassword.getText().toString().isEmpty()) {

            if (!txtInPassword.getText().toString().equals(txtInConfirmPassword.getText().toString())) {

                textInputLayout.setError("Password and Confirm Password do not match.");
                textInputLayout.requestFocus();
                return false;
            }

            // is its not empty its okay
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
            return true;
        }
        // set the error and error message
        txtInConfirmPassword.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

}