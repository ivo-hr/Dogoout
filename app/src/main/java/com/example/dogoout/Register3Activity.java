package com.example.dogoout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputLayout;


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
        radioGroup = findViewById(R.id.radioGroup);
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
                    Intent intent = new Intent(getApplicationContext(), Register4Activity.class);
                    startActivity(intent);
                }
                else {
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