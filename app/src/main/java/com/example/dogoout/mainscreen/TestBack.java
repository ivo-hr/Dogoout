package com.example.dogoout.mainscreen;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class TestBack {

    private FirebaseFirestore firestoreDB;
    private FirebaseAuth firebaseAuth;

    public static void main(String[] args) {
        FirebaseAuth firebaseAuth = null;
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestoreDB;
        firestoreDB = FirebaseFirestore.getInstance();

        String dogBreedPreference = "BREED_EVERYTHING";
        String dogOwnerPreference = "LOVERS";
        int maxAge = 35;
        int minAge = 18;
        String sexPreference = "EVERYONE";



    }
}
