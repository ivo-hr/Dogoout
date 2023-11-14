package com.example.dogoout.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.preference.Preference;
import com.example.dogoout.domain.user.UserBuilder;

public class Register12Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register12);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //Grab the human preference from the radioGroup
                RadioGroup humanPref = findViewById(R.id.humanPreference);
                //Grab the dog preference from the radioGroup
                RadioGroup dogPref = findViewById(R.id.dogPreference);

                //Grab the selected radio button from the human preference radio group
                String humanPreference = "";
                if (humanPref.getCheckedRadioButtonId() == R.id.rbtnMen)
                    humanPreference = Constants.PREF_MEN;
                else if (humanPref.getCheckedRadioButtonId() == R.id.rbtnWomen)
                    humanPreference = Constants.PREF_WOMEN;
                else if (humanPref.getCheckedRadioButtonId() == R.id.rbtnOther)
                    humanPreference = Constants.PREF_OTHER;
                else if (humanPref.getCheckedRadioButtonId() == R.id.rbtnEveryoneSex)
                    humanPreference = Constants.PREF_H_EVERYONE;
                //Grab the selected radio button from the dog preference radio group
                String dogPreference = "";
                if (dogPref.getCheckedRadioButtonId() == R.id.rbtnDogOwners)
                    dogPreference = Constants.PREF_DOG_OWNERS;
                else if (dogPref.getCheckedRadioButtonId() == R.id.rbtnDogLovers)
                    dogPreference = Constants.PREF_DOG_LOVERS;
                else if (dogPref.getCheckedRadioButtonId() == R.id.rbtnEveryoneDogOwnersLovers)
                    dogPreference = Constants.PREF_D_EVERYONE;


                //Grab user builder and dog builder from previous screen
                Intent intentPreviousActivity = getIntent();
                UserBuilder userBuilder = (UserBuilder) intentPreviousActivity.getSerializableExtra(Constants.USER_BUILDER_TAG);

                //Add user preference to the Preferences class
                Preference preference = new Preference();
                preference.setSexPreference(humanPreference);
                preference.setDogOwnerPreference(dogPreference);
                preference.setDogBreedPreference(Constants.PREF_BREED_ALL);

                //Add the human preference to the user builder
                userBuilder = userBuilder.withPreference(preference);

                //Pass user builder to next screen
                Intent nextIntent = new Intent(getApplicationContext(), Register13Activity.class);
                nextIntent.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                startActivity(nextIntent);
            }
        });
    }
}