package com.search.images.service;

import com.search.images.config.APIProvider;
import com.search.images.model.search.SearchResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface HttpSearchApi {
    @GET("v2/search/image")
    Call<SearchResult> getSearchResult(@Header("authorization") String authorization,
                                       @Query("query") String query,
                                       @Query("page") int pageNum,
                                       @Query("size") int size);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIProvider.current().searchHost())
            .addConverterFactory(GsonConverterFactory.create())
            .callbackExecutor(ApiResultExecutors.callbackExecutor)
            .build();
}
