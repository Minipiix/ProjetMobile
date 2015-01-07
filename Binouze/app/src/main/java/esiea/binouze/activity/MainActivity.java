package esiea.binouze.activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import esiea.binouze.fragment.BeerListFragment;
import esiea.binouze.fragment.FragmentTabListener;
import esiea.binouze.R;
import esiea.binouze.fragment.BeerGameFragment;
import esiea.binouze.fragment.CategoryFragment;
import esiea.binouze.fragment.CountryFragment;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        createTabs();

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

    private void createTabs() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab = actionBar.newTab().setText(R.string.menu_category).setTabListener(new FragmentTabListener<CategoryFragment>(this, "category", CategoryFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText(R.string.menu_country).setTabListener(new FragmentTabListener<CountryFragment>(this, "country", CountryFragment.class));
        actionBar.addTab(tab);

        Bundle args = new Bundle();
        args.putString(BeerListFragment.PARAM_SORT_TYPE, BeerListFragment.SORT_TYPE_TOP_5);
        tab = actionBar.newTab().setText(R.string.menu_top5).setTabListener(new FragmentTabListener<BeerListFragment>(this, "top5", BeerListFragment.class, args));
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText(R.string.menu_game).setTabListener(new FragmentTabListener<BeerGameFragment>(this, "beerGame", BeerGameFragment.class));
        actionBar.addTab(tab);

    }

    private void displayAboutPopup() {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("About");
        alertDialog.setMessage("Powered by Minipiix & Potetoes");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}
