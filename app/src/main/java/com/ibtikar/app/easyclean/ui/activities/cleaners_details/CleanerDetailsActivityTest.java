package com.ibtikar.app.easyclean.ui.activities.cleaners_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.ui.fragments.InfoFragment;
import com.ibtikar.app.easyclean.ui.fragments.OrderTypeFragment;
import com.ibtikar.app.easyclean.ui.fragments.pricing.PricingFragment;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.ibtikar.app.easyclean.ui_utilities.GallerySliderAdapter;
import com.ibtikar.app.easyclean.ui_utilities.NonSwipeableViewPager;
import com.ibtikar.app.easyclean.ui_utilities.ViewPagerAdapter;
import com.klinker.android.sliding.MultiShrinkScroller;
import com.klinker.android.sliding.SlidingActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CleanerDetailsActivityTest extends SlidingActivity {
    @BindView(R.id.relativeLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_main)
    NonSwipeableViewPager viewPager;
    ArrayList<String> slider_image_list = new ArrayList<>();
    GallerySliderAdapter sliderStartAdapter;
    @BindView(R.id.page_slider)
    ViewPager imagesSlider;
    ViewPagerAdapter adapter;
    @BindView(R.id.image_page_dots)
    LinearLayout loutDots;
    private TextView[] dots;

    private int[] tabIcons = {R.drawable.time1, R.drawable.info1, R.drawable.info1};
    private int[] tabIconsSelected = {R.drawable.time, R.drawable.info, R.drawable.info};

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

        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);



        for (int i = 0; i < 3; i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab_details, null);

            switch (i)
            {
                case 0 :
                    //tab.setText("الرئيسية");
                    ((CustomFontTextView)tab.findViewById(R.id.tab)).setText(R.string.order_type);
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);
                    ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(R.drawable.time1);


                    break;
                case 1 :
                    //tab.setText("حسابي");
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.myacoount1, 0, 0);
                    ((CustomFontTextView)tab.findViewById(R.id.tab)).setText(R.string.pricing);
                    ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(R.drawable.info1);
                    break;
                case 2 :
                    //tab.setText("اشتراكات");
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.coupon1, 0, 0);
                    ((CustomFontTextView)tab.findViewById(R.id.tab)).setText(R.string.info);
                    ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(R.drawable.info1);
                    break;

            }

            tabLayout.getTabAt(i).setCustomView(tab);
            ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i).setSoundEffectsEnabled(false);
            //((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i).setBackground(getResources().getDrawable(R.drawable.container_dropshadow));
        }


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //((CustomFontTextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setCompoundDrawablesWithIntrinsicBounds(0, tabIconsSelected[tab.getPosition()], 0, 0);
                ((ImageView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab_icon)).setImageResource(tabIconsSelected[tab.getPosition()]);
                //((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(tabIconsSelected[tab.getPosition()]);
                ((CustomFontTextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setTextColor(getResources().getColor(R.color.blue));
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //((CustomFontTextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[tab.getPosition()], 0, 0);
                ((ImageView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab_icon)).setImageResource(tabIcons[tab.getPosition()]);
                ((CustomFontTextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setTextColor(getResources().getColor(R.color.blue_more_white));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setCurrentItem(1);
    }

    @Override
    protected void configureScroller(MultiShrinkScroller scroller) {
        super.configureScroller(scroller);
        scroller.setIntermediateHeaderHeightRatio(1);
    }


    private void setupViewPager(NonSwipeableViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(OrderTypeFragment.newInstance(),"Order Type Fragment");
        //adapter.addFragment(PricingFragment.newInstance(), "Pricing Fragment");
        //adapter.addFragment(InfoFragment.newInstance(),"Info Fragment");

        viewPager.setAdapter(adapter);
    }



    public void initSlider() {
        addBottomDots(0);

        slider_image_list = new ArrayList<>();

        slider_image_list.add("http://newlearninginstitute.org/wp-content/uploads/2018/03/1.jpg");
        slider_image_list.add("https://scstylecaster.files.wordpress.com/2014/10/dry_cleaning_feature.jpg");
        slider_image_list.add("http://dcscofvistaridge.com/wp-content/uploads/2015/11/shutterstock_146426759-slide.jpg");
        slider_image_list.add("http://www.idealbridal.net/cm/dpl/images/create/wedding_dresses.jpg");


        //sliderStartAdapter = new GallerySliderAdapter(this, slider_image_list);
        imagesSlider.setAdapter(sliderStartAdapter);

        imagesSlider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
