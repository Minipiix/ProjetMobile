package esiea.binouze.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import esiea.binouze.R;
import esiea.binouze.activity.BeerListActivity;
import esiea.binouze.adapter.CategoryAdapter;
import esiea.binouze.adapter.CountryAdapter;
import esiea.binouze.model.Category;
import esiea.binouze.model.Country;
import esiea.binouze.services.GetDataService;
import esiea.binouze.services.ParserJsonService;

public class CountryFragment extends ListFragment {

    private Country[] countries;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        String jsonValues = GetDataService.getCountries(getActivity());
        List<Country> countriesList = ParserJsonService.getCountriesListFromJson(jsonValues);

        countries = new Country[countriesList.size()];
        countriesList.toArray(countries);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CountryAdapter adapter = new CountryAdapter(getActivity(), R.layout.country_list_row, countries);
        setListAdapter(adapter);

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Country currentCountry = countries[position];

        Intent myIntent = new Intent(getActivity(), BeerListActivity.class);
        myIntent.putExtra(BeerListFragment.PARAM_SORT_TYPE, BeerListFragment.SORT_TYPE_COUNTRY);
        myIntent.putExtra(BeerListFragment.PARAM_ID, currentCountry.getId());
        startActivity(myIntent);
    }

}
