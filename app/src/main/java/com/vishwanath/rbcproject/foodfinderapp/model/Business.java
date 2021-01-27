package com.vishwanath.rbcproject.foodfinderapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Business extends RealmObject {
    private float rating;
    private String price;
    private String phone;
    private String id;
    private String alias;
    private boolean is_closed; // kept it the way it is, if camelToed it doesn't return anything
    private RealmList<Category> categories;
    private int review_count; // kept it the way it is, if camelToed it doesn't return anything
    private String name;
    private String url;
    private Coordinates coordinates;
    private String image_url; // kept it the way it is, if camelToed it doesn't return anything
    private Location location;
    private double distance;
    private RealmList<String> transactions;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isIsClosed() {
        return is_closed;
    }

    public void setIsClosed(boolean isClosed) {
        this.is_closed = isClosed;
    }

    public RealmList<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = this.review_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = this.image_url;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public RealmList<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(RealmList<String> transactions) {
        this.transactions = transactions;
    }


}