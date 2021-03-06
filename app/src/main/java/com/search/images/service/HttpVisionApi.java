package com.search.images.service;

import com.search.images.config.APIProvider;
import com.search.images.model.vision.Vision;

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
    Call<Vision> getSearchResult(@Header("authorization") String authorization,
                                 @Field("image_url") String imageUrl);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIProvider.current().detailHost())
            .addConverterFactory(GsonConverterFactory.create())
            .callbackExecutor(ApiResultExecutors.callbackExecutor)
            .build();
}
