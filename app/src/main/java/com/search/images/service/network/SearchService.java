package com.search.images.service.network;

import com.search.images.config.Constants;
import com.search.images.model.SearchResultVO;
import com.search.images.model.VisionVO;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.NoArgsConstructor;
import retrofit2.Call;

@NoArgsConstructor
public class SearchService {
    public static AtomicInteger pageNumGenerator = new AtomicInteger(1);

    private HttpImageApi httpImageApi = HttpImageApi.retrofit.create(HttpImageApi.class);
    private HttpVisionApi httpVisionApi = HttpVisionApi.retrofit.create(HttpVisionApi.class);

    public<T> void getSearchList(HttpResponseListener<T> listener, String query, int pageNum) {
        final Call<SearchResultVO> call = httpImageApi.getSearchResult(Constants.CURRENT_IMAGE_TYPE.getAuthorization(), query, pageNum, 20);
        call.enqueue(new HttpCallback(listener));
    }

    public<T> void getImageVision(HttpResponseListener<T> listener, String imageUrl) {
        final Call<VisionVO> call = httpVisionApi.getSearchResult(Constants.CURRENT_VISION_TYPE.getAuthorization(), imageUrl);
        call.enqueue(new HttpCallback(listener));
    }
}
