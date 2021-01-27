package com.vishwanath.rbcproject.foodfinderapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishwanath.rbcproject.foodfinderapp.R;
import com.vishwanath.rbcproject.foodfinderapp.model.CategoryHeaderAndBusiness;
import com.vishwanath.rbcproject.foodfinderapp.utility.Util;
import com.vishwanath.rbcproject.foodfinderapp.view.interfaces.OnItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantLocationListAdapter extends RecyclerView.Adapter<RestaurantLocationViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_DETAIL = 1;

    private List<CategoryHeaderAndBusiness> mCategoryWithBusiness;
    private OnItemClickListener mOnItemClickListener;

    public RestaurantLocationListAdapter(List<CategoryHeaderAndBusiness> restaurantsWithCategory, OnItemClickListener onItemClickListener) {
        mCategoryWithBusiness = restaurantsWithCategory;
        mOnItemClickListener = onItemClickListener;
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Override
    public RestaurantLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RestaurantLocationViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_header, parent, false);
                viewHolder = new RestaurantLocationViewHolder.RestaurantLocationLabelViewHolder(view);
                break;
            case VIEW_TYPE_DETAIL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
                viewHolder = new RestaurantLocationViewHolder.RestaurantLocationBusinessViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantLocationViewHolder holder, int position) {
        CategoryHeaderAndBusiness categoryAndBusiness = mCategoryWithBusiness.get(position);
        holder.bind(categoryAndBusiness);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItemViewType(position) == 1) {
                    mOnItemClickListener.onItemClick(v, categoryAndBusiness.getBusiness().getName(), categoryAndBusiness.getBusiness().getId(), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mCategoryWithBusiness ? mCategoryWithBusiness.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return !Util.isNullOrEmpty(mCategoryWithBusiness.get(position).getCategoryHeader()) ? VIEW_TYPE_HEADER : VIEW_TYPE_DETAIL;
    }
}
