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

    public CardAdapter(Context context, ArrayList<User> users) {
        super();
        this.context = context;
        this.users = users;
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


        // Create a thread pool with 3 threads
        ExecutorService service = Executors.newFixedThreadPool(10);

        // Download the 1st user photo
        if (user.getPhotosUser().size() >= 1) {
            downloadAndDisplayPhotos(service, user.getPhotosUser().get(0).toString(), imgVUserPhoto1);
        } else {
            imgVUserPhoto1.setVisibility(View.GONE);
        }

        // Download the 2nd user photo
        if (user.getPhotosUser().size() >= 2) {
            downloadAndDisplayPhotos(service, user.getPhotosUser().get(1).toString(), imgVUserPhoto2);
        } else {
            imgVUserPhoto2.setVisibility(View.GONE);
        }

        // Download the 3rd user photo
        if (user.getPhotosUser().size() >= 3) {
            downloadAndDisplayPhotos(service, user.getPhotosUser().get(2).toString(), imgVUserPhoto3);
        } else {
            imgVUserPhoto3.setVisibility(View.GONE);
        }

        // calculate age in years
        LocalDate userBirthDate = user.getBirthDate();
        LocalDate now = LocalDate.now();
        int age = now.getYear() - userBirthDate.getYear();

        txtVNameAge.setText(user.getFirstname() + " (" + age + ")");
        txtVDescription.setText(user.getDescription());
        txtVUserPrompt.setText(user.getPrompt());
        txtVUserPromptAnswer.setText(user.getPromptAnswer());

        Log.d("DOGS", user.getDogs().toString());

        displayUsersDogs(user.getDogs(), linLayoutContent, service);

        return view;
    }

    public void displayUsersDogs(ArrayList<Dog> dogs, LinearLayout linLayoutContent, ExecutorService service) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (Dog dog : dogs) {

            // Create a image view for the dog photo
            ImageView imgVDogPhoto1 = new ImageView(context);
            imgVDogPhoto1.setLayoutParams(params);

            // Download the 1st dog photo
            if (dog.getPhotosDog().size() >= 1) {
                downloadAndDisplayPhotos(service, dog.getPhotosDog().get(0).toString(), imgVDogPhoto1);
            } else {
                imgVDogPhoto1.setVisibility(View.GONE);
            }


            linLayoutContent.addView(imgVDogPhoto1);
        }
    }

    public void downloadAndDisplayPhotos(ExecutorService service, String url, ImageView imageView) {
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

