package com.vishwanath.rbcproject.foodfinderapp.service.database.implementation;

public class DatabaseInitializer {

    public DatabaseInitializer() {

    }

    public void initDatabase(ManagedRealm managedRealm) {
        managedRealm.initRealm();
    }
}
