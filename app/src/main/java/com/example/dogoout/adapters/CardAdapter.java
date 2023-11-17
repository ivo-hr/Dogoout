package com.example.dogoout.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogoout.R;
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

        ImageView imgVUserPhoto1 = view.findViewById(R.id.imgVUserPhoto1);
        TextView txtVNameAge = view.findViewById(R.id.txtVNameAge);
        TextView txtVDescription = view.findViewById(R.id.txtVDescription);
        TextView txtVUserPrompt = view.findViewById(R.id.txtVUserPrompt);
        TextView txtVUserPromptAnswer = view.findViewById(R.id.txtVUserPromptAnswer);


        // Download the user's photo
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            Bitmap bitmap = ReadImage.readImage(user.getPhotosUser().get(0).toString());

            ((Activity) context).runOnUiThread((() -> {
                if (bitmap != null) {
                    imgVUserPhoto1.setImageBitmap(bitmap);
                } else {
                    imgVUserPhoto1.setImageResource(R.drawable.ic_image);
                }
            }));
        });


        // calculate age in years
        LocalDate userBirthDate = user.getBirthDate();
        LocalDate now = LocalDate.now();
        int age = now.getYear() - userBirthDate.getYear();

        txtVNameAge.setText(user.getFirstname() + " (" + age + ")");
        txtVDescription.setText(user.getDescription());
        txtVUserPrompt.setText(user.getPrompt());
        txtVUserPromptAnswer.setText(user.getPromptAnswer());

        return view;
    }
}

