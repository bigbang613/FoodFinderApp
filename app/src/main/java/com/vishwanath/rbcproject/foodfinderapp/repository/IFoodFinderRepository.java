package com.vishwanath.rbcproject.foodfinderapp.repository;

import com.vishwanath.rbcproject.foodfinderapp.callbacks.ObjectCallback;
import com.vishwanath.rbcproject.foodfinderapp.model.BusinessDetail;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;
import com.vishwanath.rbcproject.foodfinderapp.view.base.LatLong;

public interface IFoodFinderRepository {
    void getRestaurantList(String term, String sortBy, LatLong location, ObjectCallback<TotalBusiness> successCallback, ObjectCallback<Throwable> errorCallback);
    void getRestaurantList(String location, ObjectCallback<TotalBusiness> successCallback, ObjectCallback<Throwable> errorCallback);
    void getRestaurantDetail(String business_id, ObjectCallback<BusinessDetail> successCallback, ObjectCallback<Throwable> errorCallback);
    void cancelApiCalls();
}
