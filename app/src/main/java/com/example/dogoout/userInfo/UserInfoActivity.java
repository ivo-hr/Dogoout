package com.example.dogoout.userInfo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.networking.ReadImage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserInfoActivity extends AppCompatActivity {

    // DECLARING COMPONENTS
    ImageView imgVUserPhoto1, imgVUserPhoto2, imgVUserPhoto3, imgVBack;
    TextView txtVNameAge, txtVDescription, txtVUserPrompt, txtVUserPromptAnswer;

    // DECLARING VARIABLES
    ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // ININTIALIZING VARIABLES
        service = Executors.newFixedThreadPool(10);

        // Getting user from intent
        User user = (User) getIntent().getSerializableExtra(Constants.USER_TAG);

        // ININTIALIZING COMPONENTS
        LinearLayout linLayoutContent = findViewById(R.id.linLayoutContent);

        imgVUserPhoto1 = findViewById(R.id.imgVUserPhoto1);
        imgVUserPhoto2 = findViewById(R.id.imgVUserPhoto2);
        imgVUserPhoto3 = findViewById(R.id.imgVUserPhoto3);
        imgVBack = findViewById(R.id.imgVBack);

        txtVNameAge = findViewById(R.id.txtVNameAge);
        txtVDescription = findViewById(R.id.txtVDescription);
        txtVUserPrompt = findViewById(R.id.txtVUserPrompt);
        txtVUserPromptAnswer = findViewById(R.id.txtVUserPromptAnswer);

        // Download the 1st user photo
        if (user.getPhotosUser().size() >= 1) {
            downloadAndDisplayPhotos(user.getPhotosUser().get(0).toString(), imgVUserPhoto1);
        } else {
            imgVUserPhoto1.setVisibility(View.GONE);
        }

        // Download the 2nd user photo
        if (user.getPhotosUser().size() >= 2) {
            downloadAndDisplayPhotos(user.getPhotosUser().get(1).toString(), imgVUserPhoto2);
        } else {
            imgVUserPhoto2.setVisibility(View.GONE);
        }

        // Download the 3rd user photo
        if (user.getPhotosUser().size() >= 3) {
            downloadAndDisplayPhotos(user.getPhotosUser().get(2).toString(), imgVUserPhoto3);
        } else {
            imgVUserPhoto3.setVisibility(View.GONE);
        }

        // Calculate age in years
        LocalDate userBirthDate = user.getBirthDate();
        LocalDate now = LocalDate.now();
        int age = now.getYear() - userBirthDate.getYear();

        // Display the user info
        txtVNameAge.setText(user.getFirstname() + " (" + age + ")");
        txtVDescription.setText(user.getDescription());
        txtVUserPrompt.setText(user.getPrompt());
        txtVUserPromptAnswer.setText(user.getPromptAnswer());

        // Display the user's dogs
        if (user.getDogs() != null)
            displayUsersDogs(user.getDogs(), linLayoutContent);


        // SETTING ONCLICK LISTENERS
        imgVBack.setOnClickListener(view -> {
            finish();
        });
    }

    public void displayUsersDogs(ArrayList<Dog> dogs, LinearLayout linLayoutContent) {
        // Setting the layout parameters
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        LinearLayout.LayoutParams borderParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        borderParams.setMargins(0, 150, 0, 0);


        for (Dog dog : dogs) {

            // Create a new ImageView with 1st dog photo
            ImageView imgVDogPhoto1 = new ImageView(this);
            imgVDogPhoto1.setLayoutParams(borderParams);
            imgVDogPhoto1.setImageResource(R.drawable.ic_image);
            imgVDogPhoto1.setAdjustViewBounds(true);
            imgVDogPhoto1.setScaleType(ImageView.ScaleType.CENTER_CROP);


            // Download the 1st dog photo
            if (dog.getPhotosDog().size() >= 1) {
                downloadAndDisplayPhotos(dog.getPhotosDog().get(0).toString(), imgVDogPhoto1);
            } else {
                imgVDogPhoto1.setVisibility(View.GONE);
            }

            // Create a new TextView with the dog name
            TextView txtVDogName = new TextView(this);
            txtVDogName.setLayoutParams(params);
            txtVDogName.setText(dog.getName() + " (" + dog.getBreed() + ")");
            TextViewCompat.setTextAppearance(txtVDogName, R.style.text_h1);

            // Create a new TextView with the dog characteristics
            TextView txtVDogCharacteristics = new TextView(this);
            txtVDogCharacteristics.setLayoutParams(params);
            txtVDogCharacteristics.setText(dog.getCharacteristics().toString().replace("[", "").replace("]", ""));
            TextViewCompat.setTextAppearance(txtVDogCharacteristics, R.style.text);

            // Create linear layout for prompt and prompt answer
            LinearLayout linLayoutPrompt = new LinearLayout(this);
            linLayoutPrompt.setLayoutParams(params);
            linLayoutPrompt.setOrientation(LinearLayout.VERTICAL);
            linLayoutPrompt.setBackground(this.getDrawable(R.drawable.placeholder_pink));
            linLayoutPrompt.setPadding(20, 20, 20, 20);
            // Create a new TextView with the dog prompt
            TextView txtVDogPrompt = new TextView(this);
            txtVDogPrompt.setLayoutParams(params);
            txtVDogPrompt.setText(dog.getPrompt());
            TextViewCompat.setTextAppearance(txtVDogPrompt, R.style.text_h3_black);
            // Create a new TextView with the dog prompt answer
            TextView txtVDogPromptAnswer = new TextView(this);
            txtVDogPromptAnswer.setLayoutParams(params);
            txtVDogPromptAnswer.setText(dog.getPromptAnswer());
            TextViewCompat.setTextAppearance(txtVDogPromptAnswer, R.style.text);
            // Add prompt and prompt answer to the linear layout
            linLayoutPrompt.addView(txtVDogPrompt);
            linLayoutPrompt.addView(txtVDogPromptAnswer);

            // Create a new ImageView with 2nd dog photo
            ImageView imgVDogPhoto2 = new ImageView(this);
            imgVDogPhoto2.setLayoutParams(params);
            imgVDogPhoto2.setImageResource(R.drawable.ic_image);
            imgVDogPhoto2.setAdjustViewBounds(true);
            imgVDogPhoto2.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Download the 2nd dog photo
            if (dog.getPhotosDog().size() >= 2) {
                downloadAndDisplayPhotos(dog.getPhotosDog().get(1).toString(), imgVDogPhoto2);
            } else {
                imgVDogPhoto2.setVisibility(View.GONE);
            }

            // Create a new ImageView with 3rd dog photo
            ImageView imgVDogPhoto3 = new ImageView(this);
            imgVDogPhoto3.setLayoutParams(params);
            imgVDogPhoto3.setImageResource(R.drawable.ic_image);
            imgVDogPhoto3.setAdjustViewBounds(true);
            imgVDogPhoto3.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Download the 3rd dog photo
            if (dog.getPhotosDog().size() >= 3) {
                downloadAndDisplayPhotos(dog.getPhotosDog().get(2).toString(), imgVDogPhoto3);
            } else {
                imgVDogPhoto3.setVisibility(View.GONE);
            }

            // Display the dog data
            linLayoutContent.addView(imgVDogPhoto1);
            linLayoutContent.addView(txtVDogName);
            linLayoutContent.addView(txtVDogCharacteristics);
            linLayoutContent.addView(linLayoutPrompt);
            linLayoutContent.addView(imgVDogPhoto2);
            linLayoutContent.addView(imgVDogPhoto3);
        }
    }


    public void downloadAndDisplayPhotos(String url, ImageView imageView) {
        service.submit(() -> {
            Bitmap bitmap = ReadImage.readImage(url);
            runOnUiThread((() -> {
                if (bitmap != null) {
                    // Display the image
                    imageView.setImageBitmap(bitmap);
                }
            }));
        });
    }
}