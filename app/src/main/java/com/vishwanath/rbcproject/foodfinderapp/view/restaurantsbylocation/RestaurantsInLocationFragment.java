package com.vishwanath.rbcproject.foodfinderapp.view.restaurantsbylocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.vishwanath.rbcproject.foodfinderapp.R;
import com.vishwanath.rbcproject.foodfinderapp.utility.Util;
import com.vishwanath.rbcproject.foodfinderapp.view.adapters.RestaurantListAdapter;
import com.vishwanath.rbcproject.foodfinderapp.view.adapters.RestaurantLocationListAdapter;
import com.vishwanath.rbcproject.foodfinderapp.view.base.BaseFragment;
import com.vishwanath.rbcproject.foodfinderapp.view.base.LatLongListener;
import com.vishwanath.rbcproject.foodfinderapp.view.viewmodels.RestaurantListFragmentViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vishwanath.rbcproject.foodfinderapp.utility.Constants.LOCATION_REQUEST;

public class RestaurantsInLocationFragment extends BaseFragment {

    @SuppressLint("NonConstantResourceId") @BindView(R.id.loading) View mViewLoading;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.restaurants_location_list) RecyclerView mRestaurantLocationList;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.fragment_textview_no_results) TextView mNoResultsTextView;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.welcome_textview) TextView mWelcomeTextView;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.fragment_places_searchview) SearchView mPlacesSearchView;

    private RestaurantListFragmentViewModel mViewModel;
    private RestaurantLocationListAdapter mRestaurantLocationListAdapter; // desired adapter
    private RestaurantListAdapter mRestaurantListAdapter; // using this since the logic isn't 100% complete
    private LatLongListener mLatLongListener;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.restaurantsInLocationFragment);
            NavController navController = navHostFragment != null ? navHostFragment.getNavController() : null;

            if (navController != null) {
                navController.popBackStack();
            }
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurants_location_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(RestaurantListFragmentViewModel.class);
        mPlacesSearchView.setQueryHint(getString(R.string.current_location));

        initializeLatLong();
        if (Util.isLocationPermissionPresent(requireActivity())) {
            setupPlacesSearchQueryListener();
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST);
        }
    }

    private void initializeLatLong() {
        mLatLongListener = new LatLongListener(requireActivity());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupPlacesSearchQueryListener() {
        mPlacesSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!Util.isNullOrEmpty(newText)) {
                    mWelcomeTextView.setVisibility(View.GONE);
                    loadRestaurantsForLocation(newText);
                    mLatLongListener.stopLocationUpdates();
                } else {
                    mRestaurantLocationList.setVisibility(View.GONE);
                    mWelcomeTextView.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }

    private void loadRestaurantsForLocation(String location) {
        setupRestaurantsObserver();
        showLoading(true);
        mViewModel.getRestaurantList(location);
    }

    private void setupRestaurantsObserver() {
        if (getActivity() != null) {
            mViewModel.getRestaurants().observe(requireActivity(), totalBusiness -> {
                showLoading(false);
                if (!totalBusiness.getBusinesses().isEmpty()) {

                    /**
                     * This adapter show all the restaurants in an area that's searched, it is just a placeholder adapter
                     * as I am not able to complete my logic entirely due to enormous workload right now
                     * */
                    mRestaurantListAdapter = new RestaurantListAdapter(totalBusiness.getBusinesses(), (view, name, id, position) -> {
                        startNavigationComponent(R.id.action_restaurantsInLocationFragment_to_restaurantDetailFragment, id, name);
                    });

                    /**
                     * This adapter will populate a list of categories with count and all associated business.
                     * The logic is 90% done but I am out of time, adding comments for my progress
                     *
                     *
                    mRestaurantLocationListAdapter = new RestaurantLocationListAdapter(mViewModel.getCategoryWithRestaurants(totalBusiness), (view, name, id, position) -> {
                        startNavigationComponent(R.id.action_restaurantsInLocationFragment_to_restaurantDetailFragment, id, name);
                    });
                      mRestaurantLocationList.setAdapter(mRestaurantLocationListAdapter);
                     * */

                    mRestaurantLocationList.setAdapter(mRestaurantListAdapter); // just a placeholder adapter, TODO : remove when correct logic is 100% done
                    mRestaurantLocationList.setVisibility(View.VISIBLE);
                    mNoResultsTextView.setVisibility(View.GONE);
                } else {
                    mNoResultsTextView.setVisibility(View.VISIBLE);
                    mRestaurantLocationList.setVisibility(View.GONE);
                }
            });
        }
    }

    private void startNavigationComponent(int id, String business_id, String business_name) {
        /**
         * Both ways can be used to call next fragment/s, keeping it for informational purposes
         *
         *   NavDirections navDirections = RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment();
         *   final NavController navController = NavHostFragment.findNavController(this);
         *   navController.navigate(navDirections);
         *
         * */

        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.restaurantListFragment);
        NavController navController = navHostFragment != null ? navHostFragment.getNavController() : null;

        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.business_id), business_id);
        bundle.putString(getString(R.string.business_name), business_name);

        if (navController != null) {
            navController.navigate(id, bundle);
        }
    }

    private void showLoading(boolean show) {
        if (mViewLoading != null) {
            mViewLoading.bringToFront();
            mViewLoading.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupPlacesSearchQueryListener();
        } else {
            showDialog(getString(R.string.no_location_permission), getString(R.string.grant_location_permission));
        }
    }
}
