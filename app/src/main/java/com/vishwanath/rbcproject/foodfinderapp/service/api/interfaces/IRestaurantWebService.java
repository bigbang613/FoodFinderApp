package com.vishwanath.rbcproject.foodfinderapp.service.api.interfaces;

import android.content.Context;

import com.vishwanath.rbcproject.foodfinderapp.model.BusinessDetail;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.ObjectCallback;
import com.vishwanath.rbcproject.foodfinderapp.view.base.LatLong;

public interface IRestaurantWebService extends WebService {
    void getRestaurantList(Context context, String term, String sortBy, LatLong location, ObjectCallback<TotalBusiness> successCallback, ObjectCallback<Throwable> errorCallback);
    void getRestaurantList(Context context, String location, ObjectCallback<TotalBusiness> successCallback, ObjectCallback<Throwable> errorCallback);
    void getRestaurantDetail(Context context, String business_id, ObjectCallback<BusinessDetail> successCallback, ObjectCallback<Throwable> errorCallback);
}
