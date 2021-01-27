package com.vishwanath.rbcproject.foodfinderapp.service.database.implementation;

import android.content.Context;

import com.vishwanath.rbcproject.foodfinderapp.callbacks.ObjectCallback;
import com.vishwanath.rbcproject.foodfinderapp.callbacks.SimpleCallback;
import com.vishwanath.rbcproject.foodfinderapp.model.Business;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;
import com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces.IManagedRealm;
import com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces.IRealmDatabase;

import io.realm.Realm;

public class BaseRealmHelper implements IRealmDatabase {
    
    private IManagedRealm mManagedRealm;
    
    public BaseRealmHelper(IManagedRealm managedRealm) {
        mManagedRealm = managedRealm;
    }
    
    public Realm getRealm() {
        return mManagedRealm.getRealmInstance();
    }
    
    @Override
    public void executeTransaction(Realm.Transaction transaction) {
        try {
            getRealm().executeTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void executeTransactionAsync(Realm.Transaction transaction) {
        try {
            getRealm().executeTransactionAsync(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback) {
        try {
            getRealm().executeTransactionAsync(transaction, successCallback::onCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeTransactionAsync(Realm.Transaction transaction, SimpleCallback successCallback, ObjectCallback<Throwable> errorCallback) {
        try {
            getRealm().executeTransactionAsync(transaction, successCallback::onCallback, errorCallback::onCallback);
        } catch (Exception e) {
            e.printStackTrace();
            errorCallback.onCallback(e);
        }
    }


    @Override
    public TotalBusiness getRestaurantList(String query) {
        return null;
    }

    @Override
    public void saveBusiness(Business restaurant) {

    }

    public boolean isClosed() {
        boolean isClosed = true;
        
        try {
            isClosed = getRealm().isClosed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isClosed;
    }
    
    protected Context getContext() {
        return mManagedRealm.getContext();
    }}
