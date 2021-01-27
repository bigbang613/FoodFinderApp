package com.vishwanath.rbcproject.foodfinderapp.view.base;

public interface Presenter {
    void onCreated();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
