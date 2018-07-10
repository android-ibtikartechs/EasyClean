package com.ibtikar.app.easyclean.ui.activities.cleaners_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.models.Category;
import com.ibtikar.app.easyclean.data.models.CleanerDetails;
import com.ibtikar.app.easyclean.data.models.Gallery;
import com.ibtikar.app.easyclean.ui.activities.base.BaseActivity;
import com.ibtikar.app.easyclean.ui.fragments.InfoFragment;
import com.ibtikar.app.easyclean.ui.fragments.OrderTypeFragment;
import com.ibtikar.app.easyclean.ui.fragments.home.HomePresenter;
import com.ibtikar.app.easyclean.ui.fragments.pricing.PricingFragment;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.ibtikar.app.easyclean.ui_utilities.GallerySliderAdapter;
import com.ibtikar.app.easyclean.ui_utilities.NonSwipeableViewPager;
import com.ibtikar.app.easyclean.ui_utilities.ViewPagerAdapter;
import com.ibtikar.app.easyclean.utilities.StaticValues;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ooo.oxo.library.widget.PullBackLayout;

public class CleanerDetailsActivity extends BaseActivity implements CleanerDetailsMvpView {

    ArrayList<Gallery> slider_image_list = new ArrayList<>();
    @BindView(R.id.page_slider)
    ViewPager imagesSlider;


    @BindView(R.id.tv_mini_charge)
    CustomFontTextView tvMinCharge;

    @BindView(R.id.tv_cleaner_name)
    CustomFontTextView tvCleanerName;

    @BindView(R.id.im_rate)
    ImageView imRate;

    @BindView(R.id.relativeLayout)
    TabLayout tabLayout;

    @BindView(R.id.tv_deliv_value)
    CustomFontTextView tvDelivValue;

    private int[] tabIcons = {R.drawable.time1, R.drawable.info1, R.drawable.info1};
    private int[] tabIconsSelected = {R.drawable.time, R.drawable.info, R.drawable.info};

    @BindView(R.id.view_pager_main)
    NonSwipeableViewPager viewPager;

    @BindView(R.id.tv_comments_amount)
    CustomFontTextView tvCommentsAmount;


    @BindView(R.id.image_page_dots)
    LinearLayout loutDots;

    @BindView(R.id.main_content)
    CoordinatorLayout coordinatorLayout;

    private TextView[] dots;

    ViewPagerAdapter adapter;

    GallerySliderAdapter sliderStartAdapter;
    CleanerDetailsPresenter presenter;

    Handler handler;
    private Integer id;

    String title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_details);
        ButterKnife.bind(this);


        handler = new Handler(Looper.getMainLooper());
        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new CleanerDetailsPresenter(dataManager);
        presenter.onAttach(this);

        Intent intent = getIntent();
        String cleanerId = intent.getStringExtra(StaticValues.KEY_CLEANER_ID);

        presenter.getMainDetails(cleanerId);







    }

    private void setupTabs() {
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

 /*   @Override
    public void init(Bundle savedInstanceState) {
        enableFullscreen();
        setContent(R.layout.activity_cleaner_details);
        ButterKnife.bind(this);
        initSlider();
        addBottomDots(0);
    }*/


    public void initSlider(ArrayList<Gallery> gallery) {
        addBottomDots(0);

        /*slider_image_list = new ArrayList<>();

        slider_image_list.add(new Gallery(1,"http://newlearninginstitute.org/wp-content/uploads/2018/03/1.jpg"));
        slider_image_list.add(new Gallery(2,"https://scstylecaster.files.wordpress.com/2014/10/dry_cleaning_feature.jpg"));
        slider_image_list.add(new Gallery(3,"http://dcscofvistaridge.com/wp-content/uploads/2015/11/shutterstock_146426759-slide.jpg"));

*/

        sliderStartAdapter = new GallerySliderAdapter(this, gallery);
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



    public static Intent getStartIntent(Context context, String cleanerId) {
        Intent intent = new Intent(context, CleanerDetailsActivity.class);
        intent.putExtra(StaticValues.KEY_CLEANER_ID, cleanerId);
        return intent;
    }

    private void setupViewPager(NonSwipeableViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(OrderTypeFragment.newInstance(),"Order Type Fragment");
        adapter.addFragment(PricingFragment.newInstance(id.toString()), "Pricing Fragment");
        adapter.addFragment(InfoFragment.newInstance(title, description),"Info Fragment");

        viewPager.setAdapter(adapter);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void populateMainData(final CleanerDetails cleanerDetails) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                initSlider(cleanerDetails.getGallery());

                tvCleanerName.setText(cleanerDetails.getName());
                String commentsAmount = getString(R.string.comments) + " : " + cleanerDetails.getComments();
                tvCommentsAmount.setText(commentsAmount);
                String mincharge = getString(R.string.minimum_charge) + " : " + cleanerDetails.getMincharge();
                tvMinCharge.setText(mincharge);

                String delivVal = getString(R.string.delivary_price) + " : " + cleanerDetails.getDelivaryVal();
                tvDelivValue.setText(delivVal);


                rate(cleanerDetails.getReview());

                id = cleanerDetails.getId();

                title = cleanerDetails.getName();
                description = cleanerDetails.getBio();

                setupTabs();
            }
        });

    }

    private void rate(int rate) {
        switch (rate)
        {
            case 1:
                imRate.setImageResource(R.drawable.star1);
                break;

            case 2:
                imRate.setImageResource(R.drawable.star2);
                break;

            case 3:
                imRate.setImageResource(R.drawable.star3);
                break;

            case 4:
                imRate.setImageResource(R.drawable.star4);
                break;

            case 5:
                imRate.setImageResource(R.drawable.star5);
                break;
        }
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






/*
    public boolean onTouch(View view, MotionEvent event) {

        // Get finger position on screen
        final int Y = (int) event.getRawY();

        // Switch on motion event type
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                // save default base layout height
                defaultViewHeight = baseLayout.getHeight();

                // Init finger and view position
                previousFingerPosition = Y;
                baseLayoutPosition = (int) baseLayout.getY();
                break;

            case MotionEvent.ACTION_UP:
                // If user was doing a scroll up
                if(isScrollingUp){
                    // Reset baselayout position
                    baseLayout.setY(0);
                    // We are not in scrolling up mode anymore
                    isScrollingUp = false;
                }

                // If user was doing a scroll down
                if(isScrollingDown){
                    // Reset baselayout position
                    baseLayout.setY(0);
                    // Reset base layout size
                    baseLayout.getLayoutParams().height = defaultViewHeight;
                    baseLayout.requestLayout();
                    // We are not in scrolling down mode anymore
                    isScrollingDown = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isClosing){
                    int currentYPosition = (int) baseLayout.getY();

                    // If we scroll up
                    if(previousFingerPosition >Y){
                        // First time android rise an event for "up" move
                        if(!isScrollingUp){
                            isScrollingUp = true;
                        }

                        // Has user scroll down before -> view is smaller than it's default size -> resize it instead of change it position
                        if(baseLayout.getHeight()<defaultViewHeight){
                            baseLayout.getLayoutParams().height = baseLayout.getHeight() - (Y - previousFingerPosition);
                            baseLayout.requestLayout();
                        }
                        else {
                            // Has user scroll enough to "auto close" popup ?
                            if ((baseLayoutPosition - currentYPosition) > defaultViewHeight / 4) {
                                closeUpAndDismissDialog(currentYPosition);
                                return true;
                            }

                            //
                        }
                        baseLayout.setY(baseLayout.getY() + (Y - previousFingerPosition));

                    }
                    // If we scroll down
                    else{

                        // First time android rise an event for "down" move
                        if(!isScrollingDown){
                            isScrollingDown = true;
                        }

                        // Has user scroll enough to "auto close" popup ?
                        if (Math.abs(baseLayoutPosition - currentYPosition) > defaultViewHeight / 2)
                        {
                            closeDownAndDismissDialog(currentYPosition);
                            return true;
                        }

                        // Change base layout size and position (must change position because view anchor is top left corner)
                        baseLayout.setY(baseLayout.getY() + (Y - previousFingerPosition));
                        baseLayout.getLayoutParams().height = baseLayout.getHeight() - (Y - previousFingerPosition);
                        baseLayout.requestLayout();
                    }

                    // Update position
                    previousFingerPosition = Y;
                }
                break;
        }
        return true;
    }



    public void closeUpAndDismissDialog(int currentPosition){
        isClosing = true;
        ObjectAnimator positionAnimator = ObjectAnimator.ofFloat(baseLayout, "y", currentPosition, -baseLayout.getHeight());
        positionAnimator.setDuration(300);
        positionAnimator.addListener(new Animator.AnimatorListener()
        {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator)
            {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        positionAnimator.start();
    }

    public void closeDownAndDismissDialog(int currentPosition){
        isClosing = true;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenHeight = size.y;
        ObjectAnimator positionAnimator = ObjectAnimator.ofFloat(baseLayout, "y", currentPosition, screenHeight+baseLayout.getHeight());
        positionAnimator.setDuration(300);
        positionAnimator.addListener(new Animator.AnimatorListener()
        {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator)
            {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        positionAnimator.start();
    }

*/

}
