package esiea.binouze.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsyncImageRequestService extends AsyncTask<String, Integer, Map<String, Bitmap>> {

    private final static String IMAGE_URL = "http://www.binouze.fabrigli.fr";

    @Override
    protected Map<String, Bitmap> doInBackground(String... urls) {
        Map<String, Bitmap> map = new HashMap<>();
        if(urls != null) {
            for (String url : urls) {
                Bitmap image = getImage(IMAGE_URL + url);
                if(image != null) {
                    map.put(url, image);
                }
            }
        }
        return map;
    }

    // Méthode qui recupère une image sur le net
    private Bitmap getImage(String url) {
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }
}
