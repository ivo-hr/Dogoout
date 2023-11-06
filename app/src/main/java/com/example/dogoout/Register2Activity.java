package com.example.dogoout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

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
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String birthday = String.format("%d%02d%02d", year, month, day);

                Intent intent = new Intent(getApplicationContext(), Register3Activity.class);
                intent.putExtra("birthday", birthday);
                intent.putExtra("country", actxtVCountry.getSelectedCountryName());
                startActivity(intent);
            }
        });
    }
}