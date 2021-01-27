package com.vishwanath.rbcproject.foodfinderapp.callbacks;

import retrofit2.Callback;

public interface RetrofitCallback<T> extends Callback<T> {
    void onOffline();
}
