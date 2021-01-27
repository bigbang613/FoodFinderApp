package com.vishwanath.rbcproject.foodfinderapp.model;


import com.vishwanath.rbcproject.foodfinderapp.utility.Util;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Location extends RealmObject {
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String country;
    private String state;
    private String zip_code;
    private RealmList<String> displayAddress = null;
    private String crossStreets;
//    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getCity() {
        return !Util.isNullOrEmpty(city) ? city : "";
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress2() {
        return !Util.isNullOrEmpty(address2) ? address2 : "";
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return !Util.isNullOrEmpty(address3) ? address3 : "";
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getState() {
        return !Util.isNullOrEmpty(state) ? state : "";
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress1() {
        return !Util.isNullOrEmpty(address1) ? address1 : "";
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getZip_code() {
        return !Util.isNullOrEmpty(zip_code) ? zip_code : "";
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public RealmList<String> getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(RealmList<String> displayAddress) {
        this.displayAddress = displayAddress;
    }

    public String getCrossStreets() {
        return crossStreets;
    }

    public void setCrossStreets(String crossStreets) {
        this.crossStreets = crossStreets;
    }

//    public Map<String, Object> getAdditionalProperties() {
//        return additionalProperties;
//    }
//
//    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
//        this.additionalProperties = additionalProperties;
//    }


    public String toString() {
        String address = getAddress1() + " " + getAddress2() + " " + getAddress3() + " " + getCity() + " " + getState() + " " + getZip_code();
        return !Util.isNullOrEmpty(address) ? address.trim() : "";
    }

}