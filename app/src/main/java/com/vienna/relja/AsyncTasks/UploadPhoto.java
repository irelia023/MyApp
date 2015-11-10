package com.vienna.relja.AsyncTasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;

import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by reljica on 10/1/2015.
 */
public class UploadPhoto extends AsyncTask<Void, Void, Void> {

    Bitmap image;
    String name;

    public UploadPhoto(Bitmap image,String name){
        this.image = image;
        this.name = name;
    }

    @Override
    protected Void doInBackground(Void... params) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        HashMap<String, String> dataToSend = new HashMap<>();
        dataToSend.put("image", encodedImage);


        return null;
    }


}
