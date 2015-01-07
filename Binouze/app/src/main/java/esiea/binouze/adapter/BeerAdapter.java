package esiea.binouze.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import esiea.binouze.R;
import esiea.binouze.model.Beer;
import esiea.binouze.model.Category;
import esiea.binouze.services.AsyncHttpRequestService;
import esiea.binouze.services.AsyncImageRequestService;

public class BeerAdapter extends ArrayAdapter<Beer> {

    private final static String IMAGE_URL = "http://www.binouze.fabrigli.fr";

    Context context;
    int layoutResourceId;
    Beer[] beers;

    public BeerAdapter(Context context, int layoutResourceId, Beer[] beers) {
        super(context, layoutResourceId, beers);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.beers = beers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CategoryHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CategoryHolder();
            holder.image = (ImageView)row.findViewById(R.id.list_beer_img);
            holder.name = (TextView)row.findViewById(R.id.beer_row_description);

            row.setTag(holder);
        }
        else
        {
            holder = (CategoryHolder)row.getTag();
        }

        Beer beer = beers[position];
        holder.name.setText(beer.getName());

        AsyncImageRequestService asyncImageRequestService = new AsyncImageRequestService();
        asyncImageRequestService.execute(IMAGE_URL + beer.getThumb());
        try {
            holder.image.setImageBitmap(asyncImageRequestService.get());
        } catch (InterruptedException e) {
            Log.e("[GET IMAGE]", "Erreur lors de la récuperation de l'image", e);
        } catch (ExecutionException e) {
            Log.e("[GET IMAGE]", "Erreur lors de la récuperation de l'image", e);
        }

        return row;
    }

    static class CategoryHolder
    {
        ImageView image;
        TextView name;
    }

}
