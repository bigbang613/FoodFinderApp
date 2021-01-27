package com.vishwanath.rbcproject.foodfinderapp.service.dagger.module;

import com.vishwanath.rbcproject.foodfinderapp.service.database.implementation.FoodFinderRealm;
import com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces.IFoodFinderDatabase;
import com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces.IManagedRealm;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseServiceModule {
    private IManagedRealm mManagedRealm;

    public DatabaseServiceModule(IManagedRealm managedRealm) {
        mManagedRealm = managedRealm;
    }

    @Singleton
    @Provides
    public IFoodFinderDatabase provideFoodFinderDatabase() {
        return new FoodFinderRealm(mManagedRealm);
    }

    @Singleton
    @Provides
    public IManagedRealm provideManagedRealm() {
        return mManagedRealm;
    }

}
