package com.vishwanath.rbcproject.foodfinderapp.view.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.vishwanath.rbcproject.foodfinderapp.R;
import com.vishwanath.rbcproject.foodfinderapp.model.CategoryHeaderAndBusiness;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

abstract class RestaurantLocationViewHolder extends RecyclerView.ViewHolder {

    RestaurantLocationViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract void bind(CategoryHeaderAndBusiness item);

    static class RestaurantLocationLabelViewHolder extends RestaurantLocationViewHolder {
        @Nullable
        @BindView(R.id.category_header) TextView mCategoryHeader;

        RestaurantLocationLabelViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CategoryHeaderAndBusiness item) {
            String title = item.getCategoryHeader();
            if (mCategoryHeader != null)
                mCategoryHeader.setText(title);
        }
    }

    static class RestaurantLocationBusinessViewHolder extends RestaurantLocationViewHolder {

        @BindView(R.id.restaurant_name_textview) TextView mRestaurantNameText;
        @BindView(R.id.restaurant_address) TextView mAddressText;
        @BindView(R.id.restaurant_price_range) TextView mExpensiveText;
        @BindView(R.id.restaurant_category) TextView mFoodCategory;
        @BindView(R.id.dinein_imageview) ImageView mIsDineInAvailable;
        @BindView(R.id.takeout_imageview) ImageView mIsTakeoutAvailable;
        @BindView(R.id.delivery_imageview) ImageView mIsDeliveryAvailable;
        @BindView(R.id.restaurant_distance_textview) TextView mDistanceText;
        @BindView(R.id.restaurant_rating_textview) TextView mRatingBarText;
        @BindView(R.id.restaurant_rating_bar)
        RatingBar mRatingBar;

        RestaurantLocationBusinessViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("DefaultLocale")
        void bind(CategoryHeaderAndBusiness item) {
            mRestaurantNameText.setText(item.getBusiness().getName());
            mAddressText.setText(item.getBusiness().getLocation().toString());
            mIsDineInAvailable.setImageResource(isDineInAvailable(item.getBusiness().getTransactions()) ? R.drawable.ic_available : R.drawable.ic_close);
            mIsTakeoutAvailable.setImageResource(isTakeoutAvailable(item.getBusiness().getTransactions()) ? R.drawable.ic_available : R.drawable.ic_close);
            mIsDeliveryAvailable.setImageResource(isDeliveryAvailable(item.getBusiness().getTransactions()) ? R.drawable.ic_available : R.drawable.ic_close);
            mRatingBarText.setText(String.format("(%d)", item.getBusiness().getReview_count(), Locale.getDefault()));
            mRatingBar.setNumStars((int) item.getBusiness().getRating());

            String doubleNumber = String.format("%.1f", getDistanceInMiles(item.getBusiness().getDistance()));
            mDistanceText.setText(String.format("%s miles", doubleNumber));
            mExpensiveText.setText(item.getBusiness().getPrice());

            // Hiding it since the layout is being used elsewhere
            mFoodCategory.setVisibility(View.GONE);
            mFoodCategory.setText("N/A");
        }

        private double getDistanceInMiles(double distance) {
            return (distance / 1000) * 0.625; // 1000 for converting to kilometers and 0.625 for KM to Miles
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
