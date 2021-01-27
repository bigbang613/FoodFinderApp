package com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces;

import com.vishwanath.rbcproject.foodfinderapp.callbacks.ObjectCallback;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.SimpleCallback;
import com.vishwanath.rbcproject.foodfinderapp.model.Business;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;

import io.realm.Realm;

public interface IFoodFinderDatabase {
    void executeTransaction(Realm.Transaction transaction);
    
    void executeTransactionAsync(Realm.Transaction transaction);
    
    void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback);
    
    void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback, ObjectCallback<Throwable> errorCallback);

    TotalBusiness getRestaurantList(String query);

    void saveBusiness(Business restaurant);

}
