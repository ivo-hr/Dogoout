package com.example.dogoout.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.UserBuilder;
import com.hbb20.CountryCodePicker;

import com.example.dogoout.validation.Validator;

import java.time.LocalDate;

public class Register2Activity extends AppCompatActivity {
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    DatePicker datePicker;
    CountryCodePicker actxtVCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        previousScreenBtn = findViewById(R.id.imgVBack);
        nextScreenBtn = findViewById(R.id.btnNext);
        datePicker = findViewById(R.id.datePicker);
        actxtVCountry = findViewById(R.id.actxtVCountry);

        final Calendar maxBirthday = Calendar.getInstance();
        maxBirthday.add(Calendar.YEAR, -18);
        datePicker.setMaxDate(maxBirthday.getTimeInMillis());
        previousScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the date from the date picker
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                LocalDate birthday = LocalDate.of(year, month, day);

                // Check if the age is valid
                if (!Validator.isValidAge(birthday)){
                    //Show error toast and return
                    Toast.makeText(getApplicationContext(), "Must be between 18 and 100 to register!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Get the builder from the previous activity
                Intent intent = getIntent();
                UserBuilder userBuilder = (UserBuilder) intent.getSerializableExtra(Constants.USER_BUILDER_TAG);



                // Get the country code from the country code picker
                String countryCode = actxtVCountry.getSelectedCountryNameCode();

                // Add the attributes to the builder
                userBuilder = userBuilder.withBirthDate(birthday).withCountry(countryCode);

                // Add the builder to the intent and pass it to the next activity
                Intent intentNextActivity = new Intent(getApplicationContext(), Register3Activity.class);
                intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
                startActivity(intentNextActivity);
            }
        });
    }
}