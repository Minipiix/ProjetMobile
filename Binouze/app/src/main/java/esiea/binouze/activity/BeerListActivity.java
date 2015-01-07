package esiea.binouze.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import esiea.binouze.R;
import esiea.binouze.fragment.BeerListFragment;

/**
 * Created by Quentin on 07/01/2015.
 */
public class BeerListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setBeerListFragment();
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

    @Override
     public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.beer_list_layout);
    }

    public void setBeerListFragment() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();

        getSupportFragmentManager().popBackStack();

        Fragment fragment = new BeerListFragment();
        fragment.setArguments(getIntent().getExtras());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.beer_list_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }


}
