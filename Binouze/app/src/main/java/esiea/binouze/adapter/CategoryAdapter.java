package esiea.binouze.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import esiea.binouze.R;
import esiea.binouze.model.Category;

public class CategoryAdapter extends ArrayAdapter<Category> {

    Context context;
    int layoutResourceId;
    Category[] categories;

    public CategoryAdapter(Context context, int layoutResourceId, Category[] categories) {
        super(context, layoutResourceId, categories);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.categories = categories;
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
            holder.name = (TextView)row.findViewById(R.id.category_row_name);
            holder.description = (TextView)row.findViewById(R.id.category_row_description);

            row.setTag(holder);
        }
        else
        {
            holder = (CategoryHolder)row.getTag();
        }

        Category category = categories[position];
        holder.name.setText(category.getName());
        if(category.getDescription() != null && !"null".equals(category.getDescription())) {
            holder.description.setText(category.getDescription());
        }

        return row;
    }

    static class CategoryHolder
    {
        TextView name;
        TextView description;
    }

}
