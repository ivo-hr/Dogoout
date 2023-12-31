package com.example.dogoout.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.preference.Preference;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;
import com.example.dogoout.domain.user.UserImpl;
import com.example.dogoout.forgottenPassword.ForgottenPassword1Activity;
import com.example.dogoout.mainscreen.MainActivity;
import com.example.dogoout.registration.Register1Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    FirebaseUser currentUser;
    FirebaseFirestore fStore;

    TextView forgotPassword;

    @SuppressLint("WrongViewCast")
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
        forgotPassword = findViewById(R.id.txtVForgotPassword);

        //Set on click listener for register textview
        registerTextView.setOnClickListener(v -> {
            //Start register activity
            Intent nextIntent = new Intent(LoginActivity.this, Register1Activity.class);
            startActivity(nextIntent);
        });

        forgotPassword.setOnClickListener(v -> {
            Intent nextIntent = new Intent(LoginActivity.this, ForgottenPassword1Activity.class);
            startActivity(nextIntent);
        });

        //emailEditText.setText("faience.sifter0v@icloud.com");
        emailEditText.setText("rinses_mattes.0d@icloud.com");
        passEditText.setText("Dublin123!");

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
                firebaseAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passEditText.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // TODO: get the user that is logged in (implement method getUserFromDatabase)
                            getUserFromDatabase();
                        } else {
                            builder.show();
                            Log.w("LoginActivity", "signInWithEmail:failure", task.getException());

                        }
                    }
                });
            }
        });
    }


    public User getUserFromDatabase() {
        // TODO: get the user that is logged in
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Fetch user data from Firebase Data Store
        fStore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fStore.collection("users").document(currentUser.getUid());

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Get user data from the document
                        String birthDate = document.getString("birthDate");
                        String country = document.getString("country");
                        String description = document.getString("description");
                        String email = document.getString("email");
                        String firstname = document.getString("firstname");
                        String gender = document.getString("gender");

                        //Save user photos with URI
                        ArrayList<String> uriStrings = (ArrayList<String>) document.get("photos");
                        ArrayList<URI> photos = new ArrayList<>();
                        for (String uriString : uriStrings) {
                            try {
                                URI uri = new URI(uriString);
                                photos.add(uri);
                            } catch (URISyntaxException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        String prompt = document.getString("prompt");
                        String promptAnswer = document.getString("promptAnswer");
                        String surname = document.getString("surname");

                        //Save user preference class
                        HashMap<String, Object> userPreference = (HashMap<String, Object>) document.get("userPreference");
                        Preference preference = new Preference();
                        preference.setSexPreference((String) userPreference.get("sexPreference"));
                        preference.setDogBreedPreference((String) userPreference.get("dogBreedPreference"));
                        preference.setDogOwnerPreference((String) userPreference.get("dogOwnerPreference"));
                        preference.setMinAge(Math.toIntExact((long) userPreference.get("minAge")));
                        preference.setMaxAge(Math.toIntExact((long) userPreference.get("maxAge")));

                        UserBuilder userBuilder = new UserBuilder()
                                .withBirthDate(LocalDate.parse(birthDate))
                                .withCountry(country)
                                .withDescription(description)
                                .withEmail(email)
                                .withFirstname(firstname)
                                .withGender(gender)
                                .withPhotosUser(photos)
                                .withSurname(surname)
                                .withPrompt(prompt)
                                .withPromptAnswer(promptAnswer)
                                .withPreference(preference);

                        //Save dogs with dog class
                        ArrayList<HashMap> dogs = (ArrayList<HashMap>) document.get("dogs");
                        if (dogs != null) {
                            for (HashMap dog : dogs) {
                                DogBuilder dogBuilder = new DogBuilder()
                                        .withPromptAnswer((String) dog.get("promptAnswer"))
                                        .withCharacteristics((ArrayList<String>) dog.get("characteristics"))
                                        .withName((String) dog.get("name"))
                                        .withPrompt((String) dog.get("prompt"))
                                        .withBreed((String) dog.get("breed"));

                                ArrayList<String> uriDogStrings = (ArrayList<String>) dog.get("photosDog");
                                ArrayList<URI> photosDog = new ArrayList<>();
                                for (String uriString : uriDogStrings) {
                                    try {
                                        URI uri = new URI(uriString);
                                        photosDog.add(uri);
                                    } catch (URISyntaxException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                dogBuilder = dogBuilder.withPhotosDog(photosDog);

                                userBuilder.withDog(dogBuilder.build());
                            }
                        }

                        UserImpl user = userBuilder.build();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(Constants.USER_TAG, user);
                        startActivity(intent);
                        finish();
                    } else {
                        // Handle user not found
                    }
                } else {
                    // Handle error
                }
            }
        });

        return null;
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