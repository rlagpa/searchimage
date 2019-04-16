package com.search.images.service;

import com.search.images.config.APIProvider;
import com.search.images.model.search.SearchResult;
import com.search.images.model.vision.Vision;

import lombok.NoArgsConstructor;
import retrofit2.Call;

@NoArgsConstructor
public class SearchService {

    private HttpSearchApi searchApi = HttpSearchApi.retrofit.create(HttpSearchApi.class);
    private HttpVisionApi visionApi = HttpVisionApi.retrofit.create(HttpVisionApi.class);

    public void search(String query, int pageNum) {
        final Call<SearchResult> call =
                searchApi.getSearchResult(APIProvider.current().autoToken(), query, pageNum, APIProvider.ITEM_PER_PAGE);
        call.enqueue(new SearchApiCallback());
    }

    public void getVision(String imageUrl) {
        final Call<Vision> call =
                visionApi.getSearchResult(APIProvider.current().autoToken(), imageUrl);
        call.enqueue(new VisionApiCallback());
    }
}

