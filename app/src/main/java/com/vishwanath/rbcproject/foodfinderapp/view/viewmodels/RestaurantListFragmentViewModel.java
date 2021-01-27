package com.vishwanath.rbcproject.foodfinderapp.view.viewmodels;

import android.app.Application;

import com.vishwanath.rbcproject.foodfinderapp.model.Business;
import com.vishwanath.rbcproject.foodfinderapp.model.BusinessDetail;
import com.vishwanath.rbcproject.foodfinderapp.model.CategoryHeaderAndBusiness;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;
import com.vishwanath.rbcproject.foodfinderapp.repository.FoodFinderRepository;
import com.vishwanath.rbcproject.foodfinderapp.utility.Util;
import com.vishwanath.rbcproject.foodfinderapp.view.base.LatLong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RestaurantListFragmentViewModel extends AndroidViewModel {

    private FoodFinderRepository mFoodFinderRepository = new FoodFinderRepository();
    private LiveData<TotalBusiness> mTotalBusiness = mFoodFinderRepository.getTotalBusinesses();
    private LiveData<BusinessDetail> mBusinessDetail = mFoodFinderRepository.getBusinessDetail();

    public RestaurantListFragmentViewModel(@Nonnull Application application) {
        super(application);
    }

    public LiveData<TotalBusiness> getRestaurants() {
        return mTotalBusiness;
    }

    public LiveData<BusinessDetail> getRestaurantDetail() {
        return mBusinessDetail;
    }

    public void getRestaurantList(String term, String sortBy, LatLong location) {
        mFoodFinderRepository.getRestaurantList(term, sortBy, location);
    }

    public void getRestaurantList(String location) {
        mFoodFinderRepository.getRestaurantList(location);
    }

    public void getRestaurantDetail(String business_id) {
        mFoodFinderRepository.getRestaurantDetail(business_id);
    }

    /**
     *
     * This will create a list of categories with associated businesses in that category. I am assuming that the first category is most definitive
     * for classifying as it will be coming first in the priority. For E.G. if a business has American and Tex-Mex listed, we can assume that
     * the restaurant is primarily American but also has Tex-Mex.
     *
     * */
    public List<CategoryHeaderAndBusiness> getCategoryWithRestaurants(TotalBusiness totalBusiness) {
        Map<String, Business> categoriesWithBusiness = new HashMap<>();
        List<Business> businesses = new ArrayList<>();
        List<CategoryHeaderAndBusiness> list = new ArrayList<>();
        Map<String, Integer> categoryWithCount = new HashMap<>();
        List<String> categories = new ArrayList<>();

        for (Business business : totalBusiness.getBusinesses()) {
            // we will loop thru this to get first category for every business and assign business to that category
            if (business.getCategories().get(0) != null && !Util.isNullOrEmpty(business.getCategories().get(0).getTitle())){
                String title = business.getCategories().get(0).getTitle();
                businesses.add(business);

                if (categoriesWithBusiness.containsKey(title)) {
                    // if category exists and its repeated, we will increment the count by 1 else assign 1 if category doesnt exist
                    categoryWithCount.put(title, categoryWithCount.get(title) != null ? (categoryWithCount.get(title) + 1) : 1);
                }
                categoryWithCount.put(title, 1); // adding category for first time
            }
        }

        // Now that we have a list of categories and their counts, we can add them to the list

        for (String category : categoryWithCount.keySet()){
            categories.add(category + categoryWithCount.get(category));
        }

        // TODO Now we need to add categories and businesses in that category together

        return list;
    }
}
