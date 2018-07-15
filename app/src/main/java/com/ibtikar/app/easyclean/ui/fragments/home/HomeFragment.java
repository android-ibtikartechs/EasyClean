package com.ibtikar.app.easyclean.ui.fragments.home;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.CleanerListAdapter;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.models.City;
import com.ibtikar.app.easyclean.data.models.CleanerItemModel;
import com.ibtikar.app.easyclean.ui.activities.Main2Activity;
import com.ibtikar.app.easyclean.ui.activities.cleaners_details.CleanerDetailsActivity;
import com.ibtikar.app.easyclean.ui.activities.cleaners_details.CleanerDetailsActivityTest;
import com.ibtikar.app.easyclean.ui.fragments.base.BaseFragment;
import com.ibtikar.app.easyclean.ui.fragments.search.SearchDialogFragment;
import com.ibtikar.app.easyclean.ui_utilities.CustomRecyclerView;
import com.ibtikar.app.easyclean.utilities.PaginationAdapterCallback;
import com.ibtikar.app.easyclean.utilities.PaginationScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeMvpView, CleanerListAdapter.customButtonListener, PaginationAdapterCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CleanerListAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    private static final int PAGE_START = 1;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 20;
    private Integer currentPage = PAGE_START;

    private ArrayList<CleanerItemModel> arrayList;

    HomePresenter presenter;

    Handler handler;

    @BindView(R.id.rv_cleaners_list)
    CustomRecyclerView rvCleaners;
    @BindView(R.id.btn_search)
    ImageView btnSearch;

    @BindView(R.id.cities_spin)
    Spinner citiesSpin;



    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
       /* Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args); */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        handler = new Handler(Looper.getMainLooper());
        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new HomePresenter(dataManager);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);
        arrayList = new ArrayList<>();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getChildFragmentManager();
                SearchDialogFragment searchDialogFragment = new SearchDialogFragment();

                searchDialogFragment.show(fm, "search_dialog");

         //startActivity(new Intent(getActivity(), Main2Activity.class));
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCleaners.setLayoutManager(linearLayoutManager);
        rvCleaners.setHasFixedSize(true);

        rvCleaners.setItemViewCacheSize(20);
        rvCleaners.setDrawingCacheEnabled(true);
        rvCleaners.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        populatRecyclerView();
        implementScrolListener();

        currentPage = PAGE_START;
        presenter.loadCities();
        presenter.loadFirstPage();
        super.onViewCreated(view, savedInstanceState);
    }

    private void populatRecyclerView() {
        adapter = new CleanerListAdapter(getActivity(), arrayList);
        adapter.setCustomButtonListner(this);
        adapter.setPagingAdapterCallback(this);
        rvCleaners.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void implementScrolListener()
    {
        rvCleaners.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {


            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                presenter.loadNextPage(currentPage);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public String fetchErrorMessage() {
        return null;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void setLastPageTrue() {
        isLastPage = true;
    }

    @Override
    public void addMoreToAdapter(final ArrayList<CleanerItemModel> list) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.addAll(list);
            }
        });
    }

    @Override
    public void addLoadingFooter() {

    }

    @Override
    public void removeLoadingFooter() {

    }

    @Override
    public void showRetryAdapter() {

    }

    @Override
    public void setIsLoadingFalse() {

    }

    @Override
    public void setupSpinner(final ArrayList<City> objects) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                City country1 = new City();
                country1.setId(0);
                country1.setName("اختر المدينة");
                objects.add(0, country1);

                com.ibtikar.app.easyclean.data.adapters.SpinnerAdapter spinerAdapter = new com.ibtikar.app.easyclean.data.adapters.SpinnerAdapter(getActivity(), R.layout.spiner_item_layout, R.id.title, objects);
                citiesSpin.setAdapter(spinerAdapter);
                citiesSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println(objects.get(position).getName());

                        if (!objects.get(position).getId().equals("0")) {
                            if (position != 0)
                                presenter.search(objects.get(position).getId());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

    }

    @Override
    public void refreshListCleaners(final ArrayList<CleanerItemModel> cleanersList) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(cleanersList);
            }
        });

    }

    @Override
    public void onItemClickListner(String id) {
        getActivity().startActivity(CleanerDetailsActivity.getStartIntent(getActivity(),id));
    }

    @Override
    public void retryPageLoad() {

    }
}
