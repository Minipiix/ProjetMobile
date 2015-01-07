package esiea.binouze;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import esiea.binouze.adapter.BeerAdapter;
import esiea.binouze.adapter.CategoryAdapter;
import esiea.binouze.fragment.BeerGameFragment;
import esiea.binouze.fragment.CategoryFragment;
import esiea.binouze.fragment.CountryFragment;
import esiea.binouze.fragment.TopFiveFragment;
import esiea.binouze.model.Beer;
import esiea.binouze.model.Category;
import esiea.binouze.services.GetDataService;
import esiea.binouze.services.ParserJsonService;


public class BeerListActivity extends ListActivity {

    // extras
    public final static String EXTRA_SORT_TYPE = "sort_type";
    public final static String EXTRA_CATEGORY_ID = "category_id";

    /// sort types
    public final static String SORT_TYPE_CATEGORY = "sort_type_category";

    private Beer[] beers;
    private Intent currentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentIntent = getIntent();

        String jsonValues = GetDataService.getBieres(this);
        List<Beer> allBeers = ParserJsonService.getBeersListFromJson(jsonValues);

        String sortType = currentIntent.getStringExtra(EXTRA_SORT_TYPE);
        if(sortType == null) {
            getAllBeers(allBeers);
        } else if(SORT_TYPE_CATEGORY.equals(sortType)) {
            getBeersByCategory(allBeers);
        }

        BeerAdapter adapter = new BeerAdapter(this, R.layout.beer_list_row, beers);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
        } else if(id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getBeersByCategory(List<Beer> allBeers) {
        List<Beer> selectedBeers = new ArrayList<>();

        Integer category_id = currentIntent.getIntExtra(EXTRA_CATEGORY_ID, -1);
        if(category_id == null) {
            getAllBeers(allBeers);
        } else {
            for (Beer beer : allBeers) {
                if (beer.getCategory_id() != null && beer.getCategory_id().equals(category_id)){
                    selectedBeers.add(beer);
                }
            }
        }

        beers = new Beer[selectedBeers.size()];
        selectedBeers.toArray(beers);
    }

    private void getAllBeers(List<Beer> allBeers) {
        beers = new Beer[allBeers.size()];
        allBeers.toArray(beers);
    }
}
