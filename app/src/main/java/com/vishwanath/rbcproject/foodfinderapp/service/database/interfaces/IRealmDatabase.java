package com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces;

import io.realm.Realm;

public interface IRealmDatabase extends IFoodFinderDatabase {
    Realm getRealm();
    boolean isClosed();
}
