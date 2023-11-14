package com.example.dogoout.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.UserBuilder;
import com.example.dogoout.domain.dog.DogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;

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

        // TODO: Adding dog breeds to the arraylist
        arrayListDogBreed.add("Labrador");
        arrayListDogBreed.add("Beagle");
        arrayListDogBreed.add("Australian Shepherd");
        arrayListDogBreed.add("Poodle");
        arrayListDogBreed.add("German Shepherd");
        arrayListDogBreed.add("Golden Retriever");
        arrayListDogBreed.add("Bulldog");
        arrayListDogBreed.add("Pug");
        arrayListDogBreed.add("Yorkshire Terrier");
        arrayListDogBreed.add("Boxer");
        arrayListDogBreed.add("Dachshund");
        arrayListDogBreed.add("Siberian Husky");
        arrayListDogBreed.add("Great Dane");
        arrayListDogBreed.add("Doberman Pinscher");
        arrayListDogBreed.add("Corgi");
        arrayListDogBreed.add("Shih Tzu");
        arrayListDogBreed.add("Rottweiler");
        arrayListDogBreed.add("French Bulldog");
        arrayListDogBreed.add("Pomeranian");
        arrayListDogBreed.add("American Staffordshire Terrier");
        arrayListDogBreed.add("Border Collie");

        //order alphabetically
        Collections.sort(arrayListDogBreed);

        arrayListDogBreed.add("Other...");



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

                    //Save prompt and answer
                    String dogName = txtInDogName.getText().toString().trim();
                    String dogBreed = acTxtVBreed.getText().toString().trim();

                    //Pass through the user builder
                    Intent intent = getIntent();
                    UserBuilder userBuilder = (UserBuilder) intent.getSerializableExtra(Constants.USER_BUILDER_TAG);

                    int numberOfDogs = intent.getIntExtra(Constants.NUMBER_OF_DOGS_TAG, 0);

                    //Add dog name and breed to the dog builder
                    DogBuilder dogBuilder = new DogBuilder();
                    dogBuilder = dogBuilder.withName(dogName);
                    dogBuilder = dogBuilder.withBreed(dogBreed);

                    //Send the dog builder and user builder to the next activity
                    Intent nextIntent = new Intent(getApplicationContext(), Register9Activity.class);
                    nextIntent.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                    nextIntent.putExtra(Constants.DOG_BUILDER_TAG, dogBuilder);
                    nextIntent.putExtra(Constants.NUMBER_OF_DOGS_TAG, numberOfDogs);
                    startActivity(nextIntent);
                }
            }
        });
    }

    protected boolean isValidDogName() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        if (!txtInDogName.getText().toString().trim().isEmpty()) {
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