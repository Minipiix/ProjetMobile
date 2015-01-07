package esiea.binouze.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import esiea.binouze.activity.BeerListActivity;
import esiea.binouze.R;
import esiea.binouze.adapter.CategoryAdapter;
import esiea.binouze.model.Category;
import esiea.binouze.services.GetDataService;
import esiea.binouze.services.ParserJsonService;

public class CategoryFragment extends ListFragment {

    private Category[] categories;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String jsonValues = GetDataService.getCategories(getActivity());
        List<Category> categoriesList = ParserJsonService.getCategoriesListFromJson(jsonValues);

        categories = new Category[categoriesList.size()];
        categoriesList.toArray(categories); // fill the array
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CategoryAdapter adapter = new CategoryAdapter(getActivity(), R.layout.category_list_row, categories);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Category currentCategory = categories[position];

        Intent myIntent = new Intent(getActivity(), BeerListActivity.class);
        myIntent.putExtra(BeerListFragment.PARAM_SORT_TYPE, BeerListFragment.SORT_TYPE_CATEGORY);
        myIntent.putExtra(BeerListFragment.PARAM_CATEGORY_ID, currentCategory.getId());
        startActivity(myIntent);
    }
}
