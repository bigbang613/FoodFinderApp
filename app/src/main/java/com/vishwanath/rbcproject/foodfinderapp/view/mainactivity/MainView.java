package com.vishwanath.rbcproject.foodfinderapp.view.mainactivity;

public interface MainView {
    void setTitle(String title);
    void showDialog(String title, String message);
    void showToast(String message);
    //void showLoading(boolean show);
    //void loadRestaurantList(String query);
}
