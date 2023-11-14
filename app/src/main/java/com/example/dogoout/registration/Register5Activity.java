package com.example.dogoout.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.UserBuilder;
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
                // check if user filled out the description field
                if (isValidTextInputLayout()) {

                    // get the description from the edit text
                    String description = txtInDescription.getText().toString().trim();

                    // get the user builder from the previous activity
                    Intent intent = getIntent();
                    UserBuilder userBuilder = (UserBuilder) intent.getSerializableExtra(Constants.USER_BUILDER_TAG);

                    // add the description to the user builder
                    userBuilder = userBuilder.withDescription(description);

                    // create a new intent to go to the next activity
                    Intent intentNextActivity = new Intent(Register5Activity.this, Register6Activity.class);
                    // add the user builder to the intent
                    intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                    // go to the next activity
                    startActivity(intentNextActivity);
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