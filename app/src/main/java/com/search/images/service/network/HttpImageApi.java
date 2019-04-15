package com.search.images.service.network;

import com.search.images.config.APIProvider;
import com.search.images.model.search.SearchResultVO;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * use retrofit http client library request information
 */
public interface HttpImageApi {
    @GET("v2/search/image")
    Call<SearchResultVO> getSearchResult(@Header("authorization") String authorization,
                                         @Query("query") String query,
                                         @Query("page") int pageNum,
                                         @Query("size") int size);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIProvider.current().searchHost())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
