package com.vishwanath.rbcproject.foodfinderapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessDetail {

    private List<Category> categories;
    private Coordinates coordinates;
    private String id;
    private String alias;
    private String name;
    private String imageUrl;
    private Boolean isClaimed;
    private Boolean isClosed;
    private String url;
    private String phone;
    private String displayPhone;
    private Integer reviewCount;
    private Double rating;//
    private Location location;//
    private List<String> photos = null;
    private String price;
    private List<Hour> hours = null;
    private List<Object> transactions = null;
    private List<SpecialHour> specialHours = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private Hour hour;
    private Open openHours;
    private SpecialHour SpecialHour;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getClaimed() {
        return isClaimed;
    }

    public void setClaimed(Boolean claimed) {
        isClaimed = claimed;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplayPhone() {
        return displayPhone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.displayPhone = displayPhone;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

    public List<Object> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Object> transactions) {
        this.transactions = transactions;
    }

    public List<SpecialHour> getSpecialHours() {
        return specialHours;
    }

    public void setSpecialHours(List<SpecialHour> specialHours) {
        this.specialHours = specialHours;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }

    public Open getOpenHours() {
        return openHours;
    }

    public void setOpenHours(Open openHours) {
        this.openHours = openHours;
    }

    public SpecialHour getSpecialHour() {
        return SpecialHour;
    }

    public void setSpecialHour(SpecialHour specialHour) {
        SpecialHour = specialHour;
    }
}
