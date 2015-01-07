package esiea.binouze.services;

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

public class AsyncHttpRequestService extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... urls) {
        if(urls != null && urls.length != 0) {
            return getHttpResponse(urls[0]);
        }
        return null;
    }


    // Méthode qui effectue une requete http get sur le server binouze.fabrigli.fr
    private String getHttpResponse(String url) {

        StringBuilder response = new StringBuilder();
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                Log.d("[GET REQUEST]", "La requête http a abouti");

                HttpEntity messageEntity = httpResponse.getEntity();
                InputStream is = messageEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (Exception e) {
            Log.e("[GET REQUEST]", "Erreur lors de la connexion au serveur", e);
        }
        Log.d("[GET REQUEST]", "Fin de lecture du resultat de la requête");
        return response.toString();
    }
}
