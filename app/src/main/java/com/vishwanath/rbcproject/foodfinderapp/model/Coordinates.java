package com.vishwanath.rbcproject.foodfinderapp.model;

import io.realm.RealmObject;

public class Coordinates extends RealmObject {
    private double latitude;
    private double longitude;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

//    public Map<String, Object> getAdditionalProperties() {
//        return additionalProperties;
//    }
//
//    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
//        this.additionalProperties = additionalProperties;
//    }
}
