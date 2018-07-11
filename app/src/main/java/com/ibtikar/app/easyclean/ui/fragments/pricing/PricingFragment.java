package com.ibtikar.app.easyclean.ui.fragments.pricing;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.ui.fragments.list_prices_fragment.ListPricesFragment;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.ibtikar.app.easyclean.ui_utilities.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PricingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PricingFragment extends Fragment implements PricingMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Handler mHandler;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String cleanerId;

    @BindView(R.id.main_tabLayout)
    TabLayout tabLayout;

    PricingPresenter presenter;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    ViewPagerAdapter adapter;


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
    public static PricingFragment newInstance(String id) {
        PricingFragment fragment = new PricingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cleanerId = getArguments().getString(ARG_PARAM1);
        }
        mHandler = new Handler(Looper.getMainLooper());

        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new PricingPresenter(dataManager);
        presenter.onAttach(this);
        presenter.getCategories(cleanerId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pricing, container, false);
        ButterKnife.bind(this,rootView);
        viewPager.setOffscreenPageLimit(4);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }



    private void setupViewPager() {
        adapter = new ViewPagerAdapter (getChildFragmentManager());
        viewPager.setAdapter(adapter);
    }


    @Override
    public void addCategoryTab(final String title, final int tabIndex) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout tab = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.view_custom_tab_title, null);
                ((CustomFontTextView)tab.findViewById(R.id.tab)).setText(title);
                tabLayout.getTabAt(tabIndex).setCustomView(tab);
            }
        });

    }

    @Override
    public void addCategoryFragment(final String id) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapter.addFragment(ListPricesFragment.newInstance(id, cleanerId), "category");
            }
        });
    }
}
