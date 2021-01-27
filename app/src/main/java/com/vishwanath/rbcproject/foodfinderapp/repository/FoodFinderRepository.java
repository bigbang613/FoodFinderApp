package com.vishwanath.rbcproject.foodfinderapp.repository;

import android.content.Context;

import com.vishwanath.rbcproject.foodfinderapp.application.FoodFinderApplication;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.ObjectCallback;
import com.vishwanath.rbcproject.foodfinderapp.model.BusinessDetail;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;
import com.vishwanath.rbcproject.foodfinderapp.service.api.interfaces.IRestaurantWebService;
import com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces.IFoodFinderDatabase;
import com.vishwanath.rbcproject.foodfinderapp.view.base.LatLong;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class FoodFinderRepository implements IFoodFinderRepository {

    @Inject Context mContext;
    @Inject IRestaurantWebService mRestaurantWebService;
    @Inject IFoodFinderDatabase mFoodFinderDatabase;

    private MutableLiveData<TotalBusiness> mTotalBusinesses = new MutableLiveData<>();
    private MutableLiveData<BusinessDetail> mBusinessDetail = new MutableLiveData<>();

    public FoodFinderRepository(){
       FoodFinderApplication.getComponent().inject(this);
    }


    public LiveData<TotalBusiness> getTotalBusinesses(){
        return mTotalBusinesses;
    }

    public LiveData<BusinessDetail> getBusinessDetail(){
        return mBusinessDetail;
    }

    public void getRestaurantList(String term, String sortBy, LatLong location){
        getRestaurantList(term, sortBy, location, response -> mTotalBusinesses.postValue(response), t -> {
            if (t != null){
                // show t
            }
        });
    }

    public void getRestaurantList( String location){
        getRestaurantList(location, response -> mTotalBusinesses.postValue(response), t -> {
            if (t != null){
                // show t
            }
        });
    }

    public void getRestaurantDetail(String business_id) {
        getRestaurantDetail(business_id, response -> mBusinessDetail.postValue(response), t -> {
            if (t != null) {
                // show t
            }
        });
    }

    @Override
    public void getRestaurantList(String term, String sortBy, LatLong location, ObjectCallback<TotalBusiness> successCallback, ObjectCallback<Throwable> errorCallback) {
        mRestaurantWebService.getRestaurantList(mContext, term, sortBy, location, response -> {
            successCallback.onCallback(response);
            System.out.println("Success");
        }, errorCallback);
    }

    @Override
    public void getRestaurantList(String location, ObjectCallback<TotalBusiness> successCallback, ObjectCallback<Throwable> errorCallback) {
        mRestaurantWebService.getRestaurantList(mContext, location, response -> {
            successCallback.onCallback(response);
            System.out.println("Success");
        }, errorCallback);
    }

    @Override
    public void getRestaurantDetail(String business_id, ObjectCallback<BusinessDetail> successCallback, ObjectCallback<Throwable> errorCallback) {
        mRestaurantWebService.getRestaurantDetail(mContext, business_id, response -> {
            successCallback.onCallback(response);
            System.out.println("Success");
        }, errorCallback);
    }

    @Override
    public void cancelApiCalls() {

    }
}
