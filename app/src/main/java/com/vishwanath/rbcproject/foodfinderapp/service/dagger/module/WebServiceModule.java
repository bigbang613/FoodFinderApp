package com.vishwanath.rbcproject.foodfinderapp.service.dagger.module;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.vishwanath.rbcproject.foodfinderapp.exceptions.InternalServerErrorException;
import com.vishwanath.rbcproject.foodfinderapp.exceptions.PageNotFoundException;
import com.vishwanath.rbcproject.foodfinderapp.exceptions.UnknownException;
import com.vishwanath.rbcproject.foodfinderapp.service.api.interfaces.IRestaurantService;

import java.io.IOException;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebServiceModule {
    
    private static OkHttpClient sOkHttpClient;
    private final Context mContext;
    
    public WebServiceModule(Context context) {
        mContext = context;
    }
    
    private String getBaseUrl() {
        return "https://api.yelp.com/v3/";
    }


    
//    @Provides
//    @Singleton
//    public IRaceApiService provideIRaceApiService() {
//        return new RetrofitBuilder<>(IRaceApiService.class).create(getOkHttpClient(), getBaseUrl());
//    }
//
    @Provides
    @Singleton
    public IRestaurantService provideIRestaurantService(){
        //return new RestaurantWebService();

    return new RetrofitBuilder<>(IRestaurantService.class).create(getOkHttpClient(), getBaseUrl());

    }
    
    /**
     * Adds interceptor for catching unauthorized calls and handles globally.
     * May need to check if this affects API calls being handled in the current
     * context when we are going back to the login activity.
     *
     * @param chain the chain of current interceptors added to the HttpClient
     * @return client's response
     * @throws IOException
     */
    private Response handleHttpStatusCodes(Interceptor.Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        
        // Add status codes here that we want to globally handle.
        switch (response.code()) {
            case 401:
            case 403:
                break;
            case 404:
                throw new PageNotFoundException();
            case 500:
                throw new InternalServerErrorException();
            default:
                throw new UnknownException();
        }
//        response.close();
        return response;
    }
    
    @NonNull
    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
           // interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(getHeaderInterceptor());
//            builder.addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
////                    chain.request()
////                            .url().newBuilder()
////                            .addQueryParameter(
////                            .build();
//                    return chain.proceed(chain.request()
//                            .newBuilder()
//                            .addHeader("Authorization", "Bearer DL3u-mjBTvE38BE72raf_BJPKpwD1UYWL6yZAkBMO0ilR9cbMAKO8pwkSLmlX8deWE-0wMlXxR5gFwrAezWo4Tz0K1dw0c5-eBhIT6DatdWEUqiJsb289Uh8TcMMYHYx")
//                            .build());
//                }
//            });
            builder.addNetworkInterceptor(new StethoInterceptor());
           // builder.addInterceptor(this::handleHttpStatusCodes);
          //  builder.addInterceptor(new StethoInterceptor());
            sOkHttpClient = builder.build();
        }
        
        return sOkHttpClient;
    }

    @NonNull
    private Interceptor getHeaderInterceptor() {
        return chain -> {
            Request.Builder ongoing = chain.request().newBuilder();
            String token = "Bearer DL3u-mjBTvE38BE72raf_BJPKpwD1UYWL6yZAkBMO0ilR9cbMAKO8pwkSLmlX8deWE-0wMlXxR5gFwrAezWo4Tz0K1dw0c5-eBhIT6DatdWEUqiJsb289Uh8TcMMYHYx";
            ongoing.addHeader("Authorization", token);
            return chain.proceed(ongoing.build());
        };
    }


//    val builder = OkHttpClient.Builder()
//            .addInterceptor(Interceptor { chain ->
//            //to add open weather map key to call headers
//            val url: HttpUrl = chain.request().url().newBuilder()
//            .addQueryParameter("appid", "ea13a794ee52c8095bd209b3dd9fb2ba")
//            .build()
//        return@Interceptor chain.proceed(chain.request().newBuilder().url(url).build())
//    })
//            StethoHelper.addOkHttpInterceptor(builder)
//            FlipperHelper.addOkHttpShit(builder)
//            builder.build()

    private static class RetrofitBuilder <T> {
        private final Class<T> service;
        
        public RetrofitBuilder(Class<T> service) {
            this.service = service;
        }
        
        T create(OkHttpClient httpClient, String baseUrl) {
            Retrofit retrofit = getRetrofit(httpClient, baseUrl);
            return retrofit.create(service);
        }
        
        @NonNull
        private Retrofit getRetrofit(OkHttpClient client, String baseUrl) {
            return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();
        }
    }
}
