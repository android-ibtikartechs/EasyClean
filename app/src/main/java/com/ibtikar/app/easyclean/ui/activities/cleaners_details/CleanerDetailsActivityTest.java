package com.ibtikar.app.easyclean.ui.activities.cleaners_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.ui_utilities.GallerySliderAdapter;
import com.klinker.android.sliding.MultiShrinkScroller;
import com.klinker.android.sliding.SlidingActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CleanerDetailsActivityTest extends SlidingActivity {
    ArrayList<String> slider_image_list = new ArrayList<>();
    GallerySliderAdapter sliderStartAdapter;
    @BindView(R.id.page_slider)
    ViewPager viewPager;

    @BindView(R.id.image_page_dots)
    LinearLayout loutDots;
    private TextView[] dots;
    @Override
    public void init(Bundle savedInstanceState) {

        //setImage(R.drawable.test_lundary);
        // setTitle("مغسلة");

        setPrimaryColors(
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.colorPrimary)
        );
        setContent(R.layout.activity_cleaner_details_test);
        setHeaderContent(R.layout.view_custom_header);

        ButterKnife.bind(this);
        initSlider();
        addBottomDots(0);
    }

    @Override
    protected void configureScroller(MultiShrinkScroller scroller) {
        super.configureScroller(scroller);
        scroller.setIntermediateHeaderHeightRatio(1);
    }

    public void initSlider() {
        addBottomDots(0);

        slider_image_list = new ArrayList<>();

        slider_image_list.add("http://newlearninginstitute.org/wp-content/uploads/2018/03/1.jpg");
        slider_image_list.add("https://scstylecaster.files.wordpress.com/2014/10/dry_cleaning_feature.jpg");
        slider_image_list.add("http://dcscofvistaridge.com/wp-content/uploads/2015/11/shutterstock_146426759-slide.jpg");
        slider_image_list.add("http://www.idealbridal.net/cm/dpl/images/create/wedding_dresses.jpg");


        sliderStartAdapter = new GallerySliderAdapter(this, slider_image_list);
        viewPager.setAdapter(sliderStartAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void addBottomDots(int currentPage) {
        dots = new TextView[slider_image_list.size()];

        loutDots.removeAllViews();
        loutDots.setPadding(0, 0, 0, 20);
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#ffffff"));
            loutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#0b94c8"));
    }



    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CleanerDetailsActivityTest.class);
        return intent;
    }
}
