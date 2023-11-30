package com.example.dogoout.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

import com.example.dogoout.R;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.networking.ReadImage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CardAdapter extends BaseAdapter {

    Context context;

    ArrayList<User> users;

    ExecutorService service;

    public CardAdapter(Context context, ArrayList<User> users) {
        super();
        this.context = context;
        this.users = users;
        // Create a thread pool with 10 threads
        this.service = Executors.newFixedThreadPool(10);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        User user = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.card, viewGroup, false);
        }

        LinearLayout linLayoutContent = view.findViewById(R.id.linLayoutContent);

        ImageView imgVUserPhoto1 = view.findViewById(R.id.imgVUserPhoto1);
        ImageView imgVUserPhoto2 = view.findViewById(R.id.imgVUserPhoto2);
        ImageView imgVUserPhoto3 = view.findViewById(R.id.imgVUserPhoto3);
        TextView txtVNameAge = view.findViewById(R.id.txtVNameAge);
        TextView txtVDescription = view.findViewById(R.id.txtVDescription);
        TextView txtVUserPrompt = view.findViewById(R.id.txtVUserPrompt);
        TextView txtVUserPromptAnswer = view.findViewById(R.id.txtVUserPromptAnswer);


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

        Log.d("DOGS", user.getDogs().toString());

        displayUsersDogs(user.getDogs(), linLayoutContent);

        return view;
    }

    public void displayUsersDogs(ArrayList<Dog> dogs, LinearLayout linLayoutContent) {
        // Setting the layout parameters
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        LinearLayout.LayoutParams borderParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        borderParams.setMargins(0, 150, 0, 0);


        for (Dog dog : dogs) {

            // Create a new ImageView with 1st dog photo
            ImageView imgVDogPhoto1 = new ImageView(context);
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
            TextView txtVDogName = new TextView(context);
            txtVDogName.setLayoutParams(params);
            txtVDogName.setText(dog.getName() + " (" + dog.getBreed() + ")");
            TextViewCompat.setTextAppearance(txtVDogName, R.style.text_h1);

            // Create a new TextView with the dog characteristics
            TextView txtVDogCharacteristics = new TextView(context);
            txtVDogCharacteristics.setLayoutParams(params);
            txtVDogCharacteristics.setText(dog.getCharacteristics().toString().replace("[", "").replace("]", ""));
            TextViewCompat.setTextAppearance(txtVDogCharacteristics, R.style.text);

            // Create linear layout for prompt and prompt answer
            LinearLayout linLayoutPrompt = new LinearLayout(context);
            linLayoutPrompt.setLayoutParams(params);
            linLayoutPrompt.setOrientation(LinearLayout.VERTICAL);
            linLayoutPrompt.setBackground(context.getDrawable(R.drawable.placeholder_pink));
            linLayoutPrompt.setPadding(20, 20, 20, 20);
            // Create a new TextView with the dog prompt
            TextView txtVDogPrompt = new TextView(context);
            txtVDogPrompt.setLayoutParams(params);
            txtVDogPrompt.setText(dog.getPrompt());
            TextViewCompat.setTextAppearance(txtVDogPrompt, R.style.text_h3_black);
            // Create a new TextView with the dog prompt answer
            TextView txtVDogPromptAnswer = new TextView(context);
            txtVDogPromptAnswer.setLayoutParams(params);
            txtVDogPromptAnswer.setText(dog.getPromptAnswer());
            TextViewCompat.setTextAppearance(txtVDogPromptAnswer, R.style.text);
            // Add prompt and prompt answer to the linear layout
            linLayoutPrompt.addView(txtVDogPrompt);
            linLayoutPrompt.addView(txtVDogPromptAnswer);

            // Create a new ImageView with 2nd dog photo
            ImageView imgVDogPhoto2 = new ImageView(context);
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
            ImageView imgVDogPhoto3 = new ImageView(context);
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
            ((Activity) context).runOnUiThread((() -> {
                if (bitmap != null) {
                    // Display the image
                    imageView.setImageBitmap(bitmap);
                }
            }));
        });
    }
}

