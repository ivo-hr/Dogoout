package com.example.dogoout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.user.UserBuilder;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class Register9Activity extends AppCompatActivity {

    // DECLARE COMPONENTS
    ImageView previousScreenBtn;
    Button nextScreenBtn;
    ImageView imgVAdd1, imgVAdd2, imgVAdd3;
    ImageView imgVDogImage1, imgVDogImage2, imgVDogImage3;

    // DECLARE VARIABLES
    URI[] imagesUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register9);

        // INITIALIZE VARIABLES
        imagesUri = new URI[3];

        // INITIALIZE COMPONENTS
        imgVAdd1 = findViewById(R.id.imgVAdd1);
        imgVAdd2 = findViewById(R.id.imgVAdd2);
        imgVAdd3 = findViewById(R.id.imgVAdd3);

        imgVDogImage1 = findViewById(R.id.imgVDogImage1);
        imgVDogImage2 = findViewById(R.id.imgVDogImage2);
        imgVDogImage3 = findViewById(R.id.imgVDogImage3);

        previousScreenBtn = findViewById(R.id.imgVBack);

        nextScreenBtn = findViewById(R.id.btnNext);

        // SET ON CLICK LISTENERS
        imgVAdd1.setOnClickListener(view -> {
            handleImageInput(imgVDogImage1, imgVAdd1, 1);
        });
        imgVAdd2.setOnClickListener(view -> {
            handleImageInput(imgVDogImage2, imgVAdd2, 2);
        });
        imgVAdd3.setOnClickListener(view -> {
            handleImageInput(imgVDogImage3, imgVAdd3, 3);
        });


        previousScreenBtn.setOnClickListener(view -> finish());

        nextScreenBtn.setOnClickListener(view -> {
            // Create an array list of image views
            ArrayList<ImageView> imageViews = new ArrayList<>();
            imageViews.add(imgVDogImage1);
            imageViews.add(imgVDogImage2);
            imageViews.add(imgVDogImage3);

            // Check if the user uploaded at least 1 imagesUri
            imagesUri = Arrays.stream(imagesUri)
                    .filter(uri -> uri != null)
                    .toArray(URI[]::new);

            ArrayList<URI> imagesUri = new ArrayList<>(Arrays.asList(this.imagesUri));
            if (imagesUri.size() < 1) {
                displayErrorMessage("Error", "You must upload at least 2 images.");
                return;
            }

            // Get the dog builder from the previous activity
            Intent intent = getIntent();
            DogBuilder dogBuilder = (DogBuilder) intent.getSerializableExtra(Constants.DOG_BUILDER_TAG);
            //Get the user builder from the previous activity to pass it to the next activity
            UserBuilder userBuilder = (UserBuilder) intent.getSerializableExtra(Constants.USER_BUILDER_TAG);
            //Get the number of dogs from the previous activity to pass it to the next activity
            int numberOfDogs = intent.getIntExtra(Constants.NUMBER_OF_DOGS_TAG, 0);

            // Add the imagesUri to the DogBuilder
            dogBuilder = dogBuilder.withPhotosDog(imagesUri);

            // Add the builder to the intent and pass it to the next activity
            Intent intentNextActivity = new Intent(getApplicationContext(), Register10Activity.class);
            intentNextActivity.putExtra(Constants.DOG_BUILDER_TAG, dogBuilder);
            intentNextActivity.putExtra(Constants.USER_BUILDER_TAG, userBuilder);
            intentNextActivity.putExtra(Constants.NUMBER_OF_DOGS_TAG, numberOfDogs);
            startActivity(intentNextActivity);
        });
    }

    protected void handleImageInput(ImageView imageView, ImageView addImageView, int requestCode) {
        if (imageView.getDrawable() == null) {
            // Open the select image dialog if the image view is empty
            ImagePicker
                    .with(Register9Activity.this)
                    .crop(4f, 5f)                            //Crop image,
                    .compress(1024)                        //Final image size will be less than 0,5 MB
                    .maxResultSize(1080, 1080)        //Final image resolution will be less than 1080 x 1080
                    .start(requestCode);
        } else {
            // Remove the image from the image view and replace the remove button with an add button
            imagesUri[requestCode - 1] = null;
            imageView.setImageResource(0);
            addImageView.setImageResource(R.drawable.ic_add);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        try {

            Uri uri = data.getData();
            switch (requestCode) {
                case 1:
                    // Set the image in ImageView and replace the add button with a remove button
                    imgVDogImage1.setImageURI(uri);
                    imagesUri[requestCode - 1] = new URI(uri.toString());
                    imgVAdd1.setImageResource(R.drawable.ic_remove);
                    break;
                case 2:
                    // Set the image in ImageView and replace the add button with a remove button
                    imgVDogImage2.setImageURI(uri);
                    imagesUri[requestCode - 1] = new URI(uri.toString());
                    imgVAdd2.setImageResource(R.drawable.ic_remove);
                    break;
                case 3:
                    // Set the image in ImageView and replace the add button with a remove button
                    imgVDogImage3.setImageURI(uri);
                    imagesUri[requestCode - 1] = new URI(uri.toString());
                    imgVAdd3.setImageResource(R.drawable.ic_remove);
                    break;
            }

        } catch (Exception e) {
            displayErrorMessage("Error", "Something went wrong. Try again.");
        }

    }

    private void displayErrorMessage(String title, String message) {
        // Display an error with alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}