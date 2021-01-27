package com.vishwanath.rbcproject.foodfinderapp.view.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.vishwanath.rbcproject.foodfinderapp.R;
import com.vishwanath.rbcproject.foodfinderapp.model.Business;
import com.vishwanath.rbcproject.foodfinderapp.model.Category;
import com.vishwanath.rbcproject.foodfinderapp.view.interfaces.OnItemClickListener;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantItemHolder> {

    private List<Business> mRestaurants;
    private OnItemClickListener mOnItemClickListener;

    public RestaurantListAdapter(List<Business> restaurants, OnItemClickListener onItemClickListener) {
        mRestaurants = restaurants;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RestaurantItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.restaurant_list_item, parent, false);
        return new RestaurantItemHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantItemHolder holder, int position) {
        Business business = mRestaurants.get(position);
        holder.bind(business);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Clicked successfully", Toast.LENGTH_SHORT).show();
                //Navigation.findNavController(mActivity.getFragmentManager()).navigate(R.id.action_restaurantListFragment_to_restaurantDetailFragment);
                mOnItemClickListener.onItemClick(v, business.getName(), business.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mRestaurants ? mRestaurants.size() : 0);
    }

    static class RestaurantItemHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener */{

        @BindView(R.id.restaurant_imageview) ImageView mRestaurantImage;
        @BindView(R.id.restaurant_name_textview) TextView mRestaurantNameText;
        @BindView(R.id.restaurant_address) TextView mAddressText;
        @BindView(R.id.restaurant_price_range) TextView mExpensiveText;
        @BindView(R.id.restaurant_category) TextView mFoodCategory;
        @BindView(R.id.dinein_imageview) ImageView mIsDineInAvailable;
        @BindView(R.id.takeout_imageview) ImageView mIsTakeoutAvailable;
        @BindView(R.id.delivery_imageview) ImageView mIsDeliveryAvailable;
        @BindView(R.id.dinein_textview) TextView mDineInText;
        @BindView(R.id.takeout_textview) TextView mTakeoutText;
        @BindView(R.id.delivery_textview) TextView mDeliveryText;
        @BindView(R.id.restaurant_distance_textview) TextView mDistanceText;
        @BindView(R.id.restaurant_rating_textview) TextView mRatingBarText;
        @BindView(R.id.restaurant_rating_bar) RatingBar mRatingBar;

        RestaurantItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("DefaultLocale")
        void bind(Business business) {
            //Glide.with(itemView).load(business.getImage_url()).override(200, 200).into(mRestaurantImage);
            mRestaurantNameText.setText(business.getName());
            mAddressText.setText(business.getLocation().toString());
            mIsDineInAvailable.setImageResource(isDineInAvailable(business.getTransactions()) ? R.drawable.ic_available : R.drawable.ic_close);
            mIsTakeoutAvailable.setImageResource(isTakeoutAvailable(business.getTransactions()) ? R.drawable.ic_available : R.drawable.ic_close);
            mIsDeliveryAvailable.setImageResource(isDeliveryAvailable(business.getTransactions()) ? R.drawable.ic_available : R.drawable.ic_close);
            mRatingBarText.setText(String.format("(%d)", business.getReview_count(), Locale.getDefault()));
            mRatingBar.setNumStars((int) business.getRating());

            String doubleNumber = String.format("%.1f", getDistanceInMiles(business.getDistance()));
            mDistanceText.setText(String.format("%s miles", doubleNumber));
            mFoodCategory.setText(getCategories(business.getCategories()));
            mExpensiveText.setText(business.getPrice());
        }

        private double getDistanceInMiles(double distance) {
            return (distance/1000)*0.625; // 1000 for converting to kilometers and 0.625 for KM to Miles
        }

        private String getCategories(List<Category> categories) {
//            StringBuilder builder = new StringBuilder();
//            for (Category category : categories) {
//                builder.append(category.getTitle());
//            }
            //return builder.toString();

            if (null == categories || categories.isEmpty()){
                return "N/A";
            }
            return categories.get(0).getTitle(); // just setting the first category (more info will be on the detail screen)
        }

        private boolean isDineInAvailable(List<String> transactions) {
            return transactions.contains("dine".toLowerCase());
        }

        private boolean isTakeoutAvailable(List<String> transactions) {
            return transactions.contains("takeout".toLowerCase());
        }

        private boolean isDeliveryAvailable(List<String> transactions) {
            return transactions.contains("delivery".toLowerCase());
        }
    }
}
