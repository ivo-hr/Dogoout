package com.example.dogoout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class Register10Activity extends AppCompatActivity {
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

    int checkedCheckBoxCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register10);

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

        CheckBox[] allCheckboxes = {cbxLoyal, cbxEnergetic,cbxPlayful,
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
        cbxInquisitive};

        //Check if more than 5 checkboxes are checked
        int maxCheckboxes = 5;
        
        for (CheckBox checkbox : allCheckboxes) {
            checkbox.setOnCheckedChangeListener(new MyCheckBoxChangeListener());

        }

        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (isEnoughCheckedBoxe()) {
                    Intent intent = new Intent(getApplicationContext(), Register11Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    class MyCheckBoxChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Update the counter based on the checkbox state
            if (isChecked) {
                checkedCheckBoxCount++;
            } else {
                checkedCheckBoxCount--;
            }
        }
    }

    protected boolean isEnoughCheckedBoxe() {

        if (checkedCheckBoxCount>=3) {
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