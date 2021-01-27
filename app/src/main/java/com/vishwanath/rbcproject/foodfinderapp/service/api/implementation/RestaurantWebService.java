package com.vishwanath.rbcproject.foodfinderapp.service.api.implementation;

import android.content.Context;
import android.util.Log;

import com.vishwanath.rbcproject.foodfinderapp.application.FoodFinderApplication;
import com.vishwanath.rbcproject.foodfinderapp.exceptions.OfflineException;
import com.vishwanath.rbcproject.foodfinderapp.model.BusinessDetail;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.ObjectCallback;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.RetrofitCallback;
import com.vishwanath.rbcproject.foodfinderapp.service.api.interfaces.IRestaurantService;
import com.vishwanath.rbcproject.foodfinderapp.service.api.interfaces.IRestaurantWebService;
import com.vishwanath.rbcproject.foodfinderapp.view.base.LatLong;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Response;

public class RestaurantWebService implements IRestaurantWebService, PendingCallManager {

    @Inject IRestaurantService mRestaurantService;
    private List<Call> mPendingCalls;

    public RestaurantWebService() {
        FoodFinderApplication.getComponent().inject(this);
    }
    

    @Override
    public void addPendingCall(Call call) {
        if (mPendingCalls == null) {
            mPendingCalls = new ArrayList<>();
        }

        mPendingCalls.add(call);
    }

    @Override
    public void removePendingCall(Call call) {
        if (mPendingCalls != null) {
            mPendingCalls.remove(call);
        }
    }

    @Override
    public void cancelPendingCalls() {
        if (mPendingCalls != null) {
            for (Call call : mPendingCalls) {
                call.cancel();
            }
        }
    }
    
    @Override
    public void getRestaurantList(Context context, String term, String sortBy, LatLong location, ObjectCallback<TotalBusiness> successCallback, ObjectCallback<Throwable> errorCallback) {
        DecimalFormat decimalFormat = new DecimalFormat("#.######");
        Call<TotalBusiness> call = mRestaurantService.getRestaurants(term, sortBy, decimalFormat.format(location.getLatitude()), decimalFormat.format(location.getLongitude()));
        ApiService.enqueue(context, call, this, new RetrofitCallback<TotalBusiness>() {
            @Override
            public void onOffline() {
                onFailure(call, new OfflineException());
            }

            @Override
            public void onResponse(@NonNull Call<TotalBusiness> call, @NonNull Response<TotalBusiness> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        successCallback.onCallback(response.body());
                    } else {
                        onFailure(call, new Exception("schema is null"));
                    }
                } else {
                    onFailure(call, new Exception("call failed"));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (errorCallback != null) {
                    errorCallback.onCallback(t);
                }
            }
        });
    }

    @Override
    public void getRestaurantList(Context context, String location, ObjectCallback<TotalBusiness> successCallback, ObjectCallback<Throwable> errorCallback) {
        Call<TotalBusiness> call = mRestaurantService.getRestaurants(location);
        ApiService.enqueue(context, call, this, new RetrofitCallback<TotalBusiness>() {
            @Override
            public void onOffline() {
                onFailure(call, new OfflineException());
            }

            @Override
            public void onResponse(@NonNull Call<TotalBusiness> call, @NonNull Response<TotalBusiness> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        successCallback.onCallback(response.body());
                    } else {
                        onFailure(call, new Exception("schema is null"));
                    }
                } else {
                    onFailure(call, new Exception("call failed"));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (errorCallback != null) {
                    errorCallback.onCallback(t);
                }
            }
        });
    }

    @Override
    public void getRestaurantDetail(Context context, String business_id, ObjectCallback<BusinessDetail> successCallback, ObjectCallback<Throwable> errorCallback) {
        Call<BusinessDetail> call = mRestaurantService.getRestaurantDetail(business_id);
        ApiService.enqueue(context, call, this, new RetrofitCallback<BusinessDetail>() {
            @Override
            public void onOffline() {
                onFailure(call, new OfflineException());
            }

            @Override
            public void onResponse(@NonNull Call<BusinessDetail> call, @NonNull Response<BusinessDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        System.out.println(response);
                        Log.d("DetailResponse", response.body().toString());
                        successCallback.onCallback(response.body());
                    } else {
                        onFailure(call, new Exception("schema is null"));
                    }
                } else {
                    onFailure(call, new Exception("call failed"));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (errorCallback != null) {
                    errorCallback.onCallback(t);
                }
            }
        });
    }

    @Override
    public void cancelApiCalls() {
        //cancel web calls here as necessary
        cancelPendingCalls();
    }
}
