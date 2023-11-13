package com.example.dogoout;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Register14Activity extends AppCompatActivity {


    //Declare complete reg button
    Button btnCompleteRegistration;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    String userID;

    FirebaseUser currentUser;

    User userCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register14);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //Initialize complete reg button
        btnCompleteRegistration = findViewById(R.id.btnCompleteRegistration);

        //Set on click listener for complete reg button
        btnCompleteRegistration.setOnClickListener(view -> {

            //Get the user builder from the intent
            UserBuilder userBuilder = (UserBuilder) getIntent().getSerializableExtra(Constants.USER_BUILDER_TAG);
            String email = getIntent().getStringExtra(Constants.EMAIL_TAG);
            String password = getIntent().getStringExtra(Constants.PASSWORD_TAG);

            userCreate = userBuilder.build();

            Log.d("User", userCreate.toString());

            currentUser = firebaseAuth.getCurrentUser();

            assert currentUser != null;

            currentUser.reload();
            if (isEmailValid(currentUser)) {


                userID = currentUser.getUid();

                Map<String, Object> user = new HashMap<>();
                user.put("firstname", userCreate.getFirstname());
                user.put("surname", userCreate.getSurname());
                user.put("email", userCreate.getEmail());
                user.put("country", userCreate.getCountry());
                user.put("birthDate", userCreate.getBirthDate().toString());
                user.put("gender", userCreate.getGender());
                user.put("description", userCreate.getDescription());
                user.put("prompt", userCreate.getPrompt());
                user.put("photoUser", new ArrayList<>());
                user.put("promptAnswer", userCreate.getPromptAnswer());
                user.put("userPreference", userCreate.getPreference());
                user.put("dogs", userCreate.getDogs());

                DocumentReference documentReference = fStore.collection("users").document(userID);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }

        });


    }

    protected boolean isEmailValid(FirebaseUser currentUser) {
        Log.d(TAG, "isEmailVerified: " + currentUser.isEmailVerified());
        try {
            if (currentUser.isEmailVerified()) {
                return true;
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Your email is not verified");
                builder.setPositiveButton("OK", null);
                builder.show();
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error checking email verification status: " + e.getMessage());
            return false;
        }
    }


}