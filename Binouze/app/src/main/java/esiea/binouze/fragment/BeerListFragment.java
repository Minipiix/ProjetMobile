package esiea.binouze.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import esiea.binouze.R;
import esiea.binouze.adapter.BeerAdapter;
import esiea.binouze.model.Beer;
import esiea.binouze.services.GetDataService;
import esiea.binouze.services.ParserJsonService;


public class BeerListFragment extends ListFragment {

    // Param keys
    public final static String PARAM_SORT_TYPE = "sort_type";
    public final static String PARAM_CATEGORY_ID = "category_id";

    /// sort types
    public final static String SORT_TYPE_CATEGORY = "sort_type_category";

    private Beer[] beers;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String sortType = null;
        Integer category_id = null;

        // get sort type from params
        Bundle bundle = getArguments();
        if (bundle != null) {
            sortType = bundle.getString(PARAM_SORT_TYPE);
            category_id = bundle.getInt(PARAM_CATEGORY_ID);
        }

        // get beer list
        if (SORT_TYPE_CATEGORY.equals(sortType)) {
            getBeersByCategory(category_id);
        }

        if (beers != null){
            BeerAdapter adapter = new BeerAdapter(getActivity(), R.layout.beer_list_row, beers);
            setListAdapter(adapter);
        }
    }

    private void getBeersByCategory(Integer category_id) {

        List<Beer> selectedBeers = new ArrayList<>();

        if(category_id == null) {
            getAllBeers();
        } else {
            String jsonValues = GetDataService.getBieres(getActivity());
            List<Beer> allBeers = ParserJsonService.getBeersListFromJson(jsonValues);
            for (Beer beer : allBeers) {
                if (beer.getCategory_id() != null && beer.getCategory_id().equals(category_id)){
                    selectedBeers.add(beer);
                }
            }
        }

        beers = new Beer[selectedBeers.size()];
        selectedBeers.toArray(beers);
    }

    private void getAllBeers() {

        String jsonValues = GetDataService.getBieres(getActivity());
        List<Beer> allBeers = ParserJsonService.getBeersListFromJson(jsonValues);

        beers = new Beer[allBeers.size()];
        allBeers.toArray(beers);
    }
}