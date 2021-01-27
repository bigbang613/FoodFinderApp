package com.vishwanath.rbcproject.foodfinderapp.service.api.interfaces;

import com.vishwanath.rbcproject.foodfinderapp.model.BusinessDetail;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRestaurantService {
    @GET("businesses/search")
    Call<TotalBusiness> getRestaurants(@Query("term") String term, @Query("sort_by") String sortBy, @Query("latitude") String latitude, @Query("longitude") String longitude);

    @GET("businesses/search")
    Call<TotalBusiness> getRestaurants(@Query("location") String location);

    @GET("businesses/{id}")
    Call<BusinessDetail> getRestaurantDetail(@Path("id") String id);
}
