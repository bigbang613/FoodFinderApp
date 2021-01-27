package com.vishwanath.rbcproject.foodfinderapp.service.dagger.component;

import com.vishwanath.rbcproject.foodfinderapp.repository.FoodFinderRepository;
import com.vishwanath.rbcproject.foodfinderapp.service.api.implementation.RestaurantWebService;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.AppServiceModule;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.DatabaseServiceModule;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.FoodFinderRepositoryServiceModule;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.RealmModule;
import com.vishwanath.rbcproject.foodfinderapp.service.dagger.module.WebServiceModule;
import com.vishwanath.rbcproject.foodfinderapp.view.mainactivity.MainModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    WebServiceModule.class,
    DatabaseServiceModule.class,
    FoodFinderRepositoryServiceModule.class,
    AppServiceModule.class,
    RealmModule.class
})


public interface FoodFinderApplicationComponent {

    void inject(RestaurantWebService restaurantWebService);

    void inject(FoodFinderRepository foodFinderRepository);

    void inject(MainModel mainModel);
}
