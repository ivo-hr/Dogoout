package com.example.dogoout.registration;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.UserBuilder;
import com.example.dogoout.validation.Validator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register13Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    EditText txtInEmail;
    EditText txtInPassword;
    EditText txtInConfirmPassword;
    Pattern pattern;
    Matcher matcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register13);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        txtInEmail = findViewById(R.id.txtInEmail);
        txtInPassword = findViewById(R.id.txtInPassword);
        txtInConfirmPassword = findViewById(R.id.txtInConfirmPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        previousScreenBtn.setOnClickListener(view -> finish());

        nextScreenBtn.setOnClickListener(view -> {
            // Check if the email and password are valid
            boolean isValidEmail = Validator.isValidEmail(txtInEmail.getText().toString());
            boolean isValidPassword = Validator.isValidPassword(txtInPassword.getText().toString());

            boolean passAndConfirmPassMatch = txtInPassword.getText().toString().equals(txtInConfirmPassword.getText().toString());
            if (isValidEmail && isValidPassword && passAndConfirmPassMatch) {
                // Get the email and password
                String email = txtInEmail.getText().toString().trim();
                String password = txtInPassword.getText().toString().trim();

                // Get the intent from the previous activity
                Intent intent = getIntent();
                // Get the user builder from the intent and add the email to it
                UserBuilder userBuilder = (UserBuilder) intent.getSerializableExtra(Constants.USER_BUILDER_TAG);
                userBuilder = userBuilder.withEmail(email);

                Intent intentNextActivity = new Intent(getApplicationContext(), Register14Activity.class);
                intentNextActivity.putExtra(Constants.EMAIL_TAG, email);
                intentNextActivity.putExtra(Constants.PASSWORD_TAG, password);
                intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // User creation success
                                    //Log.d("FirebaseAuth", "createUserWithEmail:success");
                                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                    assert firebaseUser != null;
                                    firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                        Toast.makeText(Register13Activity.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                        }
                                        }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                        }
                                    });

                                    startActivity(intentNextActivity);
                                } else {
                                    Toast.makeText(Register13Activity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }

            else {
                if (!isValidEmail) {
                    // Get the text input layout
                    TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
                    // set the error and error message
                    textInputLayout.setError("Please choose a valid email.");
                }
                else{
                    TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
                    textInputLayout.setError(null);
                }
                if (!isValidPassword) {
                    // Get the text input layout
                    TextInputLayout textInputLayout = findViewById(R.id.textInputLayout1);
                    // set the error and error message
                    txtInPassword.setText("");
                    textInputLayout.setError("Please choose a valid password. (at least 8 characters long, has at least one uppercase letter, one lowercase letter and one number)");
                }
                else{
                    TextInputLayout textInputLayout = findViewById(R.id.textInputLayout1);
                    textInputLayout.setError(null);
                }
                if (!passAndConfirmPassMatch) {
                    // Get the text input layout
                    TextInputLayout textInputLayout = findViewById(R.id.textInputLayout2);
                    // set the error and error message
                    txtInConfirmPassword.setText("");
                    textInputLayout.setError("Password and Confirm Password do not match.");
                }
                else{
                    TextInputLayout textInputLayout = findViewById(R.id.textInputLayout2);
                    textInputLayout.setError(null);
                }
                return;
            }
        });
    }

    /*protected boolean isValidTextInputEmail() {

        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        // Check if the edit text is empty
        String email = txtInEmail.getText().toString().trim();
        if (!email.isEmpty()) {

            pattern = Pattern.compile(EMAIL_PATTERN);
            CharSequence cs = (CharSequence) txtInEmail.getText().toString();
            matcher = pattern.matcher(cs);

            if (matcher.matches() != true) {
                textInputLayout.setError("Invalid email.");
                return false;
            }

            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }

        // set the error and error message
        txtInEmail.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidTextInputPassword() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout1);

        // Check if the edit text is empty
        String password = txtInPassword.getText().toString().trim();
        if (!password.isEmpty()) {

            pattern = Pattern.compile(PASSWORD_PATTERN);
            CharSequence cs = (CharSequence) password;
            matcher = pattern.matcher(cs);


            if (matcher.matches() != true) {
                textInputLayout.setError("Your password is too weak.");
                return false;
            }

            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }
        // set the error and error message
        txtInPassword.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidTextInputConfirmPassword() {
        // Get the text input layout
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout2);
        // Check if the edit text is empty
        String confirmPassword = txtInConfirmPassword.getText().toString().trim();
        if (!confirmPassword.isEmpty()) {

            if (!txtInPassword.getText().toString().equals(txtInConfirmPassword.getText().toString())) {
                textInputLayout.setError("Password and Confirm Password do not match.");
                return false;
            }

            // is its not empty its okay
            textInputLayout.setError(null);
            return true;
        }
        // set the error and error message
        txtInConfirmPassword.getText().clear();
        textInputLayout.setError("Please Fill Out this Field.");
        return false;
    }*/

}