package com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces;


import com.vishwanath.rbcproject.foodfinderapp.callbacks.ObjectCallback;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.SimpleCallback;

import io.realm.Realm;

public interface RealmDatabase {
    Realm getRealm();
    boolean isClosed();
    void executeTransaction(Realm.Transaction transaction);
    void executeTransactionAsync(Realm.Transaction transaction);
    void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback);
    void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback, ObjectCallback<Throwable> errorCallback);
}
