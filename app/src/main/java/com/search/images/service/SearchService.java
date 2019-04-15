package com.search.images.service;

import com.search.images.config.APIProvider;
import com.search.images.model.search.SearchResultVO;
import com.search.images.model.vision.VisionVO;

import lombok.NoArgsConstructor;
import retrofit2.Call;

@NoArgsConstructor
public class SearchService {

    private HttpImageApi httpImageApi = HttpImageApi.retrofit.create(HttpImageApi.class);
    private HttpVisionApi httpVisionApi = HttpVisionApi.retrofit.create(HttpVisionApi.class);

    public void search(String query, int pageNum) {
        final Call<SearchResultVO> call =
                httpImageApi.getSearchResult(APIProvider.current().autoToken(), query, pageNum, APIProvider.ITEM_PER_PGAE);
        call.enqueue(new SearchApiCallback());
    }

    public void getVision(String imageUrl) {
        final Call<VisionVO> call =
                httpVisionApi.getSearchResult(APIProvider.current().autoToken(), imageUrl);
        call.enqueue(new VisionApiCallback());
    }
}

