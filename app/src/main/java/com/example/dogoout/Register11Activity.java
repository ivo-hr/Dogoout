package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.user.UserBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Register11Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    AutoCompleteTextView promptTxtDog;
    TextView textPromptDogAnswer;

    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register11);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        textPromptDogAnswer = findViewById(R.id.txtInDogPromptAnswer);
        promptTxtDog = findViewById(R.id.actxtDogPrompt);

        arrayList.add("Your dog unearths a mysterious object buried in the backyard. What is it, and how does your dog react?");
        arrayList.add("Describe a typical day in the life of your dog as they explore the neighborhood's sights, sounds, and scents.");
        arrayList.add("If your dog could communicate with words for a day, what surprising things would they say during your conversations?");
        arrayList.add("At the local pet talent show, your dog showcases an unexpected skill. What talent do they reveal to the audience?");
        arrayList.add("On a rainy afternoon, your dog decides to host a creative indoor 'paw-ty.' What imaginative activities do they plan?");
        arrayList.add("Picture your dog as the benevolent leader of a doggy community. What unique rule does your dog implement for the greater good?");
        arrayList.add("While digging in the backyard, your dog stumbles upon a magical portal. Describe the fantastical world they discover on the other side.");
        arrayList.add("Your dog transforms into a detective, solving a mysterious neighborhood case. What clever clues does your dog follow?");
        arrayList.add("If your dog possessed a superpower, what extraordinary ability would they have, and how would they use it heroically?");
        arrayList.add("In a parallel universe where dogs teach humans, your dog is in charge of a human training school. What essential lessons do they impart at the 'Canine Academy'?");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrayList);
        promptTxtDog.setAdapter(adapter);

        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (isValidAnswerTxtPromptDog() & isValidTxtPromptDog()) {

                    //Save the prompt and the answer
                    String prompt = promptTxtDog.getText().toString().trim();
                    String answer = textPromptDogAnswer.getText().toString().trim();

                    //Grab the user builder from the previous screen
                    Intent intentPreviousActivity = getIntent();
                    UserBuilder userBuilder = (UserBuilder) intentPreviousActivity.getSerializableExtra(Constants.USER_BUILDER_TAG);
                    //Add the prompt and the answer to the dog builder
                    DogBuilder dogBuilder = (DogBuilder) intentPreviousActivity.getSerializableExtra(Constants.DOG_BUILDER_TAG);
                    dogBuilder = dogBuilder.withPromptId(prompt);
                    dogBuilder = dogBuilder.withPromptAnswer(answer);
                    //Grab the dog number from the previous screen
                    int numberOfDogs = intentPreviousActivity.getIntExtra(Constants.NUMBER_OF_DOGS_TAG, 0);

                    //Add the dog builder, user builder and the number of dogs to the next screen
                    Intent nextIntent = new Intent(getApplicationContext(), Register12Activity.class);
                    nextIntent.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                    nextIntent.putExtra(Constants.DOG_BUILDER_TAG, dogBuilder);
                    nextIntent.putExtra(Constants.NUMBER_OF_DOGS_TAG, numberOfDogs);
                    startActivity(nextIntent);
                }
            }
        });
    }

    protected boolean isValidAnswerTxtPromptDog() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        if (!textPromptDogAnswer.getText().toString().trim().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }
        // set the error and error message
        textPromptDogAnswer.setText("");
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidTxtPromptDog() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.cbxLayout);
        // Check if the edit text is empty
        if (!promptTxtDog.getText().toString().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }
        // set the error and error message
        promptTxtDog.setText("");
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }
}