package com.vishwanath.rbcproject.foodfinderapp.service.dagger.module;

import android.content.Context;

import com.vishwanath.rbcproject.foodfinderapp.repository.FoodFinderRepository;
import com.vishwanath.rbcproject.foodfinderapp.repository.IFoodFinderRepository;
import com.vishwanath.rbcproject.foodfinderapp.service.api.implementation.RestaurantWebService;
import com.vishwanath.rbcproject.foodfinderapp.service.api.interfaces.IRestaurantWebService;

import dagger.Module;
import dagger.Provides;

@Module
public class FoodFinderRepositoryServiceModule {
    private Context mApplicationContext;

    public FoodFinderRepositoryServiceModule(Context context) {
        mApplicationContext = context;
    }
    
    @Provides
    public IFoodFinderRepository provideFoodFinderRepository() {
        return new FoodFinderRepository();
    }

    @Provides
    public IRestaurantWebService provideRestaurantWebService() {
        return new RestaurantWebService();
    }
}
