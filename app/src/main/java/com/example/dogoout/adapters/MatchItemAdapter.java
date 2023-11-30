package com.example.dogoout.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogoout.R;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserImpl;
import com.example.dogoout.networking.ReadImage;
import com.example.dogoout.userInfo.UserInfoActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatchItemAdapter extends BaseAdapter {

    Context context;

    ArrayList<User> users;

    ExecutorService service;

    public MatchItemAdapter(Context context, ArrayList<User> users) {
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
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserImpl user = (UserImpl) getItem(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.match_item, viewGroup, false);
        }

        // Get the views
        ImageView imgVImage = view.findViewById(R.id.imgVImage);
        TextView txtVNameAge = view.findViewById(R.id.txtVNameAge);

        // Calculate age in years
        LocalDate userBirthDate = user.getBirthDate();
        LocalDate now = LocalDate.now();
        int age = now.getYear() - userBirthDate.getYear();

        // Set the name and age
        txtVNameAge.setText(user.getFirstname() + " (" + age + ")");

        // Download the  user photo
        if (user.getPhotosUser().size() >= 1) {
            downloadAndDisplayPhotos(user.getPhotosUser().get(0).toString(), imgVImage);
        } else {
            imgVImage.setImageResource(R.drawable.ic_image);
        }

        // Add click listener to the view
        view.setOnClickListener(view1 -> {
            // Start the activity UserInfoActivity
            Intent intent = new Intent(context, UserInfoActivity.class);
            intent.putExtra(Constants.USER_TAG, user);
            context.startActivity(intent);
        });

        return view;
    }

    public void downloadAndDisplayPhotos(String url, ImageView imageView) {
        this.service.submit(() -> {
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
