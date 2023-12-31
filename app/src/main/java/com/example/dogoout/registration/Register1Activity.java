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

import com.example.dogoout.validation.Validator;

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
                //Validate name and surname
                boolean isValidName = Validator.isValidName(txtInFirstName.getText().toString());
                boolean isValidSurname = Validator.isValidName(txtInLastName.getText().toString());
                if (isValidSurname && isValidName) {
                    // Get the text from the edit text
                    String firstName = txtInFirstName.getText().toString().trim();
                    String lastName = txtInLastName.getText().toString().trim();

                    // Create Builder with the data (firstName, lastName)
                    UserBuilder userBuilder
                            = new UserBuilder()
                            .withFirstname(firstName)
                            .withSurname(lastName);

                    // Add the builder to the intent and pass it to the next activity
                    Intent intent = new Intent(getApplicationContext(), Register2Activity.class);
                    intent.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                    startActivity(intent);
                }
                else {
                    // Show error message
                    if (!Validator.isValidName(txtInFirstName.getText().toString())) {
                        txtInFirstName.getText().clear();
                        txtInFirstName.setError("Please put your Name.");
                    }
                    else txtInFirstName.setError(null);
                    if (!Validator.isValidName(txtInLastName.getText().toString())) {
                        txtInLastName.getText().clear();
                        txtInLastName.setError("Please put your Surname.");
                    }
                    else txtInLastName.setError(null);
                }
            }
        });
    }

    /*protected boolean isValidTextInputLayout() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        if (!txtInFirstName.getText().toString().trim().isEmpty()) {
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
        if (!txtInLastName.getText().toString().trim().isEmpty()) {
            // is its not empty its okay
            textInputLayout.setErrorEnabled(false);
            return true;
        }
        // set the error and error message
        txtInLastName.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }*/
}