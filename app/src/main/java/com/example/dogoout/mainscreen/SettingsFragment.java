package com.example.dogoout.mainscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.preference.Preference;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hbb20.CountryCodePicker;

import java.time.LocalDate;


public class SettingsFragment extends Fragment {

    //Declare variables to change user information
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser currentUser;
    User userGet;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View settingsView = inflater.inflate(R.layout.fragment_settings, container, false);


        //Grab the current user from the intent
        User userFromIntent = (User) getActivity().getIntent().getSerializableExtra(Constants.USER_TAG);


        // T E S T   D A T A

        Preference preference = new Preference();
        preference.setSexPreference(Constants.PREF_OTHER);
        preference.setDogBreedPreference(Constants.PREF_BREED_ALL);
        preference.setDogOwnerPreference(Constants.PREF_DOG_OWNERS);
        preference.setMinAge(18);
        preference.setMaxAge(29);
        UserBuilder userBuilder = new UserBuilder()
                .withBirthDate(LocalDate.of(1998, 1, 1))
                .withCountry("US")
                .withDescription("I am an actor")
                //.withDog(dogBuilder1.build())
                .withEmail("tanguyvdvd@gmail.com")
                .withFirstname("Brad")
                .withGender("MALE")
                //.withPhotosUser(photos)
                .withSurname("Pitt")
                .withPrompt("Describe the perfect day with your dog.")
                .withPromptAnswer("going to the park")
                .withPreference(preference);


        User user = userBuilder.build();
        // E N D   T E S T   D A T A


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
        if (humanPreference == Constants.PREF_MEN)
            humanPrefRadioButton = settingsView.findViewById(R.id.rbtnMen);
        else if (humanPreference == Constants.PREF_WOMEN)
            humanPrefRadioButton = settingsView.findViewById(R.id.rbtnWomen);
        else if (humanPreference == Constants.PREF_OTHER)
            humanPrefRadioButton = settingsView.findViewById(R.id.rbtnOther);
        else
            humanPrefRadioButton = settingsView.findViewById(R.id.rbtnEveryoneSex);
        //Set the radio button to checked
        humanPrefRadioButton.setChecked(true);

        //Set the dog preference
        dogPreference = user.getPreference().getDogOwnerPreference();
        //Search for the radio button that matches the dog preference
        RadioButton dogPrefRadioButton;
        if (dogPreference == Constants.PREF_DOG_OWNERS)
            dogPrefRadioButton = settingsView.findViewById(R.id.rbtnDogOwners);
        else if (dogPreference == Constants.PREF_DOG_LOVERS)
            dogPrefRadioButton = settingsView.findViewById(R.id.rbtnDogLovers);
        else
            dogPrefRadioButton = settingsView.findViewById(R.id.rbtnEveryoneDogOwnersLovers);
        //Set the radio button to checked
        dogPrefRadioButton.setChecked(true);

        //Set the age range
        minAge = user.getPreference().getMinAge();
        maxAge = user.getPreference().getMaxAge();
        ageSlider.setValues((float) minAge, (float) maxAge);


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
                /*
                HashMap<String, Object> userUpdate = new HashMap<>();
                userUpdate.put("preference.sexPreference", humanPreference);
                userUpdate.put("preference.dogOwnerPreference", dogPreference);
                userUpdate.put("preference.minAge", minAge);
                userUpdate.put("preference.maxAge", maxAge);
                userUpdate.put("country", countryCode);
                fStore.collection("users").document(userID).update(userUpdate);
                */

                Toast.makeText(getContext(), "Settings saved", Toast.LENGTH_SHORT).show();
            }
        });

        //Logout button listener
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Clear the current user and go back to the login screen
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
                } else if (deleteCount == 2)
                    deleteBtn.setText("Are you really sure?");
                else if (deleteCount == 3) {
                    deleteBtn.setText("There is no going back!");
                    Toast.makeText(getContext(), "Last warning!", Toast.LENGTH_SHORT).show();
                } else if (deleteCount == 4)
                    deleteBtn.setText("DELETE ACCOUNT");
                else {
                    //TODO: Delete the current user from the database and go back to the login screen
                    Toast.makeText(getContext(), "Account deleted", Toast.LENGTH_SHORT).show();
                    deleteCount = 0;
                    //Reset the constraints
                    layoutParams.startToEnd = ConstraintLayout.LayoutParams.UNSET;
                    layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                    layoutParams.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                    deleteBtn.setLayoutParams(layoutParams);
                    deleteBtn.setText("delete");
                }

            }
        });


        // Inflate the layout for this fragment
        return settingsView;

    }
}