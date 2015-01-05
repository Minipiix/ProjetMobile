package esiea.binouze.services;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import esiea.binouze.model.Biere;

public class ParserJson {

    private final static String KEY_ID = "id";
    private final static String KEY_NAME = "name";
    private final static String KEY_DESCRPTION = "description";
    private final static String KEY_COUNTRY = "country";
    private final static String KEY_BUVEUR = "buveur";
    private final static String KEY_NOTE = "note";
    private final static String KEY_NOTE_MOYENNE = "note_moyenne";
    private final static String KEY_NUMBER_OF_NOTE = "number_of_notes";
    private final static String KEY_IMAGE = "image";
    private final static String KEY_THUMB = "thumb";
    private final static String KEY_CATEGORY_ID = "category_id";
    private final static String KEY_CATEGORY = "category";
    private final static String KEY_CREATED_AT = "created_at";
    private final static String KEY_UPDATED_AT = "updated_at";

    // méthode qui récupère un objet Biere depuis un json
    public static Biere getBiereFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        Biere biere = new Biere();
        biere.setId(jsonObject.getInt(KEY_ID));
        biere.setName(jsonObject.getString(KEY_NAME));
        biere.setDescription(jsonObject.getString(KEY_DESCRPTION));
        biere.setCountry(jsonObject.getString(KEY_COUNTRY));
        biere.setBuveur(jsonObject.getString(KEY_BUVEUR));
        biere.setNote(jsonObject.getInt(KEY_NOTE));
        biere.setNote_moyenne(jsonObject.getInt(KEY_NOTE_MOYENNE));
        biere.setNumber_of_notes(jsonObject.getInt(KEY_NUMBER_OF_NOTE));
        biere.setImage(jsonObject.getString(KEY_IMAGE));
        biere.setThumb(jsonObject.getString(KEY_THUMB));
        biere.setCategory_id(jsonObject.getInt(KEY_CATEGORY_ID));
        biere.setCategory(jsonObject.getString(KEY_CATEGORY));
        biere.setCreated_at(parseDate(jsonObject.getString(KEY_CREATED_AT)));
        biere.setUpdated_at(parseDate(jsonObject.getString(KEY_UPDATED_AT)));

        return biere;
    }

    // Méthode qui récupère la date depuis le fichier json
    private static Date parseDate(String dateString) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

        Date result = null;
        try {
            if (dateString != null) {
                result = simpleDateFormat.parse(dateString);
            }
        } catch (ParseException e) {
            Log.e("[JSON PARSER]", "Error lors du parsing de la date " + dateString, e);
        }
        return result;

    }
}
