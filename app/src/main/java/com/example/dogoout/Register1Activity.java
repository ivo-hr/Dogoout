package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class Register1Activity extends AppCompatActivity {

    ImageView previousScreenBtn;
    Button nextScreenBtn;
    EditText txtInFirstName;
    EditText txtInLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        txtInFirstName = findViewById(R.id.txtInFirstName);
        txtInLastName = findViewById(R.id.txtInLastName);
        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidTextInputLayout() & isValidTextInputLayout1()) {
                    Intent intent = new Intent(getApplicationContext(), Register2Activity.class);
                    intent.putExtra("firstName", txtInFirstName.getText().toString());
                    intent.putExtra("lastName", txtInLastName.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    protected boolean isValidTextInputLayout() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        if (!txtInFirstName.getText().toString().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setErrorEnabled(false);
            return true;
        }
        // set the error and error message
        txtInFirstName.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidTextInputLayout1() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout1);
        // Check if the edit text is empty
        if (!txtInLastName.getText().toString().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setErrorEnabled(false);
            return true;
        }
        // set the error and error message
        txtInLastName.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }
}