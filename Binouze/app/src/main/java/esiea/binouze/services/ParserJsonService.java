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
    private final static String KEY_URL = "url";
    private final static String KEY_THUMB = "thumb";
    private final static String KEY_CATEGORY_ID = "category_id";
    private final static String KEY_CATEGORY = "category";
    private final static String KEY_CREATED_AT = "created_at";
    private final static String KEY_UPDATED_AT = "updated_at";

    public static List<Beer> getBeersListFromJson(String json) {
        List<Beer> beers = new ArrayList<>();
        try {
        JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++)
            {
                Beer beer = getBiereFromJson(jsonArray.getString(i));
                beers.add(beer);
            }
        } catch (JSONException e) {
                Log.e("[BEER JSON PARSING]", "Impossible de parser les bières récuperées", e);
            }
        return beers;
    }

    // méthode qui récupère un objet Biere depuis un json
    public static Beer getBiereFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        Beer beer = new Beer();
        beer.setId(getIntegerFromJson(jsonObject, KEY_ID));
        beer.setName(getStringFromJson(jsonObject, KEY_NAME));
        beer.setDescription(getStringFromJson(jsonObject, KEY_DESCRPTION));
        beer.setBuveur(getStringFromJson(jsonObject, KEY_BUVEUR));
        beer.setNote(getStringFromJson(jsonObject, KEY_NOTE));
        beer.setNote_moyenne(getStringFromJson(jsonObject, KEY_NOTE_MOYENNE));
        beer.setNumber_of_notes(getStringFromJson(jsonObject, KEY_NUMBER_OF_NOTE));
        beer.setCategory_id(getIntegerFromJson(jsonObject, KEY_CATEGORY_ID));
        beer.setCategory(getStringFromJson(jsonObject, KEY_CATEGORY));
        beer.setCreated_at(getDateFromJson(jsonObject, KEY_CREATED_AT));
        beer.setUpdated_at(getDateFromJson(jsonObject, KEY_UPDATED_AT));

        JSONObject country = getJSONObjectFromJson(jsonObject, KEY_COUNTRY);
        if (country != null) {
            beer.setCountry_id(getIntegerFromJson(country, KEY_ID));
            beer.setCountry(getStringFromJson(country, KEY_NAME));
        }

        JSONObject image = getJSONObjectFromJson(jsonObject, KEY_IMAGE);
        if (image != null) {
            JSONObject image2 = getJSONObjectFromJson(image, KEY_IMAGE);
            if (image2 != null) {
                beer.setImage(getStringFromJson(image2, KEY_URL));

                JSONObject thumb = getJSONObjectFromJson(image2, KEY_THUMB);
                if (thumb != null) {
                    beer.setThumb(getStringFromJson(thumb, KEY_URL));
                }
            }
        }

        return beer;
    }

    public static List<Country> getCountriesListFromJson(String json) {
        List<Country> countries = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++)
            {
                Country country = getCountryFromJson(jsonArray.getString(i));
                countries.add(country);
            }
        } catch (JSONException e) {
            Log.e("[CATEGORY JSON PARSING]", "Impossible de parser les pays récuperées", e);
        }
        return countries;
    }

    // méthode qui récupère un objet Pays depuis un json
    public static Country getCountryFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        Country country = new Country();
        country.setId(getIntegerFromJson(jsonObject, KEY_ID));
        country.setName(getStringFromJson(jsonObject, KEY_NAME));
        country.setImage(getStringFromJson(jsonObject, KEY_IMAGE));

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
        category.setId(getIntegerFromJson(jsonObject, KEY_ID));
        category.setName(getStringFromJson(jsonObject, KEY_NAME));
        category.setDescription(getStringFromJson(jsonObject, KEY_DESCRPTION));
        category.setCreated_at(getDateFromJson(jsonObject, KEY_CREATED_AT));
        category.setUpdated_at(getDateFromJson(jsonObject, KEY_UPDATED_AT));

        return category;
    }

    private static String getStringFromJson(JSONObject jsonObject, String key) {
        String result = null;
        try {
            result = jsonObject.getString(key);
        } catch (JSONException e) {
            Log.d("[PARSE JSON]", "Cannot find string for key" + key);
        }
        return result;
    }

    private static Integer getIntegerFromJson(JSONObject jsonObject, String key) {
        Integer result = null;
        try {
            result = jsonObject.getInt(key);
        } catch (JSONException e) {
            Log.d("[PARSE JSON]", "Cannot find Integer for key" + key);
        }
        return result;
    }

    private static JSONObject getJSONObjectFromJson(JSONObject jsonObject, String key) {
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject(key);
        } catch (JSONException e) {
            Log.d("[PARSE JSON]", "Cannot find object for key" + key);
        }
        return result;
    }

    // Méthode qui récupère la date depuis le fichier json
    private static Date getDateFromJson(JSONObject jsonObject, String key) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date result = null;
        try {
            String dateString = jsonObject.getString(key);
            if (dateString != null) {
                result = simpleDateFormat.parse(dateString);
            }
        } catch (ParseException e) {
            Log.d("[JSON PARSER]", "Error lors du parsing de la date", e);
        } catch (JSONException e) {
            Log.d("[PARSE JSON]", "Cannot find date for key" + key);
        }
        return result;

    }
}
