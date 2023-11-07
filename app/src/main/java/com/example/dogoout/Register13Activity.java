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

public class Register13Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    EditText txtInEmail;
    EditText txtInPassword;
    EditText txtInConfirmPassword;

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
                if (isValidTextInputEmail() && isValidTextInputPassword() && isValidTextInputConfirmPassword() && arePasswordTheSame()) {

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
            // is its not empty its okay
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
        // Check if the edit text is empty
        if (!txtInPassword.getText().toString().isEmpty()) {
            // is its not empty its okay
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
            // is its not empty its okay
            textInputLayout.setErrorEnabled(false);
            return true;
        }
        // set the error and error message
        txtInConfirmPassword.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean arePasswordTheSame() {

        if (!txtInPassword.getText().toString().equals(txtInConfirmPassword.getText().toString())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Register13Activity.this);
            builder.setTitle("Error");
            builder.setMessage("Password and Confirm Password do not match. Please make sure both fields have the same value.");
            builder.setPositiveButton("OK", null);
            builder.show();
            return false;
        }
        return true;

    }

}