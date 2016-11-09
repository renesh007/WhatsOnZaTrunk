package com.example.reneshn.whatsonza;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by ReneshN on 2016/11/09.
 */

public class DownloadImage extends AsyncTask<String,Void,Bitmap> {
    private ImageView bmImage;

    public DownloadImage() {
    }

    public DownloadImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap mIcon = null;

        try {
            InputStream in =  new java.net.URL(urlDisplay).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        }
        catch (Exception e){
            Log.e("Bitmap error",e.getMessage());
            e.printStackTrace();
        }

        return mIcon;
    }
    @Override
    protected void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }
}
