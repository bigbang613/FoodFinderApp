package com.vishwanath.rbcproject.foodfinderapp.service.dagger.module;

import com.vishwanath.rbcproject.foodfinderapp.model.Business;
import com.vishwanath.rbcproject.foodfinderapp.model.Category;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;
import com.vishwanath.rbcproject.foodfinderapp.service.database.implementation.ManagedRealm;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@io.realm.annotations.RealmModule(classes={TotalBusiness.class, Category.class, Business.class})
public class RealmModule {

    private ManagedRealm mManagedRealm;

    public RealmModule(ManagedRealm managedRealm) {
        mManagedRealm = managedRealm;
    }

    @Singleton
    @Provides
    public ManagedRealm provideManagedRealm() { return mManagedRealm; }

}
