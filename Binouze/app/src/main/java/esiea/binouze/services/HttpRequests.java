package esiea.binouze.services;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpRequests {

    private final static String BIERE_URL = "binouze.fabrigli.fr/bieres";
    private final static String CATEGORIES_URL = "binouze.fabrigli.fr/categories";
    private final static String COUNTRIES_URL = "binouze.fabrigli.fr/countries";

    // Récupère la liste des bières
    public static String getBieres() {

        // Construit l'url pour acceder à la liste des bières
        String url = BIERE_URL + ".json";
        String resultat = null;
        try {
            resultat = getHttpResponse(url);
        } catch (URISyntaxException e) {
            Log.e("[GET BIERES]", "L'url donnée n'est pas correcte", e);
        }
        return resultat;
    }

    // Récupère la bières demandée
    public static String getBiere(Integer numBiere) {

        // Construit l'url pour acceder à la liste des bières
        String url = BIERE_URL + "/" + numBiere + ".json";
        String resultat = null;
        try {
            resultat = getHttpResponse(url);
        } catch (URISyntaxException e) {
            Log.e("[GET BIERES]", "L'url donnée n'est pas correcte", e);
        }
        return resultat;
    }

    // Récupère la liste des catégories
    public static String getCategories() {

        // Construit l'url pour acceder à la liste des categories
        String url = CATEGORIES_URL + ".json";
        String resultat = null;
        try {
            resultat = getHttpResponse(url);
        } catch (URISyntaxException e) {
            Log.e("[GET CATEGORY]", "L'url donnée n'est pas correcte", e);
        }
        return resultat;
    }

    // Récupère la categorie demandée
    public static String getCategorie(Integer numCategorie) {

        // Construit l'url pour acceder à la liste des categories
        String url = CATEGORIES_URL + "/" + numCategorie + ".json";
        String resultat = null;
        try {
            resultat = getHttpResponse(url);
        } catch (URISyntaxException e) {
            Log.e("[GET CATEGORY]", "L'url donnée n'est pas correcte", e);
        }
        return resultat;
    }

    // Récupère la liste des pays
    public static String getCountries() {

        // Construit l'url pour acceder à la liste des pays
        String url = COUNTRIES_URL + ".json";
        String resultat = null;
        try {
            resultat = getHttpResponse(url);
        } catch (URISyntaxException e) {
            Log.e("[GET COUNTRY]", "L'url donnée n'est pas correcte", e);
        }
        return resultat;
    }

    // Récupère le pays demandé
    public static String getCountry(Integer numCountry) {

        // Construit l'url pour acceder à la liste des pays
        String url = CATEGORIES_URL + "/" + numCountry + ".json";
        String resultat = null;
        try {
            resultat = getHttpResponse(url);
        } catch (URISyntaxException e) {
            Log.e("[GET COUNTRY]", "L'url donnée n'est pas correcte", e);
        }
        return resultat;
    }


    // Méthode qui effectue une requete http get sur le server binouze.fabrigli.fr
    private static String getHttpResponse(String uriString) throws URISyntaxException {
        URI uri = new URI(uriString);
        StringBuilder response = new StringBuilder();
        try {
            HttpGet get = new HttpGet();
            get.setURI(uri);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(get);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
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
            Log.e("[GET REQUEST]", e.getMessage());
        }
        Log.d("[GET REQUEST]", "Fin de lecture du resultat de la requête");
        return response.toString();
    }
}
