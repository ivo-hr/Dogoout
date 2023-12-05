package com.example.dogoout.registration;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.dogoout.login.LoginActivity;
import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Register14Activity extends AppCompatActivity {


    //Declare complete reg button
    Button btnCompleteRegistration;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser currentUser;
    User userCreate;
    Map<String, Object> user;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();


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

            userCreate = userBuilder.build();

            Log.d("User", userCreate.toString());

            currentUser = firebaseAuth.getCurrentUser();

            assert currentUser != null;

            currentUser.reload();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isEmailValid(currentUser)) {

                userID = currentUser.getUid();

                user = new HashMap<>();
                user.put("firstname", userCreate.getFirstname());
                user.put("surname", userCreate.getSurname());
                user.put("email", userCreate.getEmail());
                user.put("country", userCreate.getCountry());
                user.put("birthDate", userCreate.getBirthDate().toString());
                user.put("gender", userCreate.getGender());
                user.put("description", userCreate.getDescription());
                user.put("prompt", userCreate.getPrompt());
                user.put("promptAnswer", userCreate.getPromptAnswer());
                user.put("userPreference", userCreate.getPreference());

                uploadPhotoAndGetID(userCreate.getPhotosUser(), new UploadCallback() {
                    @Override
                    public void onUploadComplete(ArrayList<String> arrayListIDPhotos) {
                        user.put("photos", arrayListIDPhotos);
                        Log.d(TAG, "Uploads complete: " + arrayListIDPhotos.toString());

                        ArrayList<Dog> dogs = userCreate.getDogs();
                        if (dogs.size() == 0) {
                            Log.d(TAG, "onSuccess: user Profile is created for " + userID);

                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);

                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            for (Dog dog : dogs) {
                                uploadPhotoAndGetID(dog.getPhotosDog(), new UploadCallback() {
                                    @Override
                                    public void onUploadComplete(ArrayList<String> arrayListIDPhotosDogs) {
                                        dog.setPhotosDogString(arrayListIDPhotosDogs);
                                        Log.d(TAG, "Uploads complete: " + arrayListIDPhotosDogs.toString());

                                        user.put("dogs", userCreate.getDogs());

                                        DocumentReference documentReference = fStore.collection("users").document(userID);

                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG, "onSuccess: user Profile is created for " + userID);

                                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                startActivity(intent);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d(TAG, "onFailure: " + e.toString());
                                            }
                                        });

                                    }

                                    @Override
                                    public void onUploadFailure(Exception exception) {
                                        // Handle upload failure
                                        Log.d(TAG, "Upload failed: " + exception.toString());
                                    }
                                });

                            }
                        }

                    }

                    @Override
                    public void onUploadFailure(Exception exception) {
                        Log.d(TAG, "Upload failed: " + exception.toString());
                    }
                });

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

    public interface UploadCallback {

        void onUploadComplete(ArrayList<String> arrayListIDPhotos);

        void onUploadFailure(Exception exception);
    }


    protected void uploadPhotoAndGetID(ArrayList<URI> arrayListPhotos, UploadCallback callback) {

        ArrayList<String> arrayListIDPhotos = new ArrayList<>();

        AtomicInteger count = new AtomicInteger(arrayListPhotos.size());

        for (URI uriPhotos : arrayListPhotos) {
            Uri file = Uri.fromFile(new File(uriPhotos));
            StorageReference imageRef = storageRef.child("images/" + file.getLastPathSegment());

            UploadTask uploadTask = imageRef.putFile(file);

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    arrayListIDPhotos.add(uri.toString());
                    count.decrementAndGet();

                    if (count.get() == 0) {
                        callback.onUploadComplete(arrayListIDPhotos);
                    }
                });
            }).addOnFailureListener(exception -> {
                callback.onUploadFailure(exception);
            });
        }
    }


}