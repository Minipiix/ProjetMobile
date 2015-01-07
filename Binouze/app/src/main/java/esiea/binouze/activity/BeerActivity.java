package esiea.binouze.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

import esiea.binouze.R;
import esiea.binouze.model.Beer;
import esiea.binouze.services.AsyncImageRequestService;
import esiea.binouze.services.GetDataService;
import esiea.binouze.services.ParserJsonService;

/**
 * Created by Lucile on 07/01/2015.
 */
public class BeerActivity extends ActionBarActivity {

    public final static String PARAM_ID = "id";

    private Beer beer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleIntent(Intent intent) {
        setContentView(R.layout.beer_page_layout);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getBeerByName(query);
        } else {
            Integer id = intent.getIntExtra(PARAM_ID, -1);
            getBeerById(id);
        }

        fillPage();
    }

    private void getBeerByName(String query) {
        String jsonValues = GetDataService.getBieres(this);
        List<Beer> allBeers = ParserJsonService.getBeersListFromJson(jsonValues);

        for (Beer currentBeer : allBeers) {
            if(currentBeer.getName().equalsIgnoreCase(query)) {
                beer = currentBeer;
            }
        }
    }

    private void getBeerById(Integer id) {
        String jsonValues = GetDataService.getBiere(this, id);
        try {
            beer = ParserJsonService.getBeereFromJson(jsonValues);
        } catch (JSONException e) {
            Log.e("[BEER JSON PARSING]", "Impossible de parser la bière", e);
        }
    }

    private void fillPage() {

        // set beer name
        TextView name = (TextView) findViewById(R.id.beer_name);
        name.setText(beer.getName());

        // set beer category
        TextView category = (TextView) findViewById(R.id.beer_category_content);
        category.setText(beer.getCategory());

        // set beer country
        TextView country = (TextView) findViewById(R.id.beer_country_content);
        country.setText(beer.getCountry());

        // set beer note moyenne
        TextView note_moyenne = (TextView) findViewById(R.id.beer_note_moyenne_content);
        note_moyenne.setText(beer.getNote_moyenne());

        // set beer image
        ImageView image = (ImageView) findViewById(R.id.beer_image_content);
        image.setImageBitmap(getImage(beer.getImage()));

        // set beer description
        TextView description = (TextView) findViewById(R.id.beer_description_content);
        description.setText(beer.getDescription());
    }

    private Bitmap getImage(String url) {
        AsyncImageRequestService asyncImageRequestService = new AsyncImageRequestService();
        asyncImageRequestService.execute(url);

        try {
            return asyncImageRequestService.get().get(url);
        } catch (InterruptedException e) {
            Log.w("[GET IMAGE]", "Erreur lors de la récuperation des images", e);
        } catch (ExecutionException e) {
            Log.w("[GET IMAGE]", "Erreur lors de la récuperation des images", e);
        }
        return null;
    }

}
