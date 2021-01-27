package com.vishwanath.rbcproject.foodfinderapp.view.mainactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.vishwanath.rbcproject.foodfinderapp.R;
import com.vishwanath.rbcproject.foodfinderapp.view.base.BaseActivity;

import androidx.fragment.app.FragmentManager;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView, FragmentManager.OnBackStackChangedListener {

    @SuppressLint("NonConstantResourceId") @BindView(R.id.loading) View mViewLoading;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.food_finder));
        mPresenter = new MainPresenter(this, new MainModel(this));
        mPresenter.onCreated();
        getSupportFragmentManager().addOnBackStackChangedListener(this);


//        NavHostFragment navHostFragment = (NavHostFragment) this.getSupportFragmentManager().findFragmentById(R.id.restaurantsInLocationFragment);
//        NavController navController = navHostFragment != null ? navHostFragment.getNavController() : null;
//
//
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration(navController.getGraph());
//        setup
//
//        AppCompatActivity.setupActionBarWithNavController(navController, appBarConfiguration);

    }

//    @Override
//    public void onBackStackChanged() {
//        displayHomeAsUpIcon();
//    }

    @Override
    public void onBackStackChanged() {
        int stackHeight = getSupportFragmentManager().getBackStackEntryCount();
        if (getSupportActionBar()!= null) {
            if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setHomeButtonEnabled(false);
            }
        }
    }


    /**
     * Enables Up button only if there are entries in the back stack i.e. fragments
     * */
    public void displayHomeAsUpIcon() {
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount() > 0;

        if (getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();

        return true;
    }



}
