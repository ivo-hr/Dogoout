package com.example.dogoout.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.user.UserBuilder;

import java.util.ArrayList;

public class Register10Activity extends AppCompatActivity {

    // DECLARE COMPONENTS
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    CheckBox cbxLoyal;
    CheckBox cbxEnergetic;
    CheckBox cbxPlayful;
    CheckBox cbxGentle;
    CheckBox cbxAffectionate;
    CheckBox cbxAdventurous;
    CheckBox cbxProtective;
    CheckBox cbxStubborn;
    CheckBox cbxObedient;
    CheckBox cbxAffable;
    CheckBox cbxFriendly;
    CheckBox cbxIndependant;
    CheckBox cbxIntelligent;
    CheckBox cbxInquisitive;
    CheckBox cbxCourageous;
    CheckBox cbxSocial;

    // DECLARE VARIABLES

    CheckBox[] allCheckboxes;

    int checkedCheckBoxCount = 0;

    ArrayList<String> dogCharacteristics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register10);

        // INITIALIZE COMPONENTS
        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        cbxLoyal = findViewById(R.id.cbxLoyal);
        cbxEnergetic = findViewById(R.id.cbxEnergetic);
        cbxPlayful = findViewById(R.id.cbxPlayful);
        cbxGentle = findViewById(R.id.cbxGentle);
        cbxAffectionate = findViewById(R.id.cbxAffectionate);
        cbxAdventurous = findViewById(R.id.cbxAdventurous);
        cbxProtective = findViewById(R.id.cbxProtective);
        cbxStubborn = findViewById(R.id.cbxStubborn);
        cbxObedient = findViewById(R.id.cbxObedient);
        cbxAffable = findViewById(R.id.cbxAffable);
        cbxFriendly = findViewById(R.id.cbxFriendly);
        cbxIndependant = findViewById(R.id.cbxIndependant);
        cbxIntelligent = findViewById(R.id.cbxIntelligent);
        cbxInquisitive = findViewById(R.id.cbxInquisitive);
        cbxCourageous = findViewById(R.id.cbxCourageous);
        cbxSocial = findViewById(R.id.cbxSocial);

        // Initialize the array of checkboxes
        allCheckboxes = new CheckBox[]{
                cbxLoyal,
                cbxEnergetic,
                cbxPlayful,
                cbxGentle,
                cbxAffectionate,
                cbxAdventurous,
                cbxProtective,
                cbxStubborn,
                cbxObedient,
                cbxAffable,
                cbxFriendly,
                cbxIndependant,
                cbxIntelligent,
                cbxInquisitive,
                cbxCourageous,
                cbxSocial
        };


        // Set the listener for all the checkboxes
        for (CheckBox checkbox : allCheckboxes) {
            checkbox.setOnCheckedChangeListener(new MyCheckBoxChangeListener());
        }

        previousScreenBtn.setOnClickListener(view -> finish());
        nextScreenBtn.setOnClickListener(view -> {
            if (isEnoughCheckedBoxe()) {
                // get the intent from the previous activity
                Intent intent = getIntent();

                // get the number of dogs from the intent
                int numberOfDogs = intent.getIntExtra(Constants.NUMBER_OF_DOGS_TAG, 0);
                // get the user builder from the intent
                UserBuilder userBuilder = (UserBuilder) intent.getSerializableExtra(Constants.USER_BUILDER_TAG);
                // get the dog builder from the intent
                DogBuilder dogBuilder = (DogBuilder) intent.getSerializableExtra(Constants.DOG_BUILDER_TAG);

                // add the dog characteristics to the dog builder
                dogBuilder = dogBuilder.withCharacteristics(dogCharacteristics);

                // pass the user builder, dog builder and number of dogs to the next activity
                Intent intentNextActivity = new Intent(getApplicationContext(), Register11Activity.class);
                intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                intentNextActivity.putExtra(Constants.DOG_BUILDER_TAG, dogBuilder);
                intentNextActivity.putExtra(Constants.NUMBER_OF_DOGS_TAG, numberOfDogs);
                startActivity(intentNextActivity);
            }
        });
    }

    class MyCheckBoxChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Update the counter based on the checkbox state
            if (isChecked) {
                // Add the characteristic to the list
                checkedCheckBoxCount++;
                dogCharacteristics.add(buttonView.getText().toString().trim());
            } else {
                // Remove the characteristic from the list
                checkedCheckBoxCount--;
                dogCharacteristics.remove(buttonView.getText().toString().trim());
            }

            // disable all unselected checkboxes if the max number of checkboxes is reached
            if (checkedCheckBoxCount >= 5) {
                for (CheckBox checkbox : allCheckboxes) {
                    if (!checkbox.isChecked()) {
                        checkbox.setEnabled(false);
                    }
                }
            } else {
                for (CheckBox checkbox : allCheckboxes) {
                    checkbox.setEnabled(true);
                }
            }
        }
    }

    protected boolean isEnoughCheckedBoxe() {
        if (checkedCheckBoxCount >= 3 && checkedCheckBoxCount <= 5) {
            return true;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Register10Activity.this);
            builder.setTitle("Error");
            builder.setMessage("You should choose at least 3 characteristics");
            builder.setPositiveButton("OK", null);
            builder.show();
            return false;
        }
    }
}