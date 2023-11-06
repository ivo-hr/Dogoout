package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class Register5Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    EditText txtInDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register5);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        txtInDescription = findViewById(R.id.txtInDescription);
        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidTextInputLayout()) {
                    Intent intent = new Intent(getApplicationContext(), Register6Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    protected boolean isValidTextInputLayout() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        if (!txtInDescription.getText().toString().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setErrorEnabled(false);
            return true;
        }
        // set the error and error message
        txtInDescription.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }
}