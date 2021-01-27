package com.vishwanath.rbcproject.foodfinderapp.view.base;

import android.content.Context;

import androidx.annotation.CallSuper;

public class BaseModel {
    protected Context mContext;
    
    public BaseModel(Context context) {
        mContext = context;
    }

    @CallSuper
    public void onDestroy() {
        
    }
}
