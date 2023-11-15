package com.example.dogoout.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dogoout.mainscreen.MainActivity;
import com.example.dogoout.R;
import com.example.dogoout.registration.Register1Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //Declare text input layouts for email and password
    TextInputLayout emailInput;
    EditText emailEditText;
    TextInputLayout passInput;
    EditText passEditText;
    //Initialize login button and register textview
    Button loginButton;
    TextView registerTextView;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Initialize text input layouts for email and password
        emailInput = findViewById(R.id.textInputLayout);
        passInput = findViewById(R.id.textInputLayout1);
        emailEditText = findViewById(R.id.txtInEmail);
        passEditText = findViewById(R.id.txtInPassword);
        //Initialize login button and register textview
        loginButton = findViewById(R.id.btnLogin);
        registerTextView = findViewById(R.id.txtVRegister);

        //Set on click listener for register textview
        registerTextView.setOnClickListener(v -> {
            //Start register activity
            Intent nextIntent = new Intent(LoginActivity.this, Register1Activity.class);
            startActivity(nextIntent);
        });

        emailEditText.setText("david.cadez89@gmail.com");
        passEditText.setText("Dublin123?");

        //Set on click listener for login button
        loginButton.setOnClickListener(v -> {
            //Check if email and password are valid
            if (isValidTextInputEmail() && isValidTextInputPassword()) {
                firebaseAuth = FirebaseAuth.getInstance();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Your email or password is incorrect");
                builder.setPositiveButton("OK", null);
                //Start main activity
                firebaseAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passEditText.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else {
                                    builder.show();
                                    Log.w("LoginActivity", "signInWithEmail:failure", task.getException());

                                }
                            }
                        });
            }
        });


    }

    protected boolean isValidTextInputEmail() {
        // Check if the edit text is empty
        String email = emailEditText.getText().toString().trim();
        if (!email.isEmpty()) {
            // is its not empty its okay
            emailInput.setError(null);
            return true;
        }

        // set the error and error message
        emailEditText.getText().clear();
        emailInput.setError("Please Fill Out this Field.");
        return false;
    }

    protected boolean isValidTextInputPassword() {
        // Check if the edit text is empty
        String password = passEditText.getText().toString().trim();
        if (!password.isEmpty()) {
            // is its not empty its okay
            passInput.setError(null);
            return true;
        }
        // set the error and error message
        passEditText.getText().clear();
        passInput.setError("Please Fill Out this Field.");
        return false;
    }
}