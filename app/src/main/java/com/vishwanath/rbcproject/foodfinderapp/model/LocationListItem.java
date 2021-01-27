package com.vishwanath.rbcproject.foodfinderapp.model;

public class LocationListItem {

    private String category;
    private Business business;

    public LocationListItem(String category, Business business) {
        this.category = category;
        this.business = business;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
