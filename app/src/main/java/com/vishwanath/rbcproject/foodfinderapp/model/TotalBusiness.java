package com.vishwanath.rbcproject.foodfinderapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * getters and setters for restaurant api object
 */

public class TotalBusiness extends RealmObject {
    private int total;
    private RealmList<Business> businesses;
    private Region region;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public RealmList<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(RealmList<Business> businesses) {
        this.businesses = businesses;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}




