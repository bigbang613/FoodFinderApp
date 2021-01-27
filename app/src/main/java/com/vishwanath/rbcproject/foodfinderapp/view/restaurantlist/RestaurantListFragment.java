package com.vishwanath.rbcproject.foodfinderapp.view.restaurantlist;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vishwanath.rbcproject.foodfinderapp.R;
import com.vishwanath.rbcproject.foodfinderapp.utility.Util;
import com.vishwanath.rbcproject.foodfinderapp.view.adapters.RestaurantListAdapter;
import com.vishwanath.rbcproject.foodfinderapp.view.base.BaseFragment;
import com.vishwanath.rbcproject.foodfinderapp.view.base.LatLong;
import com.vishwanath.rbcproject.foodfinderapp.view.base.LatLongListener;
import com.vishwanath.rbcproject.foodfinderapp.view.viewmodels.RestaurantListFragmentViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vishwanath.rbcproject.foodfinderapp.utility.Constants.LOCATION_REQUEST;
import static com.vishwanath.rbcproject.foodfinderapp.utility.Constants.SORT_BY_CATEGORY;

public class RestaurantListFragment extends BaseFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.loading)
    View mViewLoading;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.restaurants_list)
    RecyclerView mRestaurantList;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fragment_searchview)
    SearchView mSearchView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fragment_textview_no_results)
    TextView mNoResultsTextView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.welcome_textview)
    TextView mWelcomeTextView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.search_places_textview)
    TextView mSearchPlacesTextView;
    @SuppressLint("NonConstantResourceId")
    @BindString(R.string.search_in_location)
    String mSearchPlacesLabel;

    private RestaurantListFragmentViewModel mViewModel;
    private RestaurantListAdapter mRestaurantListAdapter;
    private Spinner mSpinner;
    private String mSearchQuery = "";
    private LatLongListener mLatLongListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(RestaurantListFragmentViewModel.class);
        initializeSearchPlacesTextView();
        initializeLatLong();
        if (Util.isLocationPermissionPresent(requireActivity())) {
            setupSearchQueryListener();
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST);
        }
    }

    private void initializeSearchPlacesTextView() {
        int start = mSearchPlacesLabel.indexOf(getString(R.string.search));
        int end = mSearchPlacesLabel.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(mSearchPlacesLabel);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSearchPlacesTextView.setText(spannableStringBuilder);
        setupSearchPlacesClickListener();
    }

    private void setupSearchPlacesClickListener() {
        mSearchPlacesTextView.setOnClickListener(v -> startNavigationComponent(R.id.action_restaurantListFragment_to_restaurantsInLocationFragment));
    }

    private void initializeLatLong() {
        mLatLongListener = new LatLongListener(requireActivity());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupSearchQueryListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!Util.isNullOrEmpty(newText)) {
                    mSearchPlacesTextView.setVisibility(View.VISIBLE);
                    mWelcomeTextView.setVisibility(View.GONE);
                    loadRestaurants(newText, SORT_BY_CATEGORY[0], new LatLong(mLatLongListener.getLatitude(), mLatLongListener.getLongitude()));
                    mLatLongListener.stopLocationUpdates();
                } else {
                    hideSortSpinner();
                    mRestaurantList.setVisibility(View.GONE);
                    mWelcomeTextView.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.restaurant_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (getActivity() != null) {
            if (null == mRestaurantListAdapter) {
                showToast(getString(R.string.search_before_sorting));
            } else {
                mSpinner = getActivity().findViewById(R.id.sort_spinner);
                String[] sortItems = getActivity().getResources().getStringArray(R.array.sort_spinner_items);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, sortItems);
                adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                mSpinner.setAdapter(adapter);
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        loadRestaurants(mSearchQuery, SORT_BY_CATEGORY[position], new LatLong(mLatLongListener.getLatitude(), mLatLongListener.getLongitude()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadRestaurants(String newText, String sortBy, LatLong location) {
        mSearchQuery = newText;
        setupRestaurantsObserver();
        showLoading(true);
        mViewModel.getRestaurantList(mSearchQuery, sortBy, location);
    }

    private void setupRestaurantsObserver() {
        mViewModel.getRestaurants().observe(requireActivity(), totalBusiness -> {
            showLoading(false);
            if (!totalBusiness.getBusinesses().isEmpty()) {
                mRestaurantListAdapter = new RestaurantListAdapter(totalBusiness.getBusinesses(), (view, name, id, position) -> {
                    startNavigationComponent(R.id.action_restaurantListFragment_to_restaurantDetailFragment, id, name);
                });
                mRestaurantList.setAdapter(mRestaurantListAdapter);
                mRestaurantList.setVisibility(View.VISIBLE);
                mNoResultsTextView.setVisibility(View.GONE);
            } else {
                mNoResultsTextView.setVisibility(View.VISIBLE);
                mRestaurantList.setVisibility(View.GONE);
            }
        });
    }

    private void startNavigationComponent(int id) {
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.restaurantListFragment);
        NavController navController = navHostFragment != null ? navHostFragment.getNavController() : null;

        if (navController != null) {
            navController.navigate(id);
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

    private void hideSortSpinner() {
        if (mSpinner != null) {
            mSpinner.setVisibility(View.GONE);
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
            setupSearchQueryListener();
        } else {
            showDialog(getString(R.string.no_location_permission), getString(R.string.grant_location_permission));
        }
    }
}
