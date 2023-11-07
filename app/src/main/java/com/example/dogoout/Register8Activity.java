package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Register8Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    AutoCompleteTextView acTxtVBreed;
    EditText txtInDogName;
    ArrayList<String> arrayListDogBreed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register8);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        txtInDogName = findViewById(R.id.txtInDogName);
        acTxtVBreed = findViewById(R.id.actxtVBreed);


        arrayListDogBreed.add("Labrador");
        arrayListDogBreed.add("Beagle");
        arrayListDogBreed.add("Australian Shepherd");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrayListDogBreed);
        acTxtVBreed.setAdapter(adapter);


        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (isValidDogName() & isValidDogBreed()) {
                    Intent intent = new Intent(getApplicationContext(), Register9Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    protected boolean isValidDogName() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        if (!txtInDogName.getText().toString().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }
        // set the error and error message
        txtInDogName.setText("");
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidDogBreed() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.cbxBreed);
        // Check if the edit text is empty
        if (!acTxtVBreed.getText().toString().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }
        // set the error and error message
        acTxtVBreed.setText("");
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }
}