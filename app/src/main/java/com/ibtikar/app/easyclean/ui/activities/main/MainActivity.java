package com.ibtikar.app.easyclean.ui.activities.main;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.ui.fragments.HomeFragment;
import com.ibtikar.app.easyclean.ui.fragments.OrdersFragment;
import com.ibtikar.app.easyclean.ui.fragments.ProfileFragment;
import com.ibtikar.app.easyclean.ui.fragments.SubscribeFragment;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.ibtikar.app.easyclean.ui_utilities.NonSwipeableViewPager;
import com.ibtikar.app.easyclean.ui_utilities.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.relativeLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_main)
    NonSwipeableViewPager viewPager;

    ViewPagerAdapter adapter;
    private int[] tabIcons = {R.drawable.home1, R.drawable.myacoount1, R.drawable.coupon1, R.drawable.cart1};
    private int[] tabIconsSelected = {R.drawable.home, R.drawable.myacoount, R.drawable.coupon, R.drawable.cart};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setOffscreenPageLimit(4);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < 4; i++) {
            CustomFontTextView tab = (CustomFontTextView) LayoutInflater.from(this).inflate(R.layout.view_custome_tab, null);
            switch (i)
            {
                case 0 :
                    tab.setText("الرئيسية");
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);

                    break;
                case 1 :
                    tab.setText("حسابي");
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.myacoount1, 0, 0);
                    break;
                case 2 :
                    tab.setText("اشتراكات");
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.coupon1, 0, 0);
                    break;
                case 3 :
                    tab.setText("طلباتي");
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cart1, 0, 0);
                    break;

            }

            tabLayout.getTabAt(i).setCustomView(tab);

        }

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab.setIcon(tabIconsSelected[tab.getPosition()]);
                ((CustomFontTextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setCompoundDrawablesWithIntrinsicBounds(0, tabIconsSelected[tab.getPosition()], 0, 0);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((CustomFontTextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[tab.getPosition()], 0, 0);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(NonSwipeableViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HomeFragment.newInstance(),"Home Fragment");
        adapter.addFragment(ProfileFragment.newInstance(), "Profile Fragment");
        adapter.addFragment(SubscribeFragment.newInstance(),"Subscribe Fragment");
        adapter.addFragment(OrdersFragment.newInstance(), "Orders Fragment");

        viewPager.setAdapter(adapter);
    }
}
