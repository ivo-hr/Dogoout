package com.example.dogoout.mainscreen;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.preference.Preference;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;
import com.example.dogoout.domain.user.UserImpl;
import com.example.dogoout.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hbb20.CountryCodePicker;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class SettingsFragment extends Fragment {

    //Declare variables to change user information
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser currentUser;
    UserImpl user;

    CountryCodePicker actxtVCountry;
    RadioGroup humanPref;
    RadioGroup dogPref;
    String humanPreference;
    String dogPreference;
    int minAge, maxAge;
    String countryCode;
    com.google.android.material.slider.RangeSlider ageSlider;
    androidx.appcompat.widget.AppCompatButton logoutBtn;
    androidx.appcompat.widget.AppCompatButton deleteBtn;
    androidx.appcompat.widget.AppCompatButton saveBtn;

    int deleteCount = 0;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View settingsView = inflater.inflate(R.layout.fragment_settings, container, false);

        //Grab the current user from the intent
        user = (UserImpl) getActivity().getIntent().getSerializableExtra(Constants.USER_TAG);

        //Grab the settings fragments
        actxtVCountry = settingsView.findViewById(R.id.actxtVCountry);
        humanPref = settingsView.findViewById(R.id.humanPreference);
        dogPref = settingsView.findViewById(R.id.dogPreference);
        ageSlider = settingsView.findViewById(R.id.rangeSlider);
        saveBtn = settingsView.findViewById(R.id.saveBut);
        logoutBtn = settingsView.findViewById(R.id.logoutBut);
        deleteBtn = settingsView.findViewById(R.id.deleteAccountBut);

        //Set the country code
        countryCode = user.getCountry();
        actxtVCountry.setCountryForNameCode(countryCode);

        //Set the human preference
        humanPreference = user.getPreference().getSexPreference();
        //Search for the radio button that matches the human preference
        RadioButton humanPrefRadioButton;

        if (humanPreference.equals(Constants.PREF_MEN))
            humanPrefRadioButton = settingsView.findViewById(R.id.rbtnMen);
        else if (humanPreference.equals(Constants.PREF_WOMEN))
            humanPrefRadioButton = settingsView.findViewById(R.id.rbtnWomen);
        else if (humanPreference.equals(Constants.PREF_OTHER))
            humanPrefRadioButton = settingsView.findViewById(R.id.rbtnOther);
        else humanPrefRadioButton = settingsView.findViewById(R.id.rbtnEveryoneSex);
        //Set the radio button to checked
        humanPrefRadioButton.setChecked(true);

        //Set the dog preference
        dogPreference = user.getPreference().getDogOwnerPreference();
        //Search for the radio button that matches the dog preference
        RadioButton dogPrefRadioButton;
        if (dogPreference.equals(Constants.PREF_DOG_OWNERS))
            dogPrefRadioButton = settingsView.findViewById(R.id.rbtnDogOwners);
        else if (dogPreference.equals(Constants.PREF_DOG_LOVERS))
            dogPrefRadioButton = settingsView.findViewById(R.id.rbtnDogLovers);
        else dogPrefRadioButton = settingsView.findViewById(R.id.rbtnEveryoneDogOwnersLovers);
        //Set the radio button to checked
        dogPrefRadioButton.setChecked(true);

        //Set the age range
        minAge = user.getPreference().getMinAge();
        maxAge = user.getPreference().getMaxAge();
        ageSlider.setValues((float) minAge, (float) maxAge);

        //Firebase Variables settings
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userID = currentUser.getUid();

        //Save button listener
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Grab the human preference from the radioGroup
                if (humanPref.getCheckedRadioButtonId() == R.id.rbtnMen)
                    humanPreference = Constants.PREF_MEN;
                else if (humanPref.getCheckedRadioButtonId() == R.id.rbtnWomen)
                    humanPreference = Constants.PREF_WOMEN;
                else if (humanPref.getCheckedRadioButtonId() == R.id.rbtnOther)
                    humanPreference = Constants.PREF_OTHER;
                else if (humanPref.getCheckedRadioButtonId() == R.id.rbtnEveryoneSex)
                    humanPreference = Constants.PREF_H_EVERYONE;
                //Grab the dog preference from the radioGroup
                if (dogPref.getCheckedRadioButtonId() == R.id.rbtnDogOwners)
                    dogPreference = Constants.PREF_DOG_OWNERS;
                else if (dogPref.getCheckedRadioButtonId() == R.id.rbtnDogLovers)
                    dogPreference = Constants.PREF_DOG_LOVERS;
                else if (dogPref.getCheckedRadioButtonId() == R.id.rbtnEveryoneDogOwnersLovers)
                    dogPreference = Constants.PREF_D_EVERYONE;

                //Grab the age range
                minAge = Math.round(ageSlider.getValues().get(0));
                maxAge = Math.round(ageSlider.getValues().get(1));

                //Grab the country code
                countryCode = actxtVCountry.getSelectedCountryNameCode();

                //Update the user
                user.setCountry(countryCode);
                user.getPreference().setSexPreference(humanPreference);
                user.getPreference().setDogOwnerPreference(dogPreference);
                user.getPreference().setMinAge(minAge);
                user.getPreference().setMaxAge(maxAge);

                //TODO: Update the user in the database
                if (currentUser != null) {
                    HashMap<String, Object> userUpdate = new HashMap<>();
                    userUpdate.put("userPreference.sexPreference", humanPreference);
                    userUpdate.put("userPreference.dogOwnerPreference", dogPreference);
                    userUpdate.put("userPreference.minAge", minAge);
                    userUpdate.put("userPreference.maxAge", maxAge);
                    userUpdate.put("country", countryCode);

                    fStore.collection("users").document(userID).update(userUpdate);

                } else {
                    Toast.makeText(getContext(), "GetIdFail", Toast.LENGTH_SHORT).show();
                    // No user is signed in
                    // Handle the case where the user is not authenticated
                }

                Toast.makeText(getContext(), "Settings saved", Toast.LENGTH_SHORT).show();
            }
        });

        //Logout button listener
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Clear the current user and go back to the login screen
                firebaseAuth.signOut();

                //Move to Login Screen
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                // Add flags to clear the back stack
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // Finish the hosting activity
                requireActivity().finish();

                Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();
            }
        });

        //Delete account button listener
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCount++;
                // Assuming you have a ConstraintLayout as the parent layout
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) deleteBtn.getLayoutParams();

                if (deleteCount == 1) {
                    // Modify the constraints as needed
                    layoutParams.startToEnd = R.id.glLeft2;
                    layoutParams.height = 200;
                    layoutParams.width = 1000;

                    // Apply the modified constraints
                    deleteBtn.setLayoutParams(layoutParams);

                    // Set the new text
                    deleteBtn.setText("Are you sure?");
                } else if (deleteCount == 2) {
                    deleteBtn.setText("Are you really sure?");
                } else if (deleteCount == 3) {
                    deleteBtn.setText("There is no going back!");
                    Toast.makeText(getContext(), "Last warning!", Toast.LENGTH_SHORT).show();
                } else if (deleteCount == 4) {
                    deleteBtn.setText("DELETE ACCOUNT");
                } else {
                    //TODO: Delete the current user from the database and go back to the login screen
                    //delete authorization from firebase
                    if (currentUser != null) {
                        //deletion complete. now delete photos from firebase storage
                        if (user.getDogs() != null) {
                            for (Dog dog : user.getDogs()) {
                                deletePhotosFromDB(dog.getPhotosDog());
                            }
                        }
                        deletePhotosFromDB(user.getPhotosUser());

                        fStore.collection("users")
                                .document(userID)
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        // User deleted successfully from Firebase Authentication
                                                        // Now, delete user data from Firestore
                                                        Log.d("DeleteAccountActivity", "User data deleted from Firestore");

                                                        Toast.makeText(getContext(), "Account deleted", Toast.LENGTH_SHORT).show();
                                                        deleteCount = 0;

                                                        //Reset the constraints
                                                        layoutParams.startToEnd = ConstraintLayout.LayoutParams.UNSET;
                                                        layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                                                        layoutParams.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                                                        deleteBtn.setLayoutParams(layoutParams);
                                                        deleteBtn.setText("delete");

                                                        //Go to Login screen
                                                        Intent intent = new Intent(requireContext(), LoginActivity.class);

                                                        // Add flags to clear the back stack
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(intent);

                                                        // Finish the hosting activity
                                                        requireActivity().finish();
                                                    } else {
                                                        Log.e("DeleteAccountActivity", "Failed to delete user account");
                                                    }
                                                }
                                            });
                                        } else {
                                            Log.e("DeleteAccountActivity", "Failed to delete user data from Firestore");
                                        }
                                    }
                                });
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return settingsView;

    }

    protected void deletePhotosFromDB(ArrayList<URI> arrayListPhotos) {
        for (int i = 0; i < arrayListPhotos.size(); ++i) {
            String uriString = arrayListPhotos.get(i).toString();
            StorageReference fileRef = FirebaseStorage.getInstance().getReferenceFromUrl(uriString);
            // Delete the storage file
            fileRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // File deleted successfully. Add  success handling code here
                    Log.d(TAG, "delete complete: " + uriString);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Add error handling code here
                    Log.d(TAG, "delete failed: " + uriString);
                }
            });
        }
    }
}