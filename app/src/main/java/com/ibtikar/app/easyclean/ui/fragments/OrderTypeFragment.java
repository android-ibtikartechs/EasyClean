package com.ibtikar.app.easyclean.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.ibtikar.app.easyclean.ui_utilities.ViewPagerAdapter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderTypeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.main_tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    ViewPagerAdapter adapter;

    public OrderTypeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment OrderTypeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderTypeFragment newInstance() {
        OrderTypeFragment fragment = new OrderTypeFragment();
      /*  Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_type, container, false);
        ButterKnife.bind(this, rootView);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabs();
        return rootView;
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(ServiceTypeFragment.newInstance(StaticValues.FLAG_NORMAL_SERVICE_TYPE, "any"),"ServiceTypeFragment");
        adapter.addFragment(ServiceTypeFragment.newInstance(StaticValues.FLAG_Fast_SERVICE_TYPE, "any"),"ServiceTypeFragment");
        viewPager.setAdapter(adapter);
    }
    private void setupTabs() {
        for (int i = 0; i < 2; i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.view_custom_tab_title, null);

            switch (i) {
                case 0:
                    //tab.setText("الرئيسية");
                    ((CustomFontTextView) tab.findViewById(R.id.tab)).setText(R.string.normal_service);
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);


                    break;
                case 1:
                    //tab.setText("حسابي");
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.myacoount1, 0, 0);
                    ((CustomFontTextView) tab.findViewById(R.id.tab)).setText(R.string.fast_service);

                    break;
            }
            tabLayout.getTabAt(i).setCustomView(tab);
            ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i).setSoundEffectsEnabled(false);
        }
    }
}
