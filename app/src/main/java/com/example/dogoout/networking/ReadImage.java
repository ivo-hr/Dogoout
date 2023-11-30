package com.example.dogoout.networking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

public class ReadImage {
    public static Bitmap readImage(String address){
        Bitmap bitmapImage = null;

        try{
            InputStream inputStream = new URL(address).openStream();
            bitmapImage = BitmapFactory.decodeStream(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (bitmapImage == null)
            Log.d("MSG", "The image was not fetched");
        return bitmapImage;
    }
}
