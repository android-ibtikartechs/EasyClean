package com.ibtikar.app.easyclean.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.models.PricingItem;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;

import java.util.ArrayList;

public class PricesListAdapter extends ArrayAdapter<PricingItem> {

    Context context;
    ViewHolder viewHolder;

    public PricesListAdapter(Context context, ArrayList<PricingItem> items)
    {
        super(context,0,items);
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final PricingItem itemModel = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_price_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = convertView.findViewById(R.id.customFontTextView);
            viewHolder.tvPrice = convertView.findViewById(R.id.customFontTextView2);
            viewHolder.loutMain = convertView.findViewById(R.id.main_lout);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        if (position % 2 != 0)
            viewHolder.loutMain.setBackgroundColor(context.getResources().getColor(R.color.white));

        viewHolder.tvTitle.setText(itemModel.getName());
        viewHolder.tvPrice.setText(itemModel.getPrice());

        return convertView;
    }

    public class ViewHolder {
        CustomFontTextView tvTitle;
        CustomFontTextView tvPrice;
        ConstraintLayout loutMain;
    }

}
