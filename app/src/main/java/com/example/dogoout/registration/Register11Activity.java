package com.example.dogoout.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.user.UserBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Register11Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    AutoCompleteTextView promptTxtDog;
    TextView textPromptDogAnswer;

    ArrayList<String> dogPrompts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register11);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        textPromptDogAnswer = findViewById(R.id.txtInDogPromptAnswer);
        promptTxtDog = findViewById(R.id.actxtDogPrompt);

        dogPrompts.add("Woof! Describe my paw-sonality in three words. How would you sum me up?");
        dogPrompts.add("Bark out loud! What's my absolute favorite game or activity?");
        dogPrompts.add("Sniff out the fun! Share a quirky habit of mine that always gets a laugh.");
        dogPrompts.add("What breed do you think I am, and what made you pawsitively choose me?");
        dogPrompts.add("Let's take a walk down memory lane! How was our first day together?");
        dogPrompts.add("Embark on an adventure with me! What's the wildest thing we've done?");
        dogPrompts.add("If I had a dating profile, what headline do you think would catch tails?");
        dogPrompts.add("Paws and reflect: what's my go-to move for grabbing your attention?");
        dogPrompts.add("Unleash the love! Share a heartwarming tail about us. I know there's plenty!");
        dogPrompts.add("If I could talk, what do you imagine I'd bark about you to the world?");
        dogPrompts.add("Treat time! What's my absolute favorite, and how do I react when I get it?");
        dogPrompts.add("Fetch the details! Describe my dream day out with you. Where would we go?");
        dogPrompts.add("If I had a theme song, what tune do you think would capture my doggy spirit?");
        dogPrompts.add("Snap a pic of my nap! What's my cutest sleeping position according to you?");
        dogPrompts.add("Tail wagging alert! What's the one thing I do that never fails to make you smile?");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, dogPrompts);
        promptTxtDog.setAdapter(adapter);

        previousScreenBtn.setOnClickListener(view -> finish());
        nextScreenBtn.setOnClickListener(view -> {
            if (isValidAnswerTxtPromptDog() & isValidTxtPromptDog()) {

                //Save the prompt and the answer
                String prompt = promptTxtDog.getText().toString().trim();
                String answer = textPromptDogAnswer.getText().toString().trim();

                //Grab the user builder from the previous screen
                Intent intentPreviousActivity = getIntent();
                UserBuilder userBuilder = (UserBuilder) intentPreviousActivity.getSerializableExtra(Constants.USER_BUILDER_TAG);
                //Add the prompt and the answer to the dog builder
                DogBuilder dogBuilder = (DogBuilder) intentPreviousActivity.getSerializableExtra(Constants.DOG_BUILDER_TAG);
                dogBuilder = dogBuilder.withPrompt(prompt);
                dogBuilder = dogBuilder.withPromptAnswer(answer);
                //Grab the dog number from the previous screen
                int numberOfDogs = intentPreviousActivity.getIntExtra(Constants.NUMBER_OF_DOGS_TAG, 0);


                // Build the dog, add it to the userBuilder and reduce the number of dogs
                Dog dog = dogBuilder.build();
                userBuilder = userBuilder.withDog(dog);
                numberOfDogs--;

                // check if there are more dogs to register
                Intent intentNextActivity;
                if (numberOfDogs == 0) {
                    // if there are no more dogs to register, go to the user preferences activity
                    intentNextActivity = new Intent(getApplicationContext(), Register12Activity.class);
                    intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                    startActivity(intentNextActivity);
                } else {
                    // if there are more dogs to register, go to the dog registration activity
                    intentNextActivity = new Intent(getApplicationContext(), Register8Activity.class);
                    intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                    intentNextActivity.putExtra(Constants.NUMBER_OF_DOGS_TAG, numberOfDogs);
                    startActivity(intentNextActivity);
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