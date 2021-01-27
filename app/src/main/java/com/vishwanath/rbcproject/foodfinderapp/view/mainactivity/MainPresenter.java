package com.vishwanath.rbcproject.foodfinderapp.view.mainactivity;

import com.vishwanath.rbcproject.foodfinderapp.view.base.Presenter;

public class MainPresenter implements Presenter {
    private MainView mView;
    private MainModel mModel;

    public MainPresenter(MainView view, MainModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onCreated() {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.onDestroy();
        }
    }
}
