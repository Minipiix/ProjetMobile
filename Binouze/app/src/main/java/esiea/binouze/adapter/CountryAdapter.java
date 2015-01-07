package esiea.binouze.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import esiea.binouze.R;
import esiea.binouze.model.Beer;
import esiea.binouze.model.Category;
import esiea.binouze.model.Country;
import esiea.binouze.services.AsyncImageRequestService;

public class CountryAdapter extends ArrayAdapter<Country> {

    Context context;
    int layoutResourceId;
    Country[] countries;
    Map<String, Bitmap> images;

    public CountryAdapter(Context context, int layoutResourceId, Country[] countries) {
        super(context, layoutResourceId, countries);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.countries = countries;

        List<String> urls = new ArrayList<>();
        for (Country country : countries) {
            if(country.getImage() != null && !country.getImage().equals("null") ) {
                urls.add(country.getImage());
            }
        }

        String[] urlsArray = new String[urls.size()];
        urls.toArray(urlsArray);

        AsyncImageRequestService asyncImageRequestService = new AsyncImageRequestService();
        asyncImageRequestService.execute(urlsArray);

        try {
            images = asyncImageRequestService.get();
        } catch (InterruptedException e) {
            Log.e("[GET IMAGE]", "Erreur lors de la récuperation des images", e);
        } catch (ExecutionException e) {
            Log.e("[GET IMAGE]", "Erreur lors de la récuperation des images", e);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CountryHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CountryHolder();
            holder.name = (TextView)row.findViewById(R.id.country_row_name);
            holder.flag = (ImageView)row.findViewById(R.id.country_row_img);

            row.setTag(holder);
        }
        else
        {
            holder = (CountryHolder)row.getTag();
        }

        Country country = countries[position];

        holder.flag.setImageBitmap(images.get(country.getImage()));
        holder.name.setText(country.getName());

        return row;
    }

    static class CountryHolder
    {
        ImageView flag;
        TextView name;
    }

}
