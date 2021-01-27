package com.vishwanath.rbcproject.foodfinderapp.view.adapters;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.vishwanath.rbcproject.foodfinderapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalImageAdapter extends RecyclerView.Adapter<HorizontalImageAdapter.RestaurantItemHolder> {

    private final List<String> mPhotos;

    public HorizontalImageAdapter(List<String> photos) {
        mPhotos = photos;
    }

    @NonNull
    @Override
    public RestaurantItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.horizontal_image_list_item, parent, false);
        return new RestaurantItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantItemHolder holder, int position) {
        holder.bind(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return (null != mPhotos ? mPhotos.size() : 0);
    }

    static class RestaurantItemHolder extends RecyclerView.ViewHolder {

        @SuppressLint("NonConstantResourceId") @BindView(R.id.loading) View mViewLoading;
        @SuppressLint("NonConstantResourceId") @BindView(R.id.imageView) ImageView mImage;

        RestaurantItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("DefaultLocale")
        void bind(String photoUrl) {
            showLoading(true);
            Glide.with(itemView).load(photoUrl).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    showLoading(false);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    showLoading(false);
                    return false;
                }
            }).into(mImage);

        }

        private void showLoading(boolean show) {
            if (mViewLoading != null) {
                mViewLoading.bringToFront();
                mViewLoading.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        }
    }
}
