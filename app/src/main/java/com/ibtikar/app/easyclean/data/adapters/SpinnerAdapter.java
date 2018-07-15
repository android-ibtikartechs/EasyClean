package com.ibtikar.app.easyclean.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.models.City;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<City> {
    LayoutInflater flater;
    public SpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ArrayList<City> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return rowview(convertView, position, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position, parent);
    }


    private View rowview(View convertView, int position, ViewGroup parent) {

        final City rowItem = getItem(position);

        viewHolder holder;
        View rowview = convertView;
        if (rowview == null) {

            holder = new viewHolder();
            flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = flater.inflate(R.layout.spiner_item_layout, parent, false);

            holder.title = rowview.findViewById(R.id.title);
            rowview.setTag(holder);
        } else {
            holder = (viewHolder) rowview.getTag();
        }

        holder.title.setText(rowItem.getName());


        return rowview;
    }

    private class viewHolder {
        TextView title;
    }

}
