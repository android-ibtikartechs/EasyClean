package com.ibtikar.app.easyclean.ui.fragments.list_prices_fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.adapters.PricesListAdapter;
import com.ibtikar.app.easyclean.data.models.PricingItem;
import com.ibtikar.app.easyclean.ui.fragments.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPricesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPricesFragment extends BaseFragment implements ListPriceMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.lv_prices)
    ListView lvCategories;

    PricesListAdapter pricesAdapter;

    ListPricePresenter presenter;

    private Handler mHandler;

    ArrayList<PricingItem> catList;


    // TODO: Rename and change types of parameters
    private String categoryId;
    private String cleanerId;


    public ListPricesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListPricesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListPricesFragment newInstance(String categoryId, String cleanerId) {
        ListPricesFragment fragment = new ListPricesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, categoryId);
        args.putString(ARG_PARAM2, cleanerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getString(ARG_PARAM1);
            cleanerId = getArguments().getString(ARG_PARAM2);
        }

        mHandler = new Handler(Looper.getMainLooper());

        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new ListPricePresenter(dataManager);
        presenter.onAttach(this);

        presenter.getListPrices(cleanerId, categoryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_prices, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void setupPriceList(final ArrayList<PricingItem> categoriesList) {
        this.catList =categoriesList;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                lvCategories.setAdapter(null);
                pricesAdapter = new PricesListAdapter(getActivity(),categoriesList);
                lvCategories.setAdapter(pricesAdapter);
            }
        });
    }
}
