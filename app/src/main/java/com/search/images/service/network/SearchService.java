package com.search.images.service.network;

import com.search.images.config.APIProvider;
import com.search.images.model.search.SearchResultVO;
import com.search.images.model.vision.VisionVO;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.NoArgsConstructor;
import retrofit2.Call;

@NoArgsConstructor
public class SearchService {
    public static AtomicInteger pageNumGenerator = new AtomicInteger(1);

    private HttpImageApi httpImageApi = HttpImageApi.retrofit.create(HttpImageApi.class);
    private HttpVisionApi httpVisionApi = HttpVisionApi.retrofit.create(HttpVisionApi.class);

    public void getSearchList(HttpResponseListener<SearchResultVO> listener, String query, int pageNum) {
        final Call<SearchResultVO> call =
                httpImageApi.getSearchResult(APIProvider.current().autoToken(), query, pageNum, 20);
        call.enqueue(new HttpCallback<>(listener));
    }

    public void getImageVision(HttpResponseListener<VisionVO> listener, String imageUrl) {
        final Call<VisionVO> call =
                httpVisionApi.getSearchResult(APIProvider.current().autoToken(), imageUrl);
        call.enqueue(new HttpCallback<>(listener));
    }

    public void initializeSearchCondition() {
        pageNumGenerator.set(1);
    }
}

