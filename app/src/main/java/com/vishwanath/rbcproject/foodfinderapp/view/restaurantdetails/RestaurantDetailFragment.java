package com.vishwanath.rbcproject.foodfinderapp.view.restaurantdetails;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vishwanath.rbcproject.foodfinderapp.R;
import com.vishwanath.rbcproject.foodfinderapp.model.BusinessDetail;
import com.vishwanath.rbcproject.foodfinderapp.model.Category;
import com.vishwanath.rbcproject.foodfinderapp.utility.Util;
import com.vishwanath.rbcproject.foodfinderapp.view.adapters.HorizontalImageAdapter;
import com.vishwanath.rbcproject.foodfinderapp.view.base.BaseFragment;
import com.vishwanath.rbcproject.foodfinderapp.view.viewmodels.RestaurantListFragmentViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vishwanath.rbcproject.foodfinderapp.utility.Constants.REQUEST_CALL_PHONE;

public class RestaurantDetailFragment extends BaseFragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.loading)
    View mViewLoading;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.images_list)
    RecyclerView mImagesListView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.map_fragment)
    MapView mMapView;
    @BindDimen(R.dimen.item_width)
    int mMapPadding;
    @BindView(R.id.expensive_textview)
    TextView mExpensiveTextView;
    @BindView(R.id.category_textview)
    TextView mCategoryTextView;
    @BindView(R.id.restaurant_hours)
    TextView mResHoursTextView;
    @BindView(R.id.restaurant_distance_textview)
    TextView mResDistanceTextView;
    @BindView(R.id.restaurant_address)
    TextView mResAddressTextView;
    @BindView(R.id.call_layout_container)
    View mCallLayout;
    @BindView(R.id.get_directions_button)
    AppCompatButton mGetDirectionsButton;
    @BindView(R.id.dinein_button)
    AppCompatButton mDineInButton;
    @BindView(R.id.takeout_button)
    AppCompatButton mTakeOutButton;
    @BindView(R.id.delivery_button)
    AppCompatButton mDeliveryButton;

    private RestaurantListFragmentViewModel mViewModel;
    private String mBusinessId = "";
    private String mBusinessName = "";
    private GoogleMap mGoogleMap = null;
    private static final float MARKER_Z_MIDDLE = 0.5f;
    private static final float MIN_MAP_ZOOM = 18f;
    private Marker mMarker;
    private BusinessDetail mBusinessDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
        ButterKnife.bind(this, view);

        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.map_fragment, supportMapFragment)
                .commit();
        supportMapFragment.getMapAsync(googleMap ->
                mGoogleMap = googleMap
        );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mBusinessId = getArguments().getString(getString(R.string.business_id));
            mBusinessName = getArguments().getString(getString(R.string.business_name));
        }
        showLoading(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle(mBusinessName != null ? mBusinessName : "");
        mViewModel = new ViewModelProvider(requireActivity()).get(RestaurantListFragmentViewModel.class);
        setupRestaurantDetailsObserver();
        mViewModel.getRestaurantDetail(mBusinessId);
    }

    private void setupRestaurantDetailsObserver() {
        mViewModel.getRestaurantDetail().observe(requireActivity(), businessDetail -> {
            showLoading(false);
            Log.d("RestaurantDetail", businessDetail + "");
            if (!businessDetail.getPhotos().isEmpty()) {
                HorizontalImageAdapter imageAdapter = new HorizontalImageAdapter(businessDetail.getPhotos());
                mImagesListView.setAdapter(imageAdapter);
            }
            initLayout(businessDetail);
            initMap(businessDetail.getCoordinates().getLatitude(), businessDetail.getCoordinates().getLongitude());
        });
    }

    private void initLayout(BusinessDetail businessDetail) {
        mBusinessDetail = businessDetail;
        mExpensiveTextView.setText(businessDetail.getPrice());
        mCategoryTextView.setText(getCategories(businessDetail.getCategories()));
        mResHoursTextView.setText("Open till 10 PM"); // TODO : "getOpenHours(businessDetail.getOpenHours())"
        mResDistanceTextView.setText(R.string.unknown);
        mResAddressTextView.setText((businessDetail.getLocation()).toString());
        mCallLayout.setOnClickListener(v -> {

            if (!Util.isCallPermissionPresent(requireActivity())) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
            } else {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + businessDetail.getPhone()));
                startActivity(callIntent);
            }
        });

        mGetDirectionsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(businessDetail.getUrl()));
            startActivity(intent);
        });

        mDineInButton.setOnClickListener(this);
        mTakeOutButton.setOnClickListener(this);
        mDeliveryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dinein_button || v.getId() == R.id.takeout_button | v.getId() == R.id.delivery_button) {
            showToast("TODO Feature");
        }
    }

    private String getCategories(List<Category> categories) {
        StringBuilder builder = new StringBuilder();
        for (Category category : categories) {
            builder.append(category.getTitle()).append(", ");
        }
        return builder.toString();
    }

    private void initMap(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        mMarker = mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .zIndex(MARKER_Z_MIDDLE)
                .icon(BitmapDescriptorFactory.defaultMarker()));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(mMarker.getPosition());
        LatLngBounds bounds = builder.build();

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, mMapPadding));

        if (mGoogleMap.getCameraPosition().zoom > MIN_MAP_ZOOM) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MIN_MAP_ZOOM));
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

        if (requestCode == REQUEST_CALL_PHONE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + mBusinessDetail.getDisplayPhone()));
            startActivity(callIntent);
        } else {
            showDialog(getString(R.string.no_calling_permission), getString(R.string.grant_call_permission));
        }

    }
}
