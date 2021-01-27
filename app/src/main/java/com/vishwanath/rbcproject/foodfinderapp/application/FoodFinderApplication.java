package com.vishwanath.rbcproject.foodfinderapp.application;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.component.DaggerFoodFinderApplicationComponent;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.component.FoodFinderApplicationComponent;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.AppServiceModule;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.DatabaseServiceModule;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.FoodFinderRepositoryServiceModule;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.RealmModule;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.WebServiceModule;
import com.vishwanath.rbcproject.foodfinderapp.service.database.implementation.DatabaseInitializer;
import com.vishwanath.rbcproject.foodfinderapp.service.database.implementation.ManagedRealm;

import io.realm.Realm;

public class FoodFinderApplication extends Application {

    private static ManagedRealm mManagedRealm;
    private static FoodFinderApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        initRealmDatabaseManager();
        initComponent();
        initStetho();
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void initRealmDatabaseManager() {
        mManagedRealm = new ManagedRealm(this);
        new DatabaseInitializer().initDatabase(mManagedRealm);
    }

    public static ManagedRealm getManagedRealm() {
        return mManagedRealm;
    }

    private void initComponent() {
        mComponent = initDaggerComponent(this);
    }

    private FoodFinderApplicationComponent initDaggerComponent(FoodFinderApplication application) {
        WebServiceModule webServiceModule = new WebServiceModule(application);
        DatabaseServiceModule databaseServiceModule = new DatabaseServiceModule(getManagedRealm());
        return DaggerFoodFinderApplicationComponent.builder()
                .webServiceModule(webServiceModule)
                .databaseServiceModule(databaseServiceModule)
                .foodFinderRepositoryServiceModule(new FoodFinderRepositoryServiceModule(this))
                .realmModule(new RealmModule(getManagedRealm()))
                .appServiceModule(new AppServiceModule(application))
                .build();
    }

    public static FoodFinderApplicationComponent getComponent() {
        return mComponent;
    }
}
