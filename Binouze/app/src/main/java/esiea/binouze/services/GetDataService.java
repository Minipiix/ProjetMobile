package esiea.binouze.services;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class GetDataService {

    private final static String BIERE_URL = "http://www.binouze.fabrigli.fr/bieres";
    private final static String CATEGORIES_URL = "http://www.binouze.fabrigli.fr/categories";
    private final static String COUNTRIES_URL = "http://www.binouze.fabrigli.fr/countries";

    // Récupère la liste des bières
    public static String getBieres(Activity activity) {

        // Construit l'url pour acceder à la liste des bières
        String url = BIERE_URL + ".json";
        String result = null;
        try {
            AsyncHttpRequestService asyncHttpRequest = new AsyncHttpRequestService();
            asyncHttpRequest.execute(url);
            result = asyncHttpRequest.get();
        } catch(InterruptedException e ) {
            Log.e("[GET BIERE]", "Erreur lors de la récuperation des bières", e);
        } catch(ExecutionException e) {
            Log.e("[GET BIERE]", "Erreur lors de la récuperation des bières", e);
        }
        return result;
    }

    // Récupère la bières demandée
    public static String getBiere(Activity activity, Integer numBiere) {

        // Construit l'url pour acceder à la liste des bières
        String url = BIERE_URL + "/" + numBiere + ".json";
        String result = null;
        try {
            AsyncHttpRequestService asyncHttpRequest = new AsyncHttpRequestService();
            asyncHttpRequest.execute(url);
            result = asyncHttpRequest.get();
        } catch(InterruptedException e ) {
            Log.e("[GET BIERE]", "Erreur lors de la récuperation de la bière", e);
        } catch(ExecutionException e) {
            Log.e("[GET BIERE]", "Erreur lors de la récuperation de la bière", e);
        }
        return result;
    }

    // Récupère la liste des catégories
    public static String getCategories(Activity activity) {

        // Construit l'url pour acceder à la liste des categories
        String url = CATEGORIES_URL + ".json";
        String result = null;
        try {
            AsyncHttpRequestService asyncHttpRequest = new AsyncHttpRequestService();
            asyncHttpRequest.execute(url);
            result = asyncHttpRequest.get();
        } catch(InterruptedException e ) {
            Log.e("[GET CATEGORY]", "Erreur lors de la récuperation des categories", e);
        } catch(ExecutionException e) {
            Log.e("[GET CATEGORY]", "Erreur lors de la récuperation des categories", e);
        }
        return result;
    }

    // Récupère la categorie demandée
    public static String getCategorie(Activity activity, Integer numCategorie) {

        // Construit l'url pour acceder à la liste des categories
        String url = CATEGORIES_URL + "/" + numCategorie + ".json";
        String result = null;
        try {
            AsyncHttpRequestService asyncHttpRequest = new AsyncHttpRequestService();
            asyncHttpRequest.execute(url);
            result = asyncHttpRequest.get();
        } catch(InterruptedException e ) {
            Log.e("[GET CATEGORY]", "Erreur lors de la récuperation de la categorie", e);
        } catch(ExecutionException e) {
            Log.e("[GET CATEGORY]", "Erreur lors de la récuperation de la categorie", e);
        }
        return result;
    }

    // Récupère la liste des pays
    public static String getCountries(Activity activity) {

        // Construit l'url pour acceder à la liste des pays
        String url = COUNTRIES_URL + ".json";
        String result = null;
        try {
            AsyncHttpRequestService asyncHttpRequest = new AsyncHttpRequestService();
            asyncHttpRequest.execute(url);
            result = asyncHttpRequest.get();
        } catch(InterruptedException e ) {
            Log.e("[GET COUNTRY]", "Erreur lors de la récuperation des pays", e);
        } catch(ExecutionException e) {
            Log.e("[GET COUNTRY]", "Erreur lors de la récuperation des pays", e);
        }

        return result;
    }

    // Récupère le pays demandé
    public static String getCountry(Activity activity, Integer numCountry) {

        // Construit l'url pour acceder à la liste des pays
        String url = CATEGORIES_URL + "/" + numCountry + ".json";
        String result = null;
        try {
            AsyncHttpRequestService asyncHttpRequest = new AsyncHttpRequestService();
            asyncHttpRequest.execute(url);
            result = asyncHttpRequest.get();
        } catch(InterruptedException e ) {
            Log.e("[GET COUNTRY]", "Erreur lors de la récuperation du pays", e);
        } catch(ExecutionException e) {
            Log.e("[GET COUNTRY]", "Erreur lors de la récuperation du pays", e);
        }

        return result;
    }


}
