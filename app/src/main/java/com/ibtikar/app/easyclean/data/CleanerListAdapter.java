package com.ibtikar.app.easyclean.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.models.CleanerItemModel;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.ibtikar.app.easyclean.ui_utilities.CustomRecyclerView;
import com.ibtikar.app.easyclean.utilities.PaginationAdapterCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CleanerListAdapter extends CustomRecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private ArrayList<CleanerItemModel> arrayList;
    private Context context;
    private customButtonListener customListener;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private PaginationAdapterCallback mCallback;
    private String errorMsg;


    public CleanerListAdapter(Context context,
                            ArrayList<CleanerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    public void add(CleanerItemModel r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size()-1 );
    }

    public void addAll(ArrayList<CleanerItemModel> opResults) {
        for (CleanerItemModel result : opResults) {
            add(result);
        }
    }

    public void remove(CleanerItemModel r) {
        int position = arrayList.indexOf(r);
        if (position > -1) {
            arrayList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public CleanerItemModel getItem(int position) {
        return arrayList.get(position);
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        //add(new OpportunityModel());
        add(getItem(arrayList.size()-1));
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = arrayList.size() - 1;
        CleanerItemModel result = getItem(position);

        if (result != null) {
            arrayList.remove(position);
            notifyItemRemoved(position);
        }
    }


    @Override
    public CustomRecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case ITEM:
                View viewItem = mInflater.inflate(
                        R.layout.view_cleaner_list_item, viewGroup, false);
                viewHolder = new ItemViewHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = mInflater.inflate(R.layout.view_loading_footer, viewGroup, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }


        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final CleanerItemModel model = arrayList.get(position);

        switch (getItemViewType(position))
        {
            case ITEM:
                final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

                if(!(model.getImage().equals("") || model.getImage() == null ))
                {
                    Glide.with(context)
                            .load(model.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(itemViewHolder.imMain);
                }
                else {
                }
                itemViewHolder.loutContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customListener.onItemClickListner(model.getId().toString());
                    }
                });

                itemViewHolder.tvCleanerName.setText(model.getName());

                switch (model.getReview())
                {
                    case 1:
                        itemViewHolder.imStars.setImageResource(R.drawable.star1);
                        break;

                    case 2:
                        itemViewHolder.imStars.setImageResource(R.drawable.star2);
                        break;

                    case 3:
                        itemViewHolder.imStars.setImageResource(R.drawable.star3);
                        break;

                    case 4:
                        itemViewHolder.imStars.setImageResource(R.drawable.star4);
                        break;

                    case 5:
                        itemViewHolder.imStars.setImageResource(R.drawable.star5);
                        break;
                }

                String minimum = context.getString(R.string.minimum_charge) + model.getMincharge() + context.getString(R.string.unit);
                itemViewHolder.tvMinimum.setText(minimum);

                if (!model.isVisa())
                    itemViewHolder.imVisa.setAlpha(0.3f);

                if (!model.isMastercard())
                    itemViewHolder.imMasterCard.setAlpha(0.3f);

                if (!model.isDelivary())
                    itemViewHolder.imDelivery.setAlpha(0.3f);
                break;

            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;

                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) loadingVH.itemView.getLayoutParams();
                layoutParams.setFullSpan(true);


                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);

                    loadingVH.mErrorTxt.setText(
                            errorMsg != null ?
                                    errorMsg :
                                    context.getString(R.string.error_msg_unknown));

                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;

        }
    }

    public class ItemViewHolder extends CustomRecyclerView.ViewHolder {
        @BindView(R.id.lout_container)
        CardView loutContainer;

        @BindView(R.id.tv_cleaner_item_name)
        CustomFontTextView tvCleanerName;

        @BindView(R.id.tv_cleaner_type)
        CustomFontTextView tvCleanerType;

        @BindView(R.id.im_stars)
        ImageView imStars;

        @BindView(R.id.tv_minimum)
        CustomFontTextView tvMinimum;

        @BindView(R.id.im_visa)
        ImageView imVisa;

        @BindView(R.id.im_master_card)
        ImageView imMasterCard;

        @BindView(R.id.im_delivery)
        ImageView imDelivery;


        @BindView(R.id.im_main)
        ImageView imMain;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }



    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.loadmore_progress)
        ProgressBar mProgressBar;
        @BindView(R.id.loadmore_retry)
        ImageButton mRetryBtn;
        @BindView(R.id.loadmore_errortxt)
        TextView mErrorTxt;
        @BindView(R.id.loadmore_errorlayout)
        LinearLayout mErrorLayout;

        public LoadingVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            if (mProgressBar != null) {
                mProgressBar.setIndeterminate(true);
                mProgressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.blue), android.graphics.PorterDuff.Mode.MULTIPLY);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:

                    showRetry(false, null);
                    mCallback.retryPageLoad();

                    break;
            }
        }
    }


    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(arrayList.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }



    public interface customButtonListener {
        public void onItemClickListner(String id);
    }
    public void setCustomButtonListner(customButtonListener listener) {
        this.customListener = listener;
    }

    public void setPagingAdapterCallback (PaginationAdapterCallback pagingAdapterCallback)
    {
        this.mCallback = pagingAdapterCallback;
    }

}
