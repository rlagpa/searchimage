package com.search.images.service.network;

import com.search.images.config.Constants;
import com.search.images.config.SearchSiteType;
import com.search.images.model.SearchResultVO;
import com.search.images.model.VisionVO;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface HttpVisionApi {
    @FormUrlEncoded
    @POST("v1/vision/face/detect")
    Call<VisionVO> getSearchResult(@Header("authorization") String authorization, @Field("image_url") String imageUrl);

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging).build();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.CURRENT_VISION_TYPE.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
}
