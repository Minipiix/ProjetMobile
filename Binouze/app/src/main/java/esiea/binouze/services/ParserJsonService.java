package esiea.binouze.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import esiea.binouze.model.Beer;
import esiea.binouze.model.Category;
import esiea.binouze.model.Country;

public class ParserJsonService {

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
    public static Beer getBiereFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        Beer beer = new Beer();
        beer.setId(jsonObject.getInt(KEY_ID));
        beer.setName(jsonObject.getString(KEY_NAME));
        beer.setDescription(jsonObject.getString(KEY_DESCRPTION));
        beer.setCountry(jsonObject.getString(KEY_COUNTRY));
        beer.setBuveur(jsonObject.getString(KEY_BUVEUR));
        beer.setNote(jsonObject.getInt(KEY_NOTE));
        beer.setNote_moyenne(jsonObject.getInt(KEY_NOTE_MOYENNE));
        beer.setNumber_of_notes(jsonObject.getInt(KEY_NUMBER_OF_NOTE));
        beer.setImage(jsonObject.getString(KEY_IMAGE));
        beer.setThumb(jsonObject.getString(KEY_THUMB));
        beer.setCategory_id(jsonObject.getInt(KEY_CATEGORY_ID));
        beer.setCategory(jsonObject.getString(KEY_CATEGORY));
        beer.setCreated_at(parseDate(jsonObject.getString(KEY_CREATED_AT)));
        beer.setUpdated_at(parseDate(jsonObject.getString(KEY_UPDATED_AT)));

        return beer;
    }

    // méthode qui récupère un objet Pays depuis un json
    public static Country getPaysFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        Country country = new Country();
        country.setId(jsonObject.getInt(KEY_ID));
        country.setName(jsonObject.getString(KEY_NAME));
        country.setImage(jsonObject.getString(KEY_IMAGE));

        return country;
    }

    public static List<Category> getCategoriesListFromJson(String json) {
        List<Category> categories = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++)
            {
                Category category = getCategorieFromJson(jsonArray.getString(i));
                categories.add(category);
            }
        } catch (JSONException e) {
            Log.e("[CATEGORY JSON PARSING]", "Impossible de parser les categories récuperées", e);
        }
        return categories;
    }

    // méthode qui récupère un objet Categorie depuis un json
    public static Category getCategorieFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        Category category = new Category();
        category.setId(jsonObject.getInt(KEY_ID));
        category.setName(jsonObject.getString(KEY_NAME));
        category.setDescription(jsonObject.getString(KEY_DESCRPTION));
        category.setCreated_at(parseDate(jsonObject.getString(KEY_CREATED_AT)));
        category.setUpdated_at(parseDate(jsonObject.getString(KEY_UPDATED_AT)));

        return category;
    }


    // Méthode qui récupère la date depuis le fichier json
    private static Date parseDate(String dateString) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

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
