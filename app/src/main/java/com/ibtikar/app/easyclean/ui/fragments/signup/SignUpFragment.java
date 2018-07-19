package com.ibtikar.app.easyclean.ui.fragments.signup;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.CleanerListAdapter;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.models.City;
import com.ibtikar.app.easyclean.data.models.Destrict;
import com.ibtikar.app.easyclean.ui.fragments.base.BaseFragment;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends BaseFragment implements SignUpMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.editText)
    EditText etName;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_re_password)
    EditText etRePassword;

    @BindView(R.id.et_email)
    EditText etEmail;


    @BindView(R.id.cities_spin)
    Spinner citiesSpinner;

    @BindView(R.id.districts_spin)
    Spinner destrictsSpin;


    @BindView(R.id.lout_main_signup)
    NestedScrollView loutMain;

    @BindView(R.id.button)
    Button btnSignUp;

    @BindView(R.id.lout_btn_login_facebook)
    CardView btnLoginFacebook;

    @BindView(R.id.lout_btn_login_google)
    CardView btnLoginGoogle;

    @BindView(R.id.lout_btn_login_twitter)
    CardView btnLoginTwitter;

    SignUpPresenter presenter;

    Handler handler;

    String destrictId;



    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        presenter = new SignUpPresenter(dataManager);
        presenter.onAttach(this);

        presenter.loadCities();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> data = new ArrayList<>();
                data.add(etName.getText().toString());
                data.add(etPassword.getText().toString());
                data.add(etRePassword.getText().toString());
                data.add(etEmail.getText().toString());
                data.add(destrictId);
                presenter.signup(data);
            }
        });
    }

    @Override
    public void showToast(final String msg, int flag_text_or_int) {

        if (flag_text_or_int == StaticValues.FLAG_TOAST_INT)
            Toast.makeText(getActivity(), Integer.valueOf(msg), Toast.LENGTH_SHORT).show();
        else
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            });


    }

    @Override
    public void setupCitiesSpinner(final ArrayList<City> objects) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                City country1 = new City();
                country1.setId(0);
                country1.setName("المحافظة");
                objects.add(0, country1);

                com.ibtikar.app.easyclean.data.adapters.SpinnerAdapter spinerAdapter = new com.ibtikar.app.easyclean.data.adapters.SpinnerAdapter(getActivity(), R.layout.spiner_item_layout, R.id.title, objects);
                citiesSpinner.setAdapter(spinerAdapter);
                citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println(objects.get(position).getName());

                        if (!objects.get(position).getId().equals("0")) {
                            if (position != 0)
                                presenter.loadDestricts(objects.get(position).getId());
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
    public void setupdestrictsSpinner(final ArrayList<Destrict> objects) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Destrict country1 = new Destrict();
                country1.setId(0);
                country1.setName("المنطقة");
                objects.add(0, country1);

                com.ibtikar.app.easyclean.data.adapters.SpinDestrictAdapter spinerAdapter = new com.ibtikar.app.easyclean.data.adapters.SpinDestrictAdapter(getActivity(), R.layout.spiner_item_layout, R.id.title, objects);
                destrictsSpin.setAdapter(spinerAdapter);
                destrictsSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println(objects.get(position).getName());

                        if (!objects.get(position).getId().equals("0")) {
                            if (position != 0)
                                destrictId = objects.get(position).getId();
                        }
                        else
                            destrictId = "";
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    @Override
    public void showReActivateSnackbar(String msg) {
        Snackbar snackbar = Snackbar
                .make(loutMain, msg, Snackbar.LENGTH_LONG)
                .setAction("إعادة الإرسال", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.resendActivation(etEmail.getText().toString());
                    }
                });

        snackbar.show();
    }

    @Override
    public void showActivationSentSnackBar() {
        Snackbar snackbar1 = Snackbar.make(loutMain, "تم إرسال رابط التفعيل على بريك الإلكتروني", Snackbar.LENGTH_SHORT);
        snackbar1.show();
    }

    @Override
    public void showSnackbarLogin() {
        Snackbar snackbar = Snackbar
                .make(loutMain, "هذا الحساب مسجل بالفعل قم بتسجيل الدخول", Snackbar.LENGTH_LONG);
        snackbar.show();
    }



}
