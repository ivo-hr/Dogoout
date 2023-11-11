package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Register6Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    AutoCompleteTextView promptTxt;
    TextView answerTxtPrompt;

    ArrayList<String> prompts = new ArrayList<>();

    //AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register6);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        promptTxt = findViewById(R.id.actxtPrompt);
        answerTxtPrompt = findViewById(R.id.txtInPromptAnswer);

        // Adding prompts to the arraylist
        prompts.add("If your dog wrote your bio, what would they say?");
        prompts.add("Best dog-friendly date idea you can think of?");
        prompts.add("Describe the perfect day with your dog.");
        prompts.add("Share a heartwarming or funny dog story.");
        prompts.add("Sum up your bond with your dog in 3 words.");
        prompts.add("If your dog picked your date, what traits would they choose?");
        prompts.add("Your go-to trick to make your dog's tail wag?");
        prompts.add("Share a time your dog made you laugh out loud.");
        prompts.add("How did you and your dog become a duo?");
        prompts.add("Your dog's favorite treat and do they get spoiled?");
        prompts.add("If your dog chose a song to describe you, what would it be?");
        prompts.add("Most adventurous thing you've done with your dog?");
        prompts.add("Photo of your dog's favorite spot and why it's special.");
        prompts.add("Key lesson learned from being a dog parent?");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, prompts);
        promptTxt.setAdapter(adapter);


        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidAnswerTxtPrompt() & isValidTxtPrompt()) {
                    Intent intent = new Intent(getApplicationContext(), Register7Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    protected boolean isValidAnswerTxtPrompt() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        if (!answerTxtPrompt.getText().toString().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }
        // set the error and error message
        answerTxtPrompt.setText("");
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidTxtPrompt() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.cbxLayout);
        // Check if the edit text is empty
        if (!promptTxt.getText().toString().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }
        // set the error and error message
        promptTxt.setText("");
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }


}