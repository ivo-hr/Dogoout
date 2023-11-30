package com.example.dogoout.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.UserBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.example.dogoout.validation.Validator;

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
                //Validate prompt and answer
                boolean validPrompt = Validator.isValidTextLength(promptTxt.getText().toString());
                boolean validAnswer = Validator.isValidTextLength(answerTxtPrompt.getText().toString(), 5,150);
                if (validPrompt && validAnswer) {

                    //Save the prompt
                    String prompt = promptTxt.getText().toString().trim();
                    //Save the answer to the prompt
                    String answer = answerTxtPrompt.getText().toString().trim();

                    //Add prompt and answer to the user builder
                    Intent intent = getIntent();
                    UserBuilder userBuilder = (UserBuilder) intent.getSerializableExtra(Constants.USER_BUILDER_TAG);
                    userBuilder = userBuilder.withPrompt(prompt);
                    userBuilder = userBuilder.withPromptAnswer(answer);
                    //Go to the next activity with the user builder
                    Intent nextIntent = new Intent(getApplicationContext(), Register7Activity.class);
                    nextIntent.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                    startActivity(nextIntent);
                }
                else {
                    if (!validPrompt) {
                        // Get the text input layout
                        TextInputLayout textInputLayout = findViewById(R.id.cbxLayout);
                        // set the error and error message
                        promptTxt.setText("");
                        textInputLayout.setError("Please select a prompt.");
                    }
                    else {
                        TextInputLayout textInputLayout = findViewById(R.id.cbxLayout);
                        textInputLayout.setError(null);
                    }
                    if (!validAnswer) {
                        // Get the text input layout
                        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
                        // set the error and error message
                        textInputLayout.setError("Please fill out this field with 5-150 characters.");
                    }
                    else {
                        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
                        textInputLayout.setError(null);
                    }
                    return;
                }
            }
        });
    }

    /*protected boolean isValidAnswerTxtPrompt() {
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
    }*/


}