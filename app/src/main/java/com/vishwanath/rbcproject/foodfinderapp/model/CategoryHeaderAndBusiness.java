package com.vishwanath.rbcproject.foodfinderapp.model;

/**
 * getters and setters for header and restaurants objects
 */

public class CategoryHeaderAndBusiness {

    private String categoryHeader;
    private Business business;

    public CategoryHeaderAndBusiness(String categoryHeader, Business business) {
        this.categoryHeader = categoryHeader;
        this.business = business;
    }

    public String getCategoryHeader() {
        return categoryHeader;
    }

    public void setCategoryHeader(String categoryHeader) {
        this.categoryHeader = categoryHeader;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}




