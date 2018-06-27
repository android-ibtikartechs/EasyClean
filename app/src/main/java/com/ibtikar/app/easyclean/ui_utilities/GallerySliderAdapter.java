package com.ibtikar.app.easyclean.ui_utilities;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ibtikar.app.easyclean.R;

import java.util.ArrayList;

public class GallerySliderAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> image_arraylist;
    private LayoutInflater layoutInflater;

    public GallerySliderAdapter(Context context, ArrayList<String> image_arraylist) {
        this.context = context;
        this.image_arraylist = image_arraylist;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slider_start_layout, container, false);
        ImageView im_slider = view.findViewById(R.id.im_slider);
        //im_slider.setImageResource(image_arraylist.get(position));
        if(!(image_arraylist.get(position).equals("")|| image_arraylist.get(position)==null))
        {
            Glide.with(context)
                    .load(image_arraylist.get(position)).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(im_slider);
        }
        im_slider.setScaleType(ImageView.ScaleType.FIT_XY);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return image_arraylist.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
