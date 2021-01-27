package com.vishwanath.rbcproject.foodfinderapp.view.mainactivity;

import android.content.Context;

import com.vishwanath.rbcproject.foodfinderapp.application.FoodFinderApplication;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.ObjectCallback;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.SimpleCallback;
import com.vishwanath.rbcproject.foodfinderapp.repository.IFoodFinderRepository;
import com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces.IFoodFinderDatabase;
import com.vishwanath.rbcproject.foodfinderapp.view.base.BaseModel;

import javax.inject.Inject;

public class MainModel extends BaseModel {

    @Inject IFoodFinderRepository mRepository;
    @Inject IFoodFinderDatabase mDatabase;


    public MainModel(Context context) {
        super(context);
        FoodFinderApplication.getComponent().inject(this);
    }

    public void getRestaurantList(SimpleCallback successCallback, ObjectCallback<Throwable> errorCallback) {
//        mRepository.getRestaurantList(list -> {
//                    successCallback.onCallback();
//                }, errorCallback);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
