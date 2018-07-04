package com.ibtikar.app.easyclean.ui.fragments.pricing;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.models.Category;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PricingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PricingFragment extends Fragment {
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


    public PricingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment PricingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PricingFragment newInstance() {
        PricingFragment fragment = new PricingFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pricing, container, false);
        return rootView;
    }

}
