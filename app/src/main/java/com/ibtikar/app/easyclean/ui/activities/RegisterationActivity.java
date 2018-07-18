package com.ibtikar.app.easyclean.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.ui.fragments.LoginFragment;
import com.ibtikar.app.easyclean.ui.fragments.signup.SignUpFragment;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.ibtikar.app.easyclean.ui_utilities.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_main)
    Toolbar mainToolbar;

    @BindView(R.id.main_tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        ButterKnife.bind(this);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabs();
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SignUpFragment.newInstance("any", "any"),"ServiceTypeFragment");
        adapter.addFragment(LoginFragment.newInstance("any", "any"),"ServiceTypeFragment");
        viewPager.setAdapter(adapter);
    }

    private void setupTabs() {
        for (int i = 0; i < 2; i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.view_custom_tab_title, null);

            switch (i) {
                case 0:
                    //tab.setText("الرئيسية");
                    ((CustomFontTextView) tab.findViewById(R.id.tab)).setText(R.string.sign_up);
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);


                    break;
                case 1:
                    //tab.setText("حسابي");
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.myacoount1, 0, 0);
                    ((CustomFontTextView) tab.findViewById(R.id.tab)).setText(R.string.login);

                    break;
            }
            tabLayout.getTabAt(i).setCustomView(tab);
            ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i).setSoundEffectsEnabled(false);
        }
    }
}
