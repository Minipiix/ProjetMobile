package esiea.binouze.fragment;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import esiea.binouze.model.Category;
import esiea.binouze.services.GetDataService;
import esiea.binouze.services.ParserJsonService;

public class CategoryFragment extends ListFragment {

    private List<String> values;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String jsonValues = GetDataService.getCategories(getActivity());
        List<Category> categories = ParserJsonService.getCategoriesListFromJson(jsonValues);

        values = new ArrayList<>();
        for (Category category : categories) {
            values.add(category.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
